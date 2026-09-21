package com.codingshuttle.razorpay.merchant_service.service.impl;

import com.codingshuttle.razorpay.common_lib.cache.ApiKeyCache;
import com.codingshuttle.razorpay.common_lib.exception.ResourceNotFoundException;
import com.codingshuttle.razorpay.common_lib.util.RandomizerUtil;
import com.codingshuttle.razorpay.merchant_service.dto.request.CreateApiKeyRequest;
import com.codingshuttle.razorpay.merchant_service.dto.response.ApiKeyCreateResponse;
import com.codingshuttle.razorpay.merchant_service.dto.response.ApiKeyResponse;
import com.codingshuttle.razorpay.merchant_service.entity.ApiKey;
import com.codingshuttle.razorpay.merchant_service.entity.Merchant;
import com.codingshuttle.razorpay.merchant_service.mapper.ApiKeyMapper;
import com.codingshuttle.razorpay.merchant_service.repository.ApiKeyRepository;
import com.codingshuttle.razorpay.merchant_service.repository.MerchantRepository;
import com.codingshuttle.razorpay.merchant_service.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ApiKeyServiceImpl implements ApiKeyService {

    private final MerchantRepository merchantRepository;
    private final ApiKeyRepository apiKeyRepository;
    private final ApiKeyMapper apiKeyMapper;
    private BCryptPasswordEncoder BCRPYT = new BCryptPasswordEncoder();
    private final ApiKeyCache apiKeyCache;

    @Override
    @Transactional
    public ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request) {
        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("merchant", merchantId));

        String keyId = "rzp_"+request.environment().name().toLowerCase()+"_"+ RandomizerUtil.randomBase64(24);
        String rawSecret = RandomizerUtil.randomBase64(40);

        ApiKey apiKey = ApiKey.builder()
                .merchant(merchant)
                .keyId(keyId)
                .keySecretHash(BCRPYT.encode(rawSecret))
                .environment(request.environment())
                .build();

        apiKey = apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId(), keyId, rawSecret, request.environment());
    }

    @Override
    public List<ApiKeyResponse> listByMerchant(UUID merchantId) {
        return apiKeyMapper.toResponseList(apiKeyRepository.findByMerchant_Id(merchantId));
    }

    @Override
    @Transactional
    public void revoke(UUID merchantId, UUID keyId) {
        ApiKey key = apiKeyRepository.findById(keyId)
                .filter(k -> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey", keyId));

        key.setEnabled(false);
        apiKeyCache.evict(key.getKeyId());
    }

    @Override
    @Transactional
    public ApiKeyCreateResponse rotate(UUID merchantId, UUID keyId) {
        ApiKey apiKey = apiKeyRepository.findById(keyId)
                .filter(k -> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey", keyId));

        if(!apiKey.isEnabled()) throw new RuntimeException("Cannot rotate a disabled key");

        String newRawSecret = RandomizerUtil.randomBase64(40);
        apiKey.setPreviousKeySecretHash(apiKey.getKeySecretHash());
        apiKey.setKeySecretHash(BCRPYT.encode(newRawSecret));
        apiKey.setRotatedAt(LocalDateTime.now());
        apiKey.setGracePeriodExpiresAt(LocalDateTime.now().plusHours(24));
        apiKey = apiKeyRepository.save(apiKey);

        apiKeyCache.evict(apiKey.getKeyId());

        return new ApiKeyCreateResponse(apiKey.getId(), apiKey.getKeyId(),
                newRawSecret, apiKey.getEnvironment());
    }

}




















package com.codingshuttle.razorpay.merchant_service.mapper;

import com.codingshuttle.razorpay.merchant_service.dto.response.ApiKeyCreateResponse;
import com.codingshuttle.razorpay.merchant_service.dto.response.ApiKeyResponse;
import com.codingshuttle.razorpay.merchant_service.entity.ApiKey;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApiKeyMapper {

    ApiKeyCreateResponse toCreateResponse(ApiKey apiKey);

    List<ApiKeyResponse> toResponseList(List<ApiKey> apiKeyList);
}

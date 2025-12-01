package com.learning.url_shortener.utils;

import com.learning.url_shortener.models.dto.UrlResponseDto;
import com.learning.url_shortener.models.entity.UrlEntity;

public class CommonUtils {

    public static String getShortUrl(String url) {
        // TODO SHORT URL ALGO
        return url;
    }

    public static UrlResponseDto convertToDto(UrlEntity savedEntity) {
        return UrlResponseDto.fromEntity(savedEntity);
    }
}

package com.learning.url_shortener.models.dto;

import com.learning.url_shortener.models.entity.UrlEntity;

import java.time.LocalDateTime;

public record UrlResponseDto(
        String shortCode,
        String originalUrl,
        LocalDateTime createdAt,
        Long accessCount
) {
    public static UrlResponseDto fromEntity(UrlEntity entity) {
        return new UrlResponseDto(
                entity.getShortUrl(),
                entity.getOriginalUrl(),
                entity.getCreatedAt(),
                entity.getAccessCount()
        );
    }
}

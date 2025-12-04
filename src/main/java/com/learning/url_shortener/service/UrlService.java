package com.learning.url_shortener.service;

import com.learning.url_shortener.exception.CustomDbException;
import com.learning.url_shortener.models.dto.UrlResponseDto;
import com.learning.url_shortener.models.entity.UrlEntity;
import com.learning.url_shortener.repository.UrlRepository;
import com.learning.url_shortener.utils.CommonUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlResponseDto createShortUrl(String url) {
        String shortUrl = CommonUtils.getShortUrl(url);
        urlRepository.findByShortUrl(shortUrl)
                .ifPresent(u -> {
                    throw new CustomDbException("URl already exist!!");
                });

        UrlEntity savedEntity = urlRepository.save(
                UrlEntity.builder()
                        .originalUrl(url)
                        .shortUrl(shortUrl)
                        .build()
        );
        return CommonUtils.convertToDto(savedEntity);
    }

    public UrlResponseDto getShortUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl)
                .map(UrlResponseDto::fromEntity)
                .orElseThrow(() -> new CustomDbException("URl already exist!!"));
    }

    public void deleteShortUrl(String shortUrl) {
    }

    public UrlResponseDto updateShortUrl(String shortUrl) {
        return null;
    }

    public UrlResponseDto getShortUrlStats(String shortUrl) {
        return null;
    }
}

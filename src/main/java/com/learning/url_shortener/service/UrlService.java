package com.learning.url_shortener.service;

import com.learning.url_shortener.exception.CustomDbException;
import com.learning.url_shortener.models.dto.UrlResponseDto;
import com.learning.url_shortener.models.entity.UrlEntity;
import com.learning.url_shortener.repository.UrlRepository;
import com.learning.url_shortener.utils.CommonUtils;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    public UrlResponseDto getResponseDtoFromShortUrl(String shortUrl) {
        UrlEntity urlEntity = findShortUrlOrThrow(shortUrl);
        updateStats(urlEntity);
        return CommonUtils.convertToDto(urlEntity);
    }

    private UrlEntity findShortUrlOrThrow(String shortUrl) {
        Optional<UrlEntity> urlEntity = urlRepository.findByShortUrl(shortUrl);
        if (urlEntity.isPresent())
            return urlEntity.get();

        throw new CustomDbException("URL doesn't exist!!");
    }

    public void deleteShortUrl(String shortUrl) {
        final UrlEntity urlEntity = findShortUrlOrThrow(shortUrl);
        urlRepository.delete(urlEntity);
    }

    public UrlResponseDto updateShortUrl(String shortUrl) {
        final UrlEntity urlEntity = findShortUrlOrThrow(shortUrl);
        urlEntity.setShortUrl(shortUrl);
        final UrlEntity savedEntity = urlRepository.save(urlEntity);
        return CommonUtils.convertToDto(savedEntity);
    }

    public Long getShortUrlStats(String shortUrl) {
        final UrlEntity urlEntity = findShortUrlOrThrow(shortUrl);
        return urlEntity.getAccessCount();
    }

    @Async
    public void updateStats(UrlEntity urlEntity) {
        urlEntity.setAccessCount(urlEntity.getAccessCount() + 1);
        urlRepository.save(urlEntity);
    }
}

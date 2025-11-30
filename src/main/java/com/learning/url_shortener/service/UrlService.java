package com.learning.url_shortener.service;

import com.learning.url_shortener.repository.UrlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
}

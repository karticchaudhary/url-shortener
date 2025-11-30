package com.learning.url_shortener.repository;

import com.learning.url_shortener.models.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UrlEntity, Long> {
}

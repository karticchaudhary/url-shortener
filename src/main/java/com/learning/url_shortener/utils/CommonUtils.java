package com.learning.url_shortener.utils;

import com.learning.url_shortener.models.dto.UrlResponseDto;
import com.learning.url_shortener.models.entity.UrlEntity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommonUtils {

    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String getShortUrl(String url) {
        return shorten(url);
    }

    private static String shorten(String longUrl) {
        byte[] hash = sha256(longUrl);

        // Use first 6 bytes -> 48 bits -> short but low collision chance
        long value = 0;
        for (int i = 0; i < 6; i++) {
            value = (value << 8) | (hash[i] & 0xFF);
        }

        return toBase62(value);
    }

    private static byte[] sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String toBase62(long value) {
        if (value == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (value > 0) {
            int rem = (int) (value % 62);
            sb.append(BASE62.charAt(rem));
            value /= 62;
        }
        return sb.reverse().toString();
    }

    public static UrlResponseDto convertToDto(UrlEntity savedEntity) {
        savedEntity.setShortUrl("https://" + savedEntity.getShortUrl() + ".com");
        return UrlResponseDto.fromEntity(savedEntity);
    }

}

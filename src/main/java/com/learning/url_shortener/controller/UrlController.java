package com.learning.url_shortener.controller;

import com.learning.url_shortener.models.dto.UrlResponseDto;
import com.learning.url_shortener.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
Create a new short URL
Retrieve an original URL from a short URL
Update an existing short URL
Delete an existing short URL
Get statistics on the short URL (e.g., number of times accessed)
*/

@RestController
@RequestMapping("/v1/api/shorten")
@AllArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping
    @Operation(summary = "Create short URL",
            description = "Accepts a Long URL and returns response.")
    public ResponseEntity<UrlResponseDto> createShortUrl(@RequestParam("short_url") String shortUrl) {
        UrlResponseDto urlResponseDto = urlService.createShortUrl(shortUrl);
        return ResponseEntity.ok(urlResponseDto);
    }

    @GetMapping
    @Operation(summary = "Get short URL Details",
            description = "Accepts a Short URL and returns response.")
    public ResponseEntity<UrlResponseDto> getEntityFromShortUrl(@RequestParam("short_url") String shortUrl) {
        UrlResponseDto urlResponseDto = urlService.getResponseDtoFromShortUrl(shortUrl);
        return ResponseEntity.ok(urlResponseDto);
    }

    @PutMapping
    @Operation(summary = "Update short URL",
            description = "Accepts a Short URL and returns response.")
    public ResponseEntity<UrlResponseDto> updateShortUrl(@PathVariable("short_url") String shortUrl) {
        UrlResponseDto urlResponseDto = urlService.updateShortUrl(shortUrl);
        return ResponseEntity.ok(urlResponseDto);
    }

    @DeleteMapping
    @Operation(summary = "Delete short URL",
            description = "Delete record on basis of short URL.")
    public void deleteShortUrl(@PathVariable("short_url") String shortUrl) {
        urlService.deleteShortUrl(shortUrl);
    }

    @GetMapping("/{short_url}/stats")
    @Operation(summary = "Get short URL access stats",
            description = "Accepts a short URL and returns access count.")
    public Long getShortUrlStats(@PathVariable("short_url") String shortUrl) {
        return urlService.getShortUrlStats(shortUrl);
    }

}

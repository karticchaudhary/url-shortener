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
            description = "Accepts a long URL and returns a unique short code.")
    public ResponseEntity<UrlResponseDto> createShortUrl(@RequestParam("short_url") String shortUrl) {
        UrlResponseDto urlResponseDto = urlService.createShortUrl(shortUrl);
        return ResponseEntity.ok(urlResponseDto);
    }

    @GetMapping
    public ResponseEntity<UrlResponseDto> getUrl(@RequestParam("short_url") String shortUrl) {
        UrlResponseDto urlResponseDto = urlService.getShortUrl(shortUrl);
        return ResponseEntity.ok(urlResponseDto);
    }

    @PutMapping("/{short_url}")
    public void updateShortUrl(@PathVariable("short_url") String shortUrl) {
        UrlResponseDto urlResponseDto = urlService.updateShortUrl(shortUrl);
    }

    @DeleteMapping("/{short_url}")
    public void deleteShortUrl(@PathVariable("short_url") String shortUrl) {
        urlService.deleteShortUrl(shortUrl);
    }

    @GetMapping("/{short_url}/stats")
    public void getShortUrlStats(@PathVariable("short_url") String shortUrl) {
        UrlResponseDto urlResponseDto = urlService.getShortUrlStats(shortUrl);
    }

}

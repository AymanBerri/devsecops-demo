package com.example.devsecopsdemo;

import org.springframework.web.bind.annotation.*;

@RestController
public class SearchController {

    // VULNERABILITY: Hardcoded secret (SAST will detect this)
    private static final String ADMIN_KEY = "sk-1234567890abcdef";

    @GetMapping("/search")
    public String search(@RequestParam String q) {
        return "Search results for: " + q;
    }

    @GetMapping("/admin")
    public String admin(@RequestParam String key) {
        if (ADMIN_KEY.equals(key)) {
            return "Admin access granted";
        }
        return "Access denied";
    }
}
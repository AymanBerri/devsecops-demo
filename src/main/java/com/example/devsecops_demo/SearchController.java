package com.example.devsecopsdemo;

import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class SearchController {

    // VULNERABILITY: Hardcoded secret (SAST should detect)
    private static final String ADMIN_KEY = "sk-1234567890abcdef";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/search")
    public String search(@RequestParam String q) {
        // VULNERABILITY: SQL Injection
        String query = "SELECT * FROM books WHERE title LIKE '%" + q + "%'";
        return jdbcTemplate.queryForObject(query, String.class);
    }

    @GetMapping("/admin")
    public String admin(@RequestParam String key) {
        if (ADMIN_KEY.equals(key)) {
            return "Admin access granted";
        }
        return "Access denied";
    }
}
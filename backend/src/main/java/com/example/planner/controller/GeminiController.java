package com.example.planner.controller;

import com.example.planner.service.GeminiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gemini")
public class GeminiController {

    private final GeminiService geminiService;

    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/generate")
    public String generate(@RequestBody String prompt) {
        return geminiService.generateText(prompt);
    }
}

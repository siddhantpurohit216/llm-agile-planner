package com.example.planner.llm;

import com.example.planner.service.GeminiService;
import org.springframework.stereotype.Component;

@Component
public class LLMExecutor implements LLMClient {

    private final GeminiService geminiService;

    public LLMExecutor(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @Override
    public String generate(String prompt) {
        return geminiService.generateText(prompt);
    }
}

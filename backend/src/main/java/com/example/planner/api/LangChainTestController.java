package com.example.planner.api;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LangChainTestController {
    private final GoogleAiGeminiChatModel geminiChatModel;

    public LangChainTestController(GoogleAiGeminiChatModel geminiChatModel) {
        this.geminiChatModel = geminiChatModel;
    }

    @GetMapping("/langchain/test")
    public String test(
            @RequestParam(defaultValue = "Say hello in one sentence") String prompt
    ) {
        return geminiChatModel.generate(prompt);
    }
}

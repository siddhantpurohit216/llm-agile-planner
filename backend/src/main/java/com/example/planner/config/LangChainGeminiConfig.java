package com.example.planner.config;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LangChainGeminiConfig {
    @Bean
    public GoogleAiGeminiChatModel geminiChatModel() {
        return GoogleAiGeminiChatModel.builder()
                .apiKey("AIzaSyCt0EnOZCU9kbODsp-HilQeKW16mnR0l3o")
                .modelName("gemini-2.5-flash")
                .temperature(0.7)
                .build();
    }
}

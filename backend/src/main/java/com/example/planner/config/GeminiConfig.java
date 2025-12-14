package com.example.planner.config;
import com.google.genai.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeminiConfig {
    @Bean
    public Client geminiClient() {
        // HARD-CODE ONLY FOR NOW (replace later with env var)
        return Client.builder()
                .apiKey("AIzaSyCt0EnOZCU9kbODsp-HilQeKW16mnR0l3o")
                .build();
    }
}

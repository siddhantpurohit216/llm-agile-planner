//package com.example.planner.config;
//
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SpringAiConfig {
//
//    @Bean
//    public ChatClient chatClient(ChatClient.Builder builder) {
//        return builder.build();
//    }
//
//
//    @Bean
//    public GoogleAiGeminiChatModel geminiChatModel() {
//        return GoogleAiGeminiChatModel.builder()
//                .apiKey("YOUR_GEMINI_API_KEY")
//                .modelName("gemini-1.5-flash")
//                .temperature(0.7)
//                .build();
//    }
//
//}

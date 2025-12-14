//package com.example.planner.llm;
//
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.stereotype.Component;
//
//@Component
//public class LlmRunner {
//
//    private final ChatClient chatClient;
//
//    public LlmRunner(ChatClient chatClient) {
//        this.chatClient = chatClient;
//    }
//
//    public String run(String systemRole, String userPrompt) {
//        return chatClient
//                .prompt()
//                .system(systemRole)
//                .user(userPrompt)
//                .call()
//                .content();
//    }
//}

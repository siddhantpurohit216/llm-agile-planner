//package com.example.planner.api;
//
//import com.example.planner.llm.LlmRunner;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class PipelineController {
//
//    private final LlmRunner llmRunner;
//
//    public PipelineController(LlmRunner llmRunner) {
//        this.llmRunner = llmRunner;
//    }
//
//    @GetMapping("/ai/test")
//    public String testAi(@RequestParam(defaultValue = "Say hello in one sentence") String prompt) {
//        return llmRunner.run(
//                "You are a helpful assistant",
//                prompt
//        );
//    }
//
//    @GetMapping("/health")
//    public String health() {
//        return "Planner backend is running ✅";
//    }
//}

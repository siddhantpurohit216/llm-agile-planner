package com.example.planner.steps.stories;

import com.example.planner.llm.LLMClient;
import com.example.planner.model.PipelineRun;
import com.example.planner.orchestrator.StepResult;
import com.example.planner.orchestrator.StepRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class StoryGenerator implements StepRunner {

    private final LLMClient llm;

    public StoryGenerator(LLMClient llm) {
        this.llm = llm;
    }

    @Override
    public String stepName() {
        return "STORY_GENERATION";
    }

    @Override
    public boolean requiresUserInput() {
        return true; // pause again
    }

    @Override
    public StepResult run(PipelineRun run) {
        String prompt = """
        You are a Product Manager.
        Convert the following epics into detailed user stories.
        Use the format:
        - Epic:
          - As a user, I want ...
        
        Epics:
        %s
        """.formatted(run.getOutput("EPIC_GENERATION"));

        String output = llm.generate(prompt);
        return StepResult.ok(output);
    }
}

package com.example.planner.steps.epics;

import com.example.planner.llm.JsonUtils;
import com.example.planner.llm.LLMClient;
import com.example.planner.llm.PromptTemplates;
import com.example.planner.model.PipelineRun;
import com.example.planner.orchestrator.StepResult;
import com.example.planner.orchestrator.StepRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class EpicGenerator implements StepRunner {

    private final LLMClient llm;

    public EpicGenerator(LLMClient llm) {
        this.llm = llm;
    }

    @Override
    public String stepName() {
        return "EPIC_GENERATION";
    }

    @Override
    public boolean requiresUserInput() {
        return true; // ✅ pause after epics
    }

    @Override
    public StepResult run(PipelineRun run) {
        String raw = llm.generate(
                PromptTemplates.epics(
                        run.getOutput("REQUIREMENT_GENERATION")
                )
        );

        System.out.println("RAW EPIC GENERATION OUTPUT:\n" + raw);

        try {
            String normalized = JsonUtils.normalize(raw);
            return StepResult.ok(normalized);
        } catch (Exception e) {
            return StepResult.fail("Invalid JSON from EPIC_GENERATION: " + raw);
        }
    }

}

package com.example.planner.steps.epics;

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
        return false; // 👈 pause after this
    }

    @Override
    public StepResult run(PipelineRun run) {
        String out = llm.generate(
                PromptTemplates.epics(
                        run.getOutput("REQUIREMENT_GENERATION")
                )
        );
        return StepResult.ok(out);
    }
}

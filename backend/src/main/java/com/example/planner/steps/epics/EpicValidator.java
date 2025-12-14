package com.example.planner.steps.epics;

import com.example.planner.llm.LLMClient;
import com.example.planner.llm.PromptTemplates;
import com.example.planner.model.PipelineRun;
import com.example.planner.orchestrator.StepResult;
import com.example.planner.orchestrator.StepRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class EpicValidator implements StepRunner {

    private final LLMClient llm;

    public EpicValidator(LLMClient llm) {
        this.llm = llm;
    }

    @Override
    public String stepName() {
        return "EPIC_VALIDATION";
    }

    @Override
    public StepResult run(PipelineRun run) {
        String epics = run.getOutput("EPIC_GENERATION");

        String response = llm.generate(
                PromptTemplates.validate(epics)
        );

        if (response.startsWith("VALID")) {
            return StepResult.ok("VALIDATED");
        }
        return StepResult.fail(response);
    }
}

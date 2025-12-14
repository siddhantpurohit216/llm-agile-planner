package com.example.planner.steps.requirements;

import com.example.planner.llm.LLMClient;
import com.example.planner.llm.PromptTemplates;
import com.example.planner.model.PipelineRun;
import com.example.planner.orchestrator.StepResult;
import com.example.planner.orchestrator.StepRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class RequirementValidator implements StepRunner {

    private final LLMClient llm;

    public RequirementValidator(LLMClient llm) {
        this.llm = llm;
    }

    @Override
    public String stepName() {
        return "REQUIREMENT_VALIDATION";
    }

    @Override
    public StepResult run(PipelineRun run) {
        String response = llm.generate(
                PromptTemplates.validate(
                        run.getOutput("REQUIREMENT_GENERATION")
                )
        );

        if (response.startsWith("VALID")) {
            return StepResult.ok("VALIDATED");
        }
        return StepResult.fail(response);
    }
}

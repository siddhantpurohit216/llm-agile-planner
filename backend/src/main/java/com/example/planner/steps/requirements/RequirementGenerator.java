package com.example.planner.steps.requirements;

import com.example.planner.llm.LLMClient;
import com.example.planner.llm.PromptTemplates;
import com.example.planner.model.PipelineRun;
import com.example.planner.orchestrator.StepResult;
import com.example.planner.orchestrator.StepRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class RequirementGenerator implements StepRunner {

    private final LLMClient llm;

    public RequirementGenerator(LLMClient llm) {
        this.llm = llm;
    }

    @Override
    public String stepName() {
        return "REQUIREMENT_GENERATION";
    }

    @Override
    public StepResult run(PipelineRun run) {
        String out = llm.generate(
                PromptTemplates.requirements(run.getRawInput())
        );
        return StepResult.ok(out);
    }
}

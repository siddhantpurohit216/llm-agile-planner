package com.example.planner.steps.requirements;

import com.example.planner.llm.JsonUtils;
import com.example.planner.llm.LLMClient;
import com.example.planner.llm.PromptTemplates;
import com.example.planner.model.PipelineRun;
import com.example.planner.orchestrator.StepResult;
import com.example.planner.orchestrator.StepRunner;
import com.fasterxml.jackson.databind.JsonNode;
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
                PromptTemplates.validateJson(
                        run.getOutput("REQUIREMENT_GENERATION"),
                        "JSON must contain a non-empty 'requirements' array"
                )
        );

        try {
            JsonNode node = JsonUtils.parse(response);
            boolean valid = node.get("valid").asBoolean();

            if (valid) {
                return StepResult.ok(response);
            }
            return StepResult.fail(node.get("reason").asText());

        } catch (Exception e) {
            // 👇 THIS IS IMPORTANT
            return StepResult.fail(
                    "Validator returned invalid JSON: " + response
            );
        }
    }

}

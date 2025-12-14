package com.example.planner.steps.epics;

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
        String epicsJson = run.getOutput("EPIC_GENERATION");

        String response = llm.generate(
                PromptTemplates.validateJson(
                        epicsJson,
                        "Must contain a non-empty 'epics' array with id, title, description"
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
            return StepResult.fail("Epic validation returned invalid JSON");
        }
    }
}
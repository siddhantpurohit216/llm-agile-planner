package com.example.planner.orchestrator;

import com.example.planner.llm.JsonUtils;
import com.example.planner.model.PipelineRun;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlanningPipeline {

    private final List<StepRunner> steps;

    public PlanningPipeline(List<StepRunner> steps) {
        this.steps = steps;
    }

    public PipelineRun execute(PipelineRun run) {

        System.out.println("\n================ PIPELINE START ================");
        System.out.println("PIPELINE ID: " + run.getId());

        if (run.getRawInput() != null) {
            System.out.println("\nUSER INPUT:");
            System.out.println(run.getRawInput());
        }

        for (StepRunner step : steps) {

            // Skip already completed steps (resume support)
            if (run.hasOutput(step.stepName())) {
                continue;
            }

            System.out.println("\n---- STEP START: " + step.stepName() + " ----");

            int attempts = 0;
            StepResult result;

            do {
                attempts++;
                run.markRunning(step.stepName());

                System.out.println("Attempt: " + attempts);

                result = step.run(run);

                if (!result.success()) {
                    System.out.println("❌ STEP FAILED");
                    System.out.println("Error:");
                    System.out.println(result.error());
                }

            } while (!result.success() && attempts <= step.maxRetries());

            // If still failed after retries → stop pipeline
            if (!result.success()) {
                System.out.println("\n❌ PIPELINE FAILED AT STEP: " + step.stepName());
                System.out.println("===============================================");
                return run;
            }

            // Store step output
            run.putOutput(step.stepName(), result.jsonOutput());

            // Pretty-print output
            System.out.println("✅ STEP SUCCESS: " + step.stepName());
            System.out.println("OUTPUT:");
            System.out.println(JsonUtils.pretty(result.jsonOutput()));

            // Pause if user interaction required
            if (step.requiresUserInput()) {
                System.out.println("\n⏸ PIPELINE PAUSED (WAITING FOR USER)");
                System.out.println("LAST STEP: " + step.stepName());
                System.out.println("===============================================");
                run.waitForUser();
                return run;
            }
        }

        // All steps completed
        run.complete();

        System.out.println("\n✅ PIPELINE COMPLETED SUCCESSFULLY");
        System.out.println("===============================================");

        return run;
    }
}

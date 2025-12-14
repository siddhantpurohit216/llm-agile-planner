package com.example.planner.orchestrator;

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

        for (StepRunner step : steps) {

            if (run.hasOutput(step.stepName())) continue;

            int attempts = 0;
            StepResult result;

            do {
                run.markRunning(step.stepName());
                result = step.run(run);
                attempts++;
            } while (!result.success() && attempts <= step.maxRetries());

            if (!result.success()) {
                run.fail(step.stepName(), result.error());
                return run;
            }

            run.putOutput(step.stepName(), result.output());

            if (step.requiresUserInput()) {
                run.waitForUser();
                return run;
            }
        }

        run.complete();
        return run;
    }
}

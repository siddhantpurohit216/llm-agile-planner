package com.example.planner.orchestrator;

import com.example.planner.model.PipelineRun;

public interface StepRunner {

    String stepName();

    StepResult run(PipelineRun run);

    default int maxRetries() {
        return 2;
    }

    default boolean requiresUserInput() {
        return false;
    }
}

package com.example.planner.orchestrator;

public record StepResult(
        boolean success,
        String jsonOutput,
        String error
) {
    public static StepResult ok(String json) {
        return new StepResult(true, json, null);
    }

    public static StepResult fail(String error) {
        return new StepResult(false, null, error);
    }
}

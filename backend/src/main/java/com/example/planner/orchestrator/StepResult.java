package com.example.planner.orchestrator;

public record StepResult(boolean success, String output, String error) {

    public static StepResult ok(String output) {
        return new StepResult(true, output, null);
    }

    public static StepResult fail(String error) {
        return new StepResult(false, null, error);
    }
}

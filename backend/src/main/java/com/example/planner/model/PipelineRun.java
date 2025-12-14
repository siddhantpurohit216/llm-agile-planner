package com.example.planner.model;

import java.time.Instant;
import java.util.*;

public class PipelineRun {

    public enum Status {
        CREATED, RUNNING, WAITING_FOR_USER, FAILED, COMPLETED
    }

    private final UUID id;
    private Status status;
    private String rawInput;
    private String currentStep;

    private final Map<String, String> outputs = new LinkedHashMap<>();
    private final Map<String, Integer> attempts = new HashMap<>();

    private final Instant createdAt;
    private Instant updatedAt;

    public static PipelineRun start(String input) {
        return new PipelineRun(input);
    }

    private PipelineRun(String input) {
        this.id = UUID.randomUUID();
        this.rawInput = input;
        this.status = Status.CREATED;
        this.createdAt = Instant.now();
        this.updatedAt = createdAt;
    }

    public void markRunning(String step) {
        this.status = Status.RUNNING;
        this.currentStep = step;
        attempts.merge(step, 1, Integer::sum);
        this.updatedAt = Instant.now();
    }

    public void putOutput(String step, String output) {
        outputs.put(step, output);
        this.updatedAt = Instant.now();
    }

    public void waitForUser() {
        this.status = Status.WAITING_FOR_USER;
        this.updatedAt = Instant.now();
    }

    public void fail(String step, String reason) {
        this.status = Status.FAILED;
        this.currentStep = step;
        this.updatedAt = Instant.now();
    }

    public void complete() {
        this.status = Status.COMPLETED;
        this.currentStep = null;
        this.updatedAt = Instant.now();
    }

    public boolean hasOutput(String step) {
        return outputs.containsKey(step);
    }

    public String getOutput(String step) {
        return outputs.get(step);
    }

    public int attemptsFor(String step) {
        return attempts.getOrDefault(step, 0);
    }

    public UUID getId() { return id; }
    public Status getStatus() { return status; }
    public String getRawInput() { return rawInput; }
    public Map<String, String> getOutputs() { return outputs; }
}

package com.example.planner.controller;

import com.example.planner.model.PipelineRun;
import com.example.planner.orchestrator.PlanningPipeline;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/planning")
public class PlanningController {

    private final PlanningPipeline pipeline;

    public PlanningController(PlanningPipeline pipeline) {
        this.pipeline = pipeline;
    }

    @PostMapping("/start")
    public PipelineRun start(@RequestBody String input) {
        return pipeline.execute(PipelineRun.start(input));
    }

    @PostMapping("/resume")
    public PipelineRun resume(@RequestBody PipelineRun run) {
        return pipeline.execute(run);
    }
}

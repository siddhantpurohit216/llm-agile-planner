package com.example.planner.llm;

public class PromptTemplates {

    public static String requirements(String input) {
        return """
        You are a product analyst.

        Convert the following text into structured requirements.

        OUTPUT RULES (MANDATORY):
        - Respond ONLY with valid JSON
        - Do NOT include markdown
        - Do NOT include explanations
        - Follow this schema exactly

        Schema:
        {
          "requirements": [
            {
              "id": "REQ-1",
              "title": "string",
              "description": "string"
            }
          ]
        }

        Input:
        %s
        """.formatted(input);
    }

    public static String epics(String requirementsJson) {
        return """
    You are a JSON generation engine.

    TASK:
    Convert the following requirements JSON into epics.

    INPUT JSON:
    %s

    OUTPUT RULES (MANDATORY):
    - Respond ONLY with valid JSON
    - Do NOT add explanations
    - Do NOT add markdown
    - Do NOT add text before or after JSON

    Output schema:
    {
      "epics": [
        {
          "id": "EPIC-1",
          "title": "string",
          "description": "string"
        }
      ]
    }
    """.formatted(requirementsJson);
    }


    public static String validateJson(String json, String rule) {
        return """
    You are a JSON validation engine.

    RULE:
    %s

    INPUT JSON:
    %s

    OUTPUT RULES (MANDATORY):
    - Respond ONLY with valid JSON
    - No markdown
    - No explanations
    - No extra text

    Output schema:
    {
      "valid": true | false,
      "reason": "string"
    }
    """.formatted(rule, json);
    }

}

package com.example.planner.llm;

public class PromptTemplates {

    public static String requirements(String input) {
        return """
        You are a product analyst.
        Convert the following text into clear software requirements:

        %s
        """.formatted(input);
    }

    public static String epics(String requirements) {
        return """
        Convert the following requirements into epics.
        Return a numbered list.

        %s
        """.formatted(requirements);
    }

    public static String validate(String content) {
        return """
        You are a strict reviewer.
        Validate the following output.
        Reply ONLY with:
        VALID
        or
        INVALID: <reason>

        %s
        """.formatted(content);
    }
}

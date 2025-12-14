package com.example.planner;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlannerApplication.class, args);
	}

//    @Bean
//    CommandLineRunner runGeminiOnStartup() {
//        return args -> {
//            Client client = Client.builder()
//                    .apiKey("AIzaSyCt0EnOZCU9kbODsp-HilQeKW16mnR0l3o") // hard-coded ONLY for testing
//                    .build();
//
//            GenerateContentResponse response =
//                    client.models.generateContent(
//                            "gemini-2.5-flash",
//                            "Explain how RAM works in a few words",
//                            null
//                    );
//
//            System.out.println("Gemini response:");
//            System.out.println(response.text());
//        };
//    }
}

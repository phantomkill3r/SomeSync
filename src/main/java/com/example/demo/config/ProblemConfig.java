package com.example.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.ProblemModule;

@Configuration
public class ProblemConfig {

    @Autowired
    public void configureObjectMapper(ObjectMapper mapper) {
        mapper.findAndRegisterModules();
        mapper.registerModule(new ProblemModule());
      }
}
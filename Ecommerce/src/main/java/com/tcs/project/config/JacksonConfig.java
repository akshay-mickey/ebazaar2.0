//package com.tcs.project.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class JacksonConfig {
//
//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.UNWRAP_ROOT_VALUE, true);
//        // Add more configurations as needed
//        return objectMapper;
//    }
//}

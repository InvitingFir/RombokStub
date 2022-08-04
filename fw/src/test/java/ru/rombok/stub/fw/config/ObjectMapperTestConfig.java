package ru.rombok.stub.fw.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.rombok.stub.test.TestObjectMapper;

@TestConfiguration
public class ObjectMapperTestConfig {

    @Bean
    ObjectMapper mapper() {
        return new TestObjectMapper();
    }
}

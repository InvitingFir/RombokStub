package ru.rombok.stub.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.InputStream;

@Getter
public class TestObjectProvider {
    protected ObjectMapper mapper;

    public TestObjectProvider() {
        this(new TestObjectMapper());
    }

    public TestObjectProvider(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public <T> T fromFile(String path, Class<T> clazz) {
        try {
            InputStream resourceAsStream = getClass().getResourceAsStream(path);
            return mapper.readValue(resourceAsStream, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T fromFile(String path, TypeReference<T> clazz) {
        try {
            InputStream resourceAsStream = getClass().getResourceAsStream(path);
            return mapper.readValue(resourceAsStream, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

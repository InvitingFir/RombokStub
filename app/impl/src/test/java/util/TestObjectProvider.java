package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.InputStream;

@Getter
public class TestObjectProvider {
    private final ObjectMapper mapper;

    public TestObjectProvider() {
        this.mapper = new TestObjectMapper();
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
}

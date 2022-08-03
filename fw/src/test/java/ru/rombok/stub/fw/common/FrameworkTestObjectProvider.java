package ru.rombok.stub.fw.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.rombok.stub.test.TestObjectProvider;

import static java.lang.String.format;

@Component
public class FrameworkTestObjectProvider extends TestObjectProvider {
    private final ApplicationContext context;

    public FrameworkTestObjectProvider(ObjectMapper mapper, ApplicationContext context) {
        this.mapper = mapper;
        this.context = context;
    }

    public <T> T forClass(Class<T> clazz) {
        try {
            return context.getBean(clazz);
        } catch (Exception e) {
            throw new IllegalArgumentException(format("No object of class %s is provided in context", clazz));
        }
    }
}

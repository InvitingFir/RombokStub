package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class RombokAssertions {
    public static final ObjectMapper mapper;

    static {
        mapper = new TestObjectMapper();
    }

    public static void assertEqualsToFile(Object o, String relativePath) {
        Function<Class<?>, URL> firstGetResourceUrl = aClass -> aClass.getResource(relativePath);
        String actualObjectString = getObjectAsJsonString(o);
        String expectedObjectString = firstGetResourceUrl
            .andThen(urlToUri())
            .andThen(Path::of)
            .andThen(pathToString())
            .apply(mapper.getClass());

        assertThat(actualObjectString)
            .isEqualTo(expectedObjectString);
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private static Function<Path, String> pathToString() {
        return path -> {
            try {
                return Files.readString(path);
            } catch (Exception e) {
                throw new IllegalArgumentException(String.format("Failed to read file string from %s", path));
            }
        };
    }

    private static Function<URL, URI> urlToUri() {
        return url -> {
            try {
                return url.toURI();
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException(String.format("Failed to convert URL %s to URI", url));
            }
        };
    }

    private static String getObjectAsJsonString(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to write object as string", e);
        }
    }
}

package ru.rombok.stub.fw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.rombok.stub")
public class StubApplication {
    public static void main(String[] args) {
        SpringApplication.run(StubApplication.class, args);
    }
}

package ru.rombok.stub.fw.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.web.context.WebApplicationContext;

@TestConfiguration
@RequiredArgsConstructor
public class GraphQlTestConfig {
    private final WebApplicationContext context;

    //    @Bean
    //    public HttpGraphQlTester httpGraphQlTester() {
    //        WebTestClient client =
    //            MockMvcWebTestClient.bindToApplicationContext(context)
    //                .configureClient()
    //                .baseUrl("/graphql")
    //                .build();
    //
    //        return HttpGraphQlTester.create(client);
    //    }
}

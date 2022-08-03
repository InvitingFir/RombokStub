package ru.rombok.stub.fw.config;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreSqlDatabaseContainer extends PostgreSQLContainer<PostgreSqlDatabaseContainer> {
    private static final String IMAGE_VERSION = "postgres:11.1";
    private static PostgreSqlDatabaseContainer container;

    private PostgreSqlDatabaseContainer() {
        super(IMAGE_VERSION);
    }

    public static PostgreSqlDatabaseContainer getInstance() {
        if (container == null) {
            container = new PostgreSqlDatabaseContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DATASOURCE_URL", container.getJdbcUrl());
        System.setProperty("DATASOURCE_USERNAME", container.getUsername());
        System.setProperty("DATASOURCE_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}

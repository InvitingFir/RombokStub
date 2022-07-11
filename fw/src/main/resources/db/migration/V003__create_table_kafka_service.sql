CREATE TABLE kafka_service
(
    id             BIGSERIAL PRIMARY KEY,
    input_topic    VARCHAR NOT NULL,
    output_topic   VARCHAR NOT NULL,
    broker_address VARCHAR NOT NULL
)
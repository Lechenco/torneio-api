package io.github.lechenco.tier.infraestructure.configs.dynamodb;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aws.dynamodb", ignoreUnknownFields = false)
public class AwsDynamoProperties {
    private String accessKey = "teste";
    private String secretKey = "teste";
    private String region = "us-east-1";
    private String endpoint = "http://localhost:4566";

    public String getRegion() {
        return region;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getAccessKey() {
        return accessKey;
    }
}

package com.zen.ZenServer.global.client.gpt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "chatgpt")
public class GptProperties {
    private String authorization;
    private String bearer;
    private String model;
    private Integer maxToken;
    private Boolean stream;
    private String role;
    private Double temperature;
    private String mediaType;
    private String url;
}

package com.zen.ZenServer.global.config;

import com.zen.ZenServer.global.client.gpt.GptProperties;
import org.springframework.context.annotation.Configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Configuration
@EnableConfigurationProperties(GptProperties.class)
public class GptConfig {
}

/*
@Configuration
public class GptConfig {

    @Value("${openai.api.key}")
    private String openAiApiKey;

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate template = new RestTemplate();
        template.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add(
                    "Authorization"
                    ,"Bearer "+openAiApiKey);
            return execution.execute(request,body);
        });

        return template;

    }
}

@Configuration
public class ChatGptConfig {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String CHAT_MODEL = "gpt-3.5-turbo";
    public static final Integer MAX_TOKEN = 300;
    public static final Boolean STREAM = false;
    public static final String ROLE = "user";
    public static final Double TEMPERATURE = 0.6;
    //public static final Double TOP_P = 1.0;
    public static final String MEDIA_TYPE = "application/json; charset=UTF-8";
    //completions : 질답
    public static final String CHAT_URL = "https://api.openai.com/v1/chat/completions";
}

 */

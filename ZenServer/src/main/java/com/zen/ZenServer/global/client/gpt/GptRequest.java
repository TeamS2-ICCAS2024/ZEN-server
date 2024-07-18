package com.zen.ZenServer.global.client.gpt;

import java.util.List;

public record GptRequest(
        String model,
        List<Message> messages,
        double temperature,
        int max_tokens,
        boolean stream
) {
}

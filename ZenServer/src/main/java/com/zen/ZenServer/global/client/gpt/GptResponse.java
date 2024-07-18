package com.zen.ZenServer.global.client.gpt;

import java.util.List;

public record GptResponse(
        String id,
        String object,
        long created,
        String model,
        Usage usage,
        List<Choice> choices
) {
    public record Usage(
            int promptTokens,
            int completionTokens,
            int totalTokens
    ) {}

    public record Choice(
            Message message,
            String finishReason,
            int index
    ) {}
}

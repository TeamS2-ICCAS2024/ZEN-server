package com.zen.ZenServer.global.client.gpt;

import com.zen.ZenServer.api.emotionDiary.dto.EmotionDiaryPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GptService {

    private final GptProperties gptProperties;
    private final WebClient webClient;

    public String getAnswer(EmotionDiaryPostRequest request) {
        String userInput = request.userInput();

        // GPT-3.5 Turbo API 호출
        GptRequest gptRequest = new GptRequest(
                gptProperties.getModel(),
                List.of(new Message(gptProperties.getRole(), userInput)),
                gptProperties.getTemperature(),
                gptProperties.getMaxToken(),
                false
        );

        GptResponse gptResponse = createCompletion(gptRequest);

        // GPT-3.5 응답에서 첫 번째 선택지를 반환
        return gptResponse.choices().get(0).message().content().trim();
    }

    public GptResponse createCompletion(GptRequest gptRequest) {
        try {
            return webClient.post()
                    .uri(gptProperties.getUrl())
                    .header("Authorization", gptProperties.getBearer() + gptProperties.getAuthorization())
                    .header("Content-Type", gptProperties.getMediaType())
                    .bodyValue(gptRequest)
                    .retrieve()
                    .bodyToMono(GptResponse.class)
                    .block();
        } catch (WebClientResponseException e) {
            // 로그에 상세 오류 메시지를 기록합니다.
            System.err.println("Error: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
            throw e;
        } catch (Exception e) {
            // 기타 예외 처리
            e.printStackTrace();
            throw new RuntimeException("An error occurred while creating completion request", e);
        }
    }
}

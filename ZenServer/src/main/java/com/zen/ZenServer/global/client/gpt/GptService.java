package com.zen.ZenServer.global.client.gpt;

import com.zen.ZenServer.api.emotionDiary.dto.EmotionDiaryPostRequest;
import com.zen.ZenServer.global.exception.CustomException;
import com.zen.ZenServer.global.response.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
@RequiredArgsConstructor
@Slf4j
public class GptService {

    private final GptProperties gptProperties;
    private final WebClient webClient;

    private static final String ADDITIONAL_PROMPT = " Based on this, we write a diary that gives feedback in 250-character perfectly constructed sentences.";
    private static final int MAX_GPT_ANSWER_LENGTH = 250; // 최대 글자 수

    public String getAnswer(EmotionDiaryPostRequest request) {
        String userInput = request.userInput() + ADDITIONAL_PROMPT;

        // GPT-3.5 Turbo API 호출
        GptRequest gptRequest = new GptRequest(
                gptProperties.getModel(),
                List.of(new Message(gptProperties.getRole(), userInput)),
                gptProperties.getTemperature(),
                gptProperties.getMaxToken(),
                gptProperties.getStream()
        );

        GptResponse gptResponse = createCompletion(gptRequest);

        // GPT-3.5 응답에서 첫 번째 선택지를 반환하고, 글자 수 제한을 적용한 후, 문장 단위로 자릅니다.
        String gptAnswer = gptResponse.choices().get(0).message().content().trim();
        return truncateToLastSentenceWithinMaxLength(gptAnswer);
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
            throw new CustomException(ErrorCode.GPT_SERVER_ERROR);
        }
    }

    private String truncateToLastSentenceWithinMaxLength(String message) {
        // 먼저 글자 수를 제한합니다.
        if (message.length() > MAX_GPT_ANSWER_LENGTH) {
            message = message.substring(0, MAX_GPT_ANSWER_LENGTH);
            log.info("글자 수를 250자로 제한하여 자름: {}", message);
        }

        // 제한된 글자 수 내에서 문장 단위로 자릅니다.
        List<String> sentences = splitIntoSentences(message);
        StringBuilder truncatedMessage = new StringBuilder();

        for (String sentence : sentences) {
            if (truncatedMessage.length() + sentence.length() + 1 > MAX_GPT_ANSWER_LENGTH) {
                break;
            }
            if (truncatedMessage.length() > 0) {
                truncatedMessage.append(". ");
            }
            truncatedMessage.append(sentence);
        }
        truncatedMessage.append(".");
        log.info("응답 message: {}", truncatedMessage.toString());
        return truncatedMessage.toString();
    }

    private List<String> splitIntoSentences(String text) {
        // 문장 구분 기호를 기준으로 텍스트를 분할
        StringTokenizer tokenizer = new StringTokenizer(text, ".!?");
        List<String> sentences = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            String sentence = tokenizer.nextToken().trim();
            if (!sentence.isEmpty()) {
                sentences.add(sentence);
            }
        }
        return sentences;
    }
}

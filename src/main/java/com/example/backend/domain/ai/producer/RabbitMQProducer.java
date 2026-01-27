package com.example.backend.domain.ai.producer;

import com.example.backend.domain.ai.dto.AnalysisRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQProducer {
    private final RabbitTemplate rabbitTemplate;

    // 인프라 중심 아키텍처입니다
    // definition.js 파일의 설정을 기반으로 연결합니다
    private final String EXCHANGE_NAME = "ai.exchange";
    private final String ROUTING_KEY = "ai.job";

    public void sendJob(Long feedbackId, String filePath, String script) {
        AnalysisRequestDto message = new AnalysisRequestDto(filePath, feedbackId, script);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
        System.out.println("[Spring] 메시지 전송 완료!");
    }
}

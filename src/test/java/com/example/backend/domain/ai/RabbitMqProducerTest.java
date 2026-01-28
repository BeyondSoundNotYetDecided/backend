package com.example.backend.domain.ai;

import com.example.backend.domain.ai.service.RabbitMQProducer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // 스프링 컨테이너를 진짜로 띄워서 테스트 (RabbitMQ 연결됨)
class RabbitMqProducerTest {

    @Autowired
    private RabbitMQProducer rabbitMqProducer;

    @Test
    @DisplayName("RabbitMQ 메시지 전송 테스트")
    void testSendJob() {
        // 1. 가짜 데이터 생성
        Long feedbackId = 777L;
        String filePath = "/app/uploads/test_audio.wav";
        String script = "Hello RabbitMQ Test";

        // 2. 메시지 전송
        System.out.println("[테스트] 메시지 전송 시작...");
        rabbitMqProducer.sendJob(feedbackId, filePath, script);
        System.out.println("[테스트] 메시지 전송 요청 끝!");

        // 주의: 이건 비동기 전송이라 '전송 요청 끝'이 떴다고 해서
        // RabbitMQ에 100% 도착했다는 보장은 관리자 페이지에서 확인해야 합니다.
    }
}

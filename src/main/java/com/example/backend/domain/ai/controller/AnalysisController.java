package com.example.backend.domain.ai.controller;

import com.example.backend.domain.ai.dto.AnalysisRequestDto;
import com.example.backend.domain.ai.producer.RabbitMQProducer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AI 분석 API", description = "RabbitMQ 테스트용 API")
@RestController
@RequestMapping("/api/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private final RabbitMQProducer rabbitMqProducer;

    @Operation(summary = "RabbitMQ 메시지 전송 테스트")
    @PostMapping("/test")
    public ResponseEntity<String> sendTestMessage(@RequestBody AnalysisRequestDto requestDto) {

        // 1. 프로듀서 호출 (메시지 전송)
        rabbitMqProducer.sendJob(
                requestDto.getFeedbackId(),
                requestDto.getFilePath(),
                requestDto.getScript()
        );

        return ResponseEntity.ok("✅ RabbitMQ로 메시지 전송 성공! (관리자 페이지 확인해보세요)");
    }
}
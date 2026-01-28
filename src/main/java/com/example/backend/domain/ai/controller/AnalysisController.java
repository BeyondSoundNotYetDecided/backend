package com.example.backend.domain.ai.controller;

import com.example.backend.domain.ai.dto.AnalysisRequestDto;
import com.example.backend.domain.ai.producer.RabbitMQProducer;
import com.example.backend.global.infra.file.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "AI 분석 API", description = "RabbitMQ 테스트용 API")
@RestController
@RequestMapping("/api/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private final RabbitMQProducer rabbitMqProducer;
    private final FileService fileService;

    @Operation(summary = "음성 파일 제출 및 분석 요청", description = "음성 파일을 업로드하고 AI 분석을 요청합니다.")
    @PostMapping(
            value = "/submit",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> testUpload(@RequestParam("file") MultipartFile file) {
        String savedName = fileService.saveFile(file);
        return ResponseEntity.ok("저장 성공: " + savedName);
    }

    @Operation(summary = "RabbitMQ 메시지 전송 테스트")
    @PostMapping("/test")
    public ResponseEntity<String> sendTestMessage(@RequestBody AnalysisRequestDto requestDto) {

        // 프로듀서 호출 (메시지 전송)
        rabbitMqProducer.sendJob(
                requestDto.getFeedbackId(),
                requestDto.getFilePath(),
                requestDto.getScript()
        );

        return ResponseEntity.ok("✅ RabbitMQ로 메시지 전송 성공! (관리자 페이지 확인해보세요)");
    }
}
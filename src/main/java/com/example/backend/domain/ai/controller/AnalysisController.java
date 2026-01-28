package com.example.backend.domain.ai.controller;

import com.example.backend.domain.ai.dto.AnalysisRequestDto;
import com.example.backend.domain.ai.producer.RabbitMQProducer;
import com.example.backend.global.infra.file.FileService;
import com.example.backend.global.userdetails.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

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
    public ResponseEntity<String> testUpload(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam("curriculumId") Long curriculumId,
            @RequestPart("file") MultipartFile file
    ) {
        // 파일저장
        String savedFileName = fileService.saveFile(file);

        // 데이터 조회
        // TODO : 정답데이터, 유저 데이터 등등 DTO로 조립
        // DB 조회 대신 하드코딩된 정답 데이터(Map) 생성
        Map<String, Object> guideData = new HashMap<>();
        guideData.put("fullText", "I like to dance");
        List<Map<String, Object>> segments = new ArrayList<>();
        Map<String, Object> word1 = new HashMap<>();
        word1.put("word", "I");
        word1.put("cipa", List.of("a", "ɪ"));
        word1.put("ckor", "아이");
        word1.put("cpl", List.of("VOWEL", "VOWEL"));
        segments.add(word1);
        Map<String, Object> word2 = new HashMap<>();
        word2.put("word", "like");
        word2.put("cipa", List.of("l", "a", "ɪ", "k"));
        word2.put("ckor", "라이크");
        word2.put("cpl", List.of("CONSONANT", "VOWEL", "VOWEL", "CONSONANT"));
        segments.add(word2);
        guideData.put("segments", segments);
        AnalysisRequestDto request = AnalysisRequestDto.builder()
                .metadata(AnalysisRequestDto.Metadata.builder()
                        .requestId("TEST_REQ_" + UUID.randomUUID().toString().substring(0, 8))
                        .userId(1L)       // ★ 테스트용 유저 ID
                        .curriculumId(101L) // ★ 테스트용 커리큘럼 ID
                        .timestamp(LocalDateTime.now().toString())
                        .build())
                .userInput(AnalysisRequestDto.UserInput.builder()
                        .fileName(savedFileName)
                        .audioFormat("wav")
                        .build())
                .guideData(guideData)
                .build();

        // 프로듀서 호출 (메시지 전송)
        rabbitMqProducer.sendJob(request);

        return ResponseEntity.ok("저장 성공: " + savedFileName);
    }

}
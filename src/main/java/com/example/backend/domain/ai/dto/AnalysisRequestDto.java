package com.example.backend.domain.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AnalysisRequestDto {
    private String filePath;
    private Long feedbackId;
    private String script;
}

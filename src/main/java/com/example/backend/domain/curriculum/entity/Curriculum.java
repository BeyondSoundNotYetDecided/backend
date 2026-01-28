package com.example.backend.domain.curriculum.entity;

import com.example.backend.domain.ai.dto.AnalysisRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "curriculum")
public class Curriculum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curriculum_id")
    private Long id;

    // "SENTENCE", "WORD", "IPA" 값을 안전하게 관리하기 위해 Enum 사용
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurriculumType type;

    @Column(nullable = false)
    private String text;    // 영어 본문 (예: "Apple", "I like you")

    private String meaning; // 한글 뜻 (예: "사과", "나는 너를 좋아해")

    // 화면에 단순 보여주기용 (발음기호, 한글발음)
    private String ipa;
    private String korPronunciation;

    /**
     * ★ 수정됨: Map 대신 ScriptInfo 객체로 바로 매핑!
     * DB에는 JSON 문자열로 저장되지만,
     * 자바에서 꺼낼 때는 자동으로 예쁜 ScriptInfo 객체가 됨.
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private AnalysisRequestDto.ScriptInfo analysisData;
}

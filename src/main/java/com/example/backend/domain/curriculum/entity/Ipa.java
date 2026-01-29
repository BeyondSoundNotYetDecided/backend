package com.example.backend.domain.curriculum.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ipa")
public class Ipa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol; // 발음 기호 (예: æ, ŋ)

    private String type;   // VOWEL, CONSONANT (Enum으로 해도 됨)
}

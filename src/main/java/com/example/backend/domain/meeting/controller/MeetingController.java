package com.example.backend.domain.meeting.controller;

import com.example.backend.domain.meeting.dto.SessionCreateRequest;
import com.example.backend.domain.meeting.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/meetings")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // 프론트엔드(3000번) 접속 허용
public class MeetingController {
    
    private final MeetingService meetingService;

    // 방 생성 API
    @PostMapping("/sessions")
    public ResponseEntity<String> initializeSession(@RequestBody(required = false) SessionCreateRequest request) {
        String sessionId = (request != null) ? request.getCustomSessionId() : null;

        try {
            String createdSessionId = meetingService.createSession(sessionId);
            return ResponseEntity.ok(createdSessionId);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating session: " + e.getMessage());
        }
    }    
}

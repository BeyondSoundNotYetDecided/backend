package com.example.backend.domain.meeting.service;

import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.Session;
import io.openvidu.java.client.SessionProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MeetingService {
    
    private final OpenVidu openVidu; // Bean 주입

    public String createSession(String customSessionId)
            throws OpenViduJavaClientException, OpenViduHttpException {

        // 방 설정 (녹화 여부 등 옵션 설정 가능)
        Map<String, Object> params = new HashMap<>();
        if (customSessionId != null && !customSessionId.isEmpty()) {
            params.put("customSessionId", customSessionId);
        }
        SessionProperties properties = SessionProperties.fromJson(params).build();

        try {
            // OpenVidu 서버에 세션(회의실) 생성 요청
            Session session = this.openVidu.createSession(properties);
            return session.getSessionId(); // 생성된 세션(방) ID 반환
        } catch (OpenViduHttpException e) {
            // 409 Conflict: 이미 존재하는 세션 ID인 경우
            if (e.getStatus() == 409) {
                // 기존 세션 ID를 그대로 사용하도록 반환
                return customSessionId;
            }
            // 다른 에러는 그대로 던짐
            throw e;
        }
    }
}
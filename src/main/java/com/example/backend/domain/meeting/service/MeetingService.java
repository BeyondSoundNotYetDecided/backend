package com.example.backend.domain.meeting.service;

import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.Session;
import io.openvidu.java.client.SessionProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.backend.domain.meeting.dto.SessionCreateRequest;

@Service
@RequiredArgsConstructor
public class MeetingService {
    
    private final OpenVidu openVidu; // Bean 주입

    public String createSession(SessionCreateRequest request) // 1. DTO를 파라미터로 받음
            throws OpenViduJavaClientException, OpenViduHttpException {

        // 2. DTO에서 customSessionId 추출
        String customSessionId = request.getCustomSessionId();

        // 3. 방 설정
        // customSessionId가 있으면 넣고, 없으면 랜덤 생성
        SessionProperties properties = new SessionProperties.Builder()
                .customSessionId(customSessionId)
                .build();

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
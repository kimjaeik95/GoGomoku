package com.example.GoGomoku.websocket.config;

import com.example.GoGomoku.websocket.StompPrincipal;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

/**
 * packageName    : com.example.GoGomoku.websocket.config
 * fileName       : WebSocketEventListener
 * author         : JAEIK
 * date           : 3/31/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/31/25       JAEIK       최초 생성
 */
@Component
public class CustomHandshakeHandler extends DefaultHandshakeHandler {


    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {
        String sessionId = UUID.randomUUID().toString();
        return new StompPrincipal(sessionId);
    }
}

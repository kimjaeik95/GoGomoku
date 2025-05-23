package com.example.GoGomoku.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * packageName    : com.example.GoGomoku.websocket
 * fileName       : config
 * author         : JAEIK
 * date           : 3/24/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/24/25       JAEIK       최초 생성
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
       registry.addEndpoint("/api/ws-stomp")
               .setAllowedOriginPatterns("*")
               .setHandshakeHandler(new CustomHandshakeHandler())
               .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 서버가 구독자들에게 메시지 전달
        registry.enableSimpleBroker("/topic", "/queue");
        // user 전달  1:1 을 위해
        registry.setUserDestinationPrefix("/user");
        // 클라이언트 /pub  경로로 메시지 보낸다.
        registry.setApplicationDestinationPrefixes("/pub");
    }
}

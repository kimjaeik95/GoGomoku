package com.example.GoGomoku.websocket;

import javax.security.auth.Subject;
import java.security.Principal;

/**
 * packageName    : com.example.GoGomoku.websocket
 * fileName       : StompPrincipal
 * author         : JAEIK
 * date           : 3/31/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/31/25       JAEIK       최초 생성
 */
public class StompPrincipal implements Principal {
    String name;

    public StompPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}

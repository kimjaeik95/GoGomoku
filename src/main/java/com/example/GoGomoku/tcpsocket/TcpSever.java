package com.example.GoGomoku.tcpsocket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * packageName    : com.example.GoGomoku.tcpsocket
 * fileName       : SeverSocket
 * author         : JAEIK
 * date           : 3/21/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/21/25       JAEIK       최초 생성
 */
@Slf4j
public class TcpSever {
    
    private ServerSocket severSocket;
    private final int port = 8080;


    /*
    SeverSocket 객체에 포트번호를 연결하고 클라이언트 연결 대기
    clientSocket 을 serverSocket 수락한다.
    클라이언트 오류가발생하면 catch 로 오류를 잡고 break 하고
    예외가 발생하든 발생안하든 finally 블록 실행되면서 소켓을 닫는다.
    (소켓연결을 안전하게 해제하고 예외처리는 상위 계층에서 예외처리하도록)
     */
    public void startServer() {
        try {
            severSocket = new ServerSocket(port);
            log.info("서버가 실행되었습니다. 포트 : {}", port);

            while (true) {
                try {
                    Socket clientSocket = severSocket.accept();
                    log.info("클라이언트가 연결되었습니다: " + clientSocket.getInetAddress());
                } catch (IOException e) {
                    log.error("클라이언트 연결 중 오류 발생", e);
                    break;

                }
            }
        } catch (IOException e) {
            log.error("서버 연결 중 오류발생");
            throw new RuntimeException(e);
        } finally {
            try {
                severSocket.close();
                log.info("연결 종료 ");
            } catch (IOException e) {
                e.printStackTrace();
                /* 소켓을 닫을때 예외를 안 던져도 되는이유
                소켓을 닫는 중에 발생한 예외는 단순히 로그로만 처리할수있고
                소켓을 닫는 작업은 정리작업에 속하기때문에
                프로그램 흐름 영향에 크게 큰영향을 안주기때문에
                 */
            }
        }
    }
}

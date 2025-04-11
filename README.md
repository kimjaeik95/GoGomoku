# ⚫ GoGomoku(1:1 오목게임)⚪
## 간단하게 바로 즐길 수 있는 1:1 오목 게임 ! - 실시간 매칭과 턴 진행이 가능한 미니 웹 게임!
## 프로젝트 소개
- 로그인 없이 즉시 플레이 1:1 실시간 오목 게임  웹 브라우저에서 즐길 수 있습니다.
- 자동 매칭 시스템 서버 대기열을 통해 두 명씩 순차적으로 자동 매칭됩니다.
- WebSocket 기반의 구조로, 동시에 여러 개의 게임방이 생성되어 실시간 플레이가 가능합니다.
- 돌 두기, 턴 전환, 승패/무승부 판단까지 전부 실시간 처리됩니다.
- SSR 기반 구조 서버에서 HTML을 렌더링해 별도 프론트 빌드 없이도 바로 동작합니다.
## 개발 기간
전체 개발 기간 : 2024-03 ~ 2024-03
## 주요 기능
- **세션 기반 자동 매칭**
로그인 없이 UUID로 사용자를 식별하고, 서버 측 대기열(Queue)을 이용해 순차적으로 두 명씩 매칭하며 실시간으로 게임을 시작합니다.
- **WebSocket 기반 실시간 게임 진행**
    
    WebSocket 통신을 활용해, 게임 시작 시마다 고유한 게임 방이 생성되는 멀티룸 구조를 구현했습니다.
     실시간 돌 두기, 턴 전환, 게임 종료까지 모두 실시간으로 전송되며,  오목돌의 특성인 사용자 간 1:1 메시징도 지원합니다.
    
- **턴 기반 게임 로직 처리**
    
    흑백 돌의 차례를 홀짝 턴으로 자동 판단하고, 잘못된 행동 시 예외 처리
    
- **게임 결과 판정**
    
    돌을 둘 때마다 8방향 탐색 알고리즘을 통해 승리 조건(같은 색 돌 5개 연속)을 체크하며, 전체 판이 가득 찬 경우는 무승부 처리
    
- **SSR 방식 렌더링**
    
    클라이언트에서 별도 빌드 없이 서버에서 HTML을 렌더링하여 간단하고 빠른 초기 로딩 속도 구조로 구현 하였다.
    
- **JavaScript 비동기 처리**
사용자 이벤트(돌 두기, 턴 전환 등)를 처 비동기적으로 처리하고, WebSocket으로  서버와 실시간 통신 게임상태를 DOM 조작방식으로 즉시 반영하여 자연스러운 사용자 경험 제공

## 기술 스택
<div align="center">

|      Type       |                                                                                                                  Tool                                                                                                                   |
| :-------------: | :-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
|    Language     |                                                     <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white">  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white"> <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=Thymeleaf&logoColor=white"> <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=HTML5&logoColor=white">                     |
|    Framework    |<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">                   
|       DB        |                   <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">  <img src="https://img.shields.io/badge/flyway-CC0200?style=for-the-badge&logo=java&logoColor=white">
|     DevOps      |     <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=java&logoColor=white"> 
|    WebSocket    | <img src="https://img.shields.io/badge/SockJS-000000?style=for-the-badge&logo=sockjs&logoColor=white"> <img src="https://img.shields.io/badge/STOMP-FF6699?style=for-the-badge&logo=stomp&logoColor=white">
|  Collaboration  |           ![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white)              |
</div>

## 회고
프로젝트 진행 과정에서 경험한 팀워크와 개발자로서의 자세, 기술적 고민들에 대해여
---
이번 프로젝트에서는 프론트엔드 없이, 서버만으로 SSR 구조를 구현하며 최소한의 구조로 최대한의 실시간 게임을 제공하는 방법을 고민 했습니다.
게임이 단순한 형태라고 해서 로직이 단순한 것은 아니었습니다. 
오목이라는 게임의 특성상, 실시간 턴 처리 , 실시간 승패 판정을 위한 탐색 기반의 알고리즘까지 다양한 논리적 생각이 필요했습니다.  이 과정에서 단순히 요청-응답 구조를 넘어서  알고리즘적 사고의 중요성을 깊이 느꼈습니다.  또한 프론트엔드 없이도 사용자에게 자연스러운 웹 서비스를 제공할 수 있다는 자신감을 얻을 수 있었습니다.

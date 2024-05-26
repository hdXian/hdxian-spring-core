package hdxian.hdxianspringcore.lifecycle;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// 어플리케이션 시작 시 네트워크 연결을 수행하는 클래스라고 가정
public class NetworkClient { // implements BeanInitializing, DisposableBean // 방법 1

    private String url;

    public NetworkClient() {
        System.out.println("in Constructor()");
        System.out.println("생성자 호출: url = " + url);
        System.out.println("out Constructor()");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 빈 생성 시 수행할 동작
    public void connect() {
        System.out.println("connect(): " + url);
    }

    public void call(String message) {
        System.out.println("call(): " + url + ", message = " + message);
    }

    // 빈 소멸 직전 수행할 동작
    public void disconnet() {
        System.out.println("disconnet(): " + url);
    }

    @PostConstruct // 방법 3 - 의존성 주입 직후 실행되는 초기화 메서드로 지정
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("연결 초기화 메시지");
    }

    @PreDestroy // 방법 3 - 빈 소멸 직전 실행되는 종료 메서드로 지정
    public void close() {
        System.out.println("NetworkClient.close");
        disconnet();
    }

}

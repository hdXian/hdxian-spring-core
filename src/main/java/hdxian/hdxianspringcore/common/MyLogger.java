package hdxian.hdxianspringcore.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;

    private String requestURL;

    public void setRequestURL(String requestURL) {
        // URL은 빈 생성 시점에는 알 수 없으므로 외부에서 setter로 주입
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "][" + requestURL + "] " + message);
    }

    @PostConstruct // HTTP 요청 발생 시 빈이 생성되어 생성 메서드 호출
    public void init() {
        uuid = UUID.randomUUID().toString(); // UUID 라이브러리를 이용하여 uuid 생성
        System.out.println("[" + uuid + "] request scope bean created: " + this);
    }

    @PreDestroy // HTTP 요청 만료 시 빈이 소멸되어 소멸 메서드 호출
    public void close() {
        System.out.println("[" + uuid + "[ request scope bean closed: " + this);
    }

}

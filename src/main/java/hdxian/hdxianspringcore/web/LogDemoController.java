package hdxian.hdxianspringcore.web;

import hdxian.hdxianspringcore.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;

//    private final ObjectProvider<MyLogger> myLoggerProvider; // MyLogger타입 빈을 LookUp해주는 Provider를 주입받는다.
    private final MyLogger myLogger;


    @RequestMapping("log-demo") // log-demo 경로로 접근하는 요청을 처리함
    @ResponseBody // 그냥 응답 http body에 문자열 박아넣음
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();

        // myLogger 빈이 필요한 지금 시점에 LookUp하여 빈을 받아온다.
//        MyLogger myLogger = myLoggerProvider.getObject();

        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
//        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }

}

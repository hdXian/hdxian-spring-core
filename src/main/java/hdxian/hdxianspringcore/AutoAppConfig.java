package hdxian.hdxianspringcore;

import hdxian.hdxianspringcore.member.MemberRepository;
import hdxian.hdxianspringcore.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 컴포넌트 스캔을 이용해 자동으로 스프링 빈을 등록하도록 설정
@Configuration
@ComponentScan(
        // 컴포넌트 스캔 대상에서 @Configuration이 붙은 클래스를 제외한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
//        useDefaultFilters = true 기본 설정이 되어있음. false로 지정하면 기본 스캔 대상들이 제외됨.
)
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository") // 빈 이름이 충돌할 경우 수동으로 등록한 빈으로 덮어씌워진다.
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}

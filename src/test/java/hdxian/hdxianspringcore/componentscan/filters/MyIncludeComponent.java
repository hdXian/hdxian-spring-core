package hdxian.hdxianspringcore.componentscan.filters;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // 클래스에 붙이는 어노테이션임을 의미
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {

}

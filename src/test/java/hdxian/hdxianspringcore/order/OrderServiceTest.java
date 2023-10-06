package hdxian.hdxianspringcore.order;

import hdxian.hdxianspringcore.member.Grade;
import hdxian.hdxianspringcore.member.Member;
import hdxian.hdxianspringcore.member.MemberService;
import hdxian.hdxianspringcore.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();


    @Test
    void createOrder() {
        // given
        // 원시 타입에는 null을 넣을 수 없어서 Long 타입을 씀.
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // when
        Order order = orderService.createOrder(memberId, "itemName", 10000);

        // then
        // 할인액이 1000원이 맞는지 테스트
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }

}

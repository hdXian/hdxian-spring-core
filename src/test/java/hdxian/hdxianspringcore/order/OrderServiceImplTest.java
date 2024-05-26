package hdxian.hdxianspringcore.order;

import hdxian.hdxianspringcore.discount.FixDiscountPolicy;
import hdxian.hdxianspringcore.member.Grade;
import hdxian.hdxianspringcore.member.Member;
import hdxian.hdxianspringcore.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository repository = new MemoryMemberRepository();
        repository.save(new Member(1L, "memberA", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(repository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);

        assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }

}
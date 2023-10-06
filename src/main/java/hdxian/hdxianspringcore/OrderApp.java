package hdxian.hdxianspringcore;

import hdxian.hdxianspringcore.member.Grade;
import hdxian.hdxianspringcore.member.Member;
import hdxian.hdxianspringcore.member.MemberService;
import hdxian.hdxianspringcore.member.MemberServiceImpl;
import hdxian.hdxianspringcore.order.Order;
import hdxian.hdxianspringcore.order.OrderService;
import hdxian.hdxianspringcore.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(1L, "itemA", 10000);

        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice());

    }

}

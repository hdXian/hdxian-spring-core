package hdxian.hdxianspringcore.discount;

import hdxian.hdxianspringcore.member.Member;

public interface DiscountPolicy {

    // 할인 액수 리턴
    int discount(Member member, int price);

}

package hdxian.hdxianspringcore.discount;

import hdxian.hdxianspringcore.member.Grade;
import hdxian.hdxianspringcore.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    // 할인 액수는 1000원
    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }
        else {
            return 0;
        }
    }

}

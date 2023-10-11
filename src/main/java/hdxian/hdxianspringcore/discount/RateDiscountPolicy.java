package hdxian.hdxianspringcore.discount;

import hdxian.hdxianspringcore.member.Grade;
import hdxian.hdxianspringcore.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {

    // 할인률은 10퍼센트
    private int discountPercent = 10;

    // 회원 등급이 VIP일 경우 10% 할인
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        }
        else {
            return 0;
        }
    }

}

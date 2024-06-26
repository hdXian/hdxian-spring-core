package hdxian.hdxianspringcore.order;

import hdxian.hdxianspringcore.annotation.MainDiscountPolicy;
import hdxian.hdxianspringcore.discount.DiscountPolicy;
import hdxian.hdxianspringcore.member.Member;
import hdxian.hdxianspringcore.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;

    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

//    @Autowired // setter 주입 방식
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    //    @Autowired // 일반 메서드 주입 방식
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // OrderService는 할인에 관여하지 않고 discountPolicy에 member정보를 넘기기만 한다.
        // 단일 책임 원칙이 잘 지켜진 사례. 할인 정책이 변경되어도 OrderService는 변화가 없음.
        int discountPrice = discountPolicy.discount(member, itemPrice); // discount()는 할인 액수를 리턴함.

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return this.memberRepository;
    }

}

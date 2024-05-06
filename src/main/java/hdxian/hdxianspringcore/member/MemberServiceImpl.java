package hdxian.hdxianspringcore.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class MemberServiceImpl implements MemberService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    // memberRepository에 들어갈 구현체를 생성자를 통해 전달받는다.
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public MemberRepository getMemberRepository() {
        return this.memberRepository;
    }
}

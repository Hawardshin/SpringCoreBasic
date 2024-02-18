package hello.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

@Component
public class OrderServiceImpl implements OrderService{
     private final MemberRepository memberRepository;
     private final DiscountPolicy discountPolicy ;



    // ///수정자 주입
    // @Autowired
    // public void setDiscountPolicy(DiscountPolicy discountPolicy) {
    //     // System.out.println("discountPolicy = " + discountPolicy);
    //     this.discountPolicy = discountPolicy;
    // }
    // @Autowired(required = false)
    // public void setMemberRepository(MemberRepository memberRepository) {
    //     // System.out.println("memberRepository = " + memberRepository);
    //     this.memberRepository = memberRepository;
    // }
    // //생성자 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        // System.out.println("memberRepository = " + memberRepository + ", discountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // @Autowired
    // public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    //     this.memberRepository = memberRepository;
    //     this.discountPolicy = discountPolicy;
    // }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

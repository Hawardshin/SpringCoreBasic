package hello.core.scan;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AutoAppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.order.OrderServiceImpl;

public class AutoAppConfigTest {
	@Test
	void basicScan(){
		ApplicationContext ac = new AnnotationConfigApplicationContext(
			(AutoAppConfig.class));
		MemberService memberService = ac.getBean(MemberService.class);
		assertThat(memberService).isInstanceOf(MemberService.class);
		OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
		MemberRepository memberRepository = bean.getMemberRepository();
		System.out.println("memberRepository = " + memberRepository);
	}

	//필드 주입의 단점!
	//이렇게 하면 순수 자바 테스트 코드에서 다른 객체를 넣고 테스트 하고 싶어도 할 수 있는 방법이 없다.
	//이런 경우 Null Point Exception 발생한다.
	// @Test
	// void fieldInjectionTest(){
	// 	OrderServiceImpl orderService = new OrderServiceImpl();
	// 	orderService.createOrder(1L, "itemA", 10000);
	// }
}



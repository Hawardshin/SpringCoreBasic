package hello.core.sigleton;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;

public class ConfigurationSingletonTest {

	@Test
	void configurationTest(){
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

		MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
		MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
		System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
		System.out.println("orderService -> memberRepository = " + orderService.getMemberRepository());
		System.out.println("memberRepository = " + memberRepository);

		assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
		assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

	}

	@Test
	void configurationDeep() {
		ApplicationContext ac = new
			AnnotationConfigApplicationContext(AppConfig.class);
		//AppConfig도 스프링 빈으로 등록된다.
		AppConfig bean = ac.getBean(AppConfig.class);

		System.out.println("bean = " + bean.getClass());
		//출력: bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$bd479d70
	}
}
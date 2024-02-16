package hello.core.sigleton;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;

public class SigletonTest {
	@Test
	@DisplayName("스프링 없는 순수한 DI 컨테이너")
	void pureContainer() {
		AppConfig appConfig = new AppConfig();

		// 1. 조회: 호출할 때마다 객체를 생성
		MemberService memberService1 = appConfig.memberService();

		// 2. 조회: 호출할 때마다 객체를 생성
		MemberService memberService2 = appConfig.memberService();

		// 참조값이 다른 것을 확인
		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);

		assertThat(memberService1).isNotSameAs(memberService2);
	}

	@Test
	@DisplayName("싱글톤 패턴을 적용한 객체 사용")
	void sigletonServiceTest() {
		SigletonService sigletonService1 = SigletonService.getInstance();
		SigletonService sigletonService2 = SigletonService.getInstance();

		System.out.println("sigletonService1 = " + sigletonService1);
		System.out.println("sigletonService2 = " + sigletonService2);
		assertThat(sigletonService1).isSameAs(sigletonService2);
		//same == 비교 는 자바 ==과 같고
	}

	@Test
	@DisplayName("스프링 컨테이너와 싱글톤")
	void springContainer() {
		// AppConfig appConfig = new AppConfig();

		ApplicationContext ac = new AnnotationConfigApplicationContext(
			AppConfig.class);
		MemberService memberService1 = ac.getBean("memberService", MemberService.class);
		MemberService memberService2 = ac.getBean("memberService", MemberService.class);

		// 참조값이 다른 것을 확인
		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);

		assertThat(memberService1).isSameAs(memberService2);

	}
}

package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

//실무에서는 이렇게 안 하고 컴포넌트 스캔을 사용한다
//왜냐하면 이전 만들어둔 예제 코드를 유지하기 위해서입니다.
@Configuration
@ComponentScan(
	excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class
))
public class AutoAppConfig {
	@Bean(name = "memoryMemberRepository")
	MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
}

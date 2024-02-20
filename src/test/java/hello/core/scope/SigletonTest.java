package hello.core.scope;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class SigletonTest {

	@Test
	void sigletonBeanFind() {
		//이렇게 컴포넌트 클래스 자체를 등록해도 컴포넌트 스캔이 돼서 등록이 된다.
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

		SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
		SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
		System.out.println("singletonBean1 = " + singletonBean1);
		System.out.println("singletonBean2 = " + singletonBean2);
		assertThat(singletonBean1).isSameAs(singletonBean2);
		ac.close();
	}

	//default 가 싱글톤이라 안 적어줘도 되지만 명시적으로 보여줄 수 있다.
	@Scope("singleton")
	static class SingletonBean {
		@PostConstruct
		public void init(){
			System.out.println("SingletonBean.init");
		}

		@PreDestroy
		public void destroy(){
			System.out.println("SingletonBean.destroy");
		}
	}
}
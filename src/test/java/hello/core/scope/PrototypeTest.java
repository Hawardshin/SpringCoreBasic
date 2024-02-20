package hello.core.scope;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class PrototypeTest {

	@Test
	void prototypeBeanFind(){
		//여기에 직접적으로 넣어줘서 클래스가 @Component라고 안 적어줘도 클래스 자체가 컴포넌트 스캔의 대상이 됩니다.
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
		System.out.println("find prototypeBean1");
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		System.out.println("find prototypeBean2");
		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		System.out.println("singletonBean1 = " + prototypeBean1);
		System.out.println("singletonBean2 = " + prototypeBean2);
		assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
		prototypeBean1.destroy();
		prototypeBean2.destroy();
		ac.close();
	}

	@Scope("prototype")
	static class PrototypeBean{
		@PostConstruct
		public void init(){
			System.out.println("PrototypeBean.init");
		}


		@PreDestroy
		public void destroy(){
			System.out.println("PrototypeBean.destroy");
		}


	}
}

package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

	@Test
	public void lifeCycleTest() {
		//기존에 사용하던 ApplicationContext 객체 ac는 close() 메서드를 호출할 수 없다.
		// ApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
		// AnnotationConfigApplicationContext 또는 ConfigurableApplicationContext를 사용하면 close() 메서드를 호출할 수 있다.
		ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
		NetworkClient client = ac.getBean(NetworkClient.class);
		ac.close();  //스프링 컨테이너를 종료, ConfigurableApplicationContext 필요
	}

	@Configuration
	static class LifeCycleConfig {
		@Bean
		public NetworkClient networkClient() {
			NetworkClient networkClient = new NetworkClient();
			networkClient.setUrl("http://hello-spring.dev");
			return networkClient;
		}
	}
}

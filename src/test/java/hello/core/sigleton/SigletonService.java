package hello.core.sigleton;

public class SigletonService {
	private static final SigletonService instance = new SigletonService();

	public static SigletonService getInstance() {
		return instance;
	}

	private SigletonService() {
	}

	public void logic(){
		System.out.println("싱글톤 객체 로직 호출");
	}
}

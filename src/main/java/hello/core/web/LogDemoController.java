package hello.core.web;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
	private final LogDemoService logDemoService;
	private final ObjectProvider<MyLogger> myLoggerProvider;
	//지금 myLogger가 http scope라서 injection이 안됩니다.
	//이걸 해결하려면 Provider를 사용해야합니다.

	//보통 고객 요청 오면 view 템플릿 거쳐서 나가야하는데,
	//RequestBody 어노테이션 붙혀주면, 문자 그대로 응답을 보내줄 수 있습니다.
	@RequestMapping("log-demo")
	@ResponseBody
	public String logDemo(HttpServletRequest request){
		MyLogger myLogger = myLoggerProvider.getObject();
		String requestURL = request.getRequestURL().toString();
		myLogger.setRequestURL(requestURL);

		myLogger.log("controller test");
		logDemoService.logic("testId");
		return "OK";
	}
}

package hello.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;

//Qualifier 에서 복붙함
//필드나 메서드 파라미터 타입 등 다 붙힐 수 있도록 함
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")  //실수로 mainDiscountPolicy 가 아닌 다른 이름을 넣으면 컴파일 오류가 난다. (이전에 사용했던 방식은 문자여서 런타임중 오류가 날 수 있다.)
public @interface MainDiscountPolicy {

}

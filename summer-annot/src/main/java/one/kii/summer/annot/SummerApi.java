package one.kii.summer.annot;

import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

/**
 * Created by WangYanJiong on 02/04/2017.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@Inherited
public @interface SummerApi {

    String value() default "";
}

package one.kii.summer.annot;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * Created by WangYanJiong on 02/04/2017.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
@Inherited
public @interface SummerSpi {

    String value() default "";
}

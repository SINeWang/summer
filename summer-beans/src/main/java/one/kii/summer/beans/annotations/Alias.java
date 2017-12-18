package one.kii.summer.beans.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Alias {

    /**
     * @return another name under specified namespace
     */
    String name();

    /**
     * @return Namespace
     */
    String space() default "";

}

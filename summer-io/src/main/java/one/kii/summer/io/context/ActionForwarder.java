package one.kii.summer.io.context;

import one.kii.summer.io.annotations.OwnerId;
import one.kii.summer.io.exception.BadRequest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by WangYanJiong on 20/04/2017.
 */
public abstract class ActionForwarder {

    protected static String findOwnerIdFromObject(Object target) throws BadRequest {
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(OwnerId.class);
            if (annotation != null) {
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(target);
                } catch (IllegalAccessException e) {
                    throw new BadRequest(OwnerId.FIELD_NAME);
                }
                if (value != null) {
                    return value.toString();
                }
            }
        }
        throw new BadRequest(OwnerId.FIELD_NAME);
    }

}

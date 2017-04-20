package one.kii.summer.io.context;

import one.kii.summer.io.annotations.OwnerId;
import one.kii.summer.io.exception.BadRequest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.UUID;

/**
 * Created by WangYanJiong on 19/04/2017.
 */
public class ReadForwarder extends ActionForwarder {

    public static ReadContext from(WriteContext context, String targetOwnerId) throws BadRequest {
        if (targetOwnerId == null) {
            throw new BadRequest(OwnerId.FIELD_NAME);
        }
        ReadContext readContext = new ReadContext(
                UUID.randomUUID().toString(),
                targetOwnerId,
                context.getOperatorId());
        readContext.referId = context.getProcessId();
        return readContext;

    }

    public static ReadContext from(ReadContext context, String targetOwnerId) throws BadRequest {
        if (targetOwnerId == null) {
            throw new BadRequest(OwnerId.FIELD_NAME);
        }
        ReadContext readContext = new ReadContext(
                UUID.randomUUID().toString(),
                targetOwnerId,
                context.getVisitorId());
        readContext.referId = context.getProcessId();
        return readContext;
    }

    public static ReadContext from(ReadContext context, Object target) throws BadRequest {
        String ownerId = findOwnerIdFromObject(target);
        ReadContext readContext = new ReadContext(
                UUID.randomUUID().toString(),
                ownerId,
                context.getVisitorId());
        readContext.referId = context.getProcessId();
        return readContext;
    }

    public static ReadContext from(WriteContext context, Object target) throws BadRequest {
        String ownerId = findOwnerIdFromObject(target);
        ReadContext readContext = new ReadContext(
                UUID.randomUUID().toString(),
                ownerId,
                context.getOperatorId());
        readContext.referId = context.getProcessId();
        return readContext;
    }

}

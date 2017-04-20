package one.kii.summer.io.context;

import one.kii.summer.io.annotations.OwnerId;
import one.kii.summer.io.exception.BadRequest;

import java.util.UUID;

/**
 * Created by WangYanJiong on 20/04/2017.
 */
public class ReadAuthorizationForwarder extends ActionForwarder {


    public static ReadAuthorizationContext from(WriteAuthorizationContext context, String targetOwnerId) throws BadRequest {
        if (targetOwnerId == null) {
            throw new BadRequest(OwnerId.FIELD_NAME);
        }
        ReadAuthorizationContext readContext = buildReadAuthorizationContext(context, targetOwnerId);
        return readContext;

    }

    public static ReadAuthorizationContext from(WriteAuthorizationContext context, Object target) throws BadRequest {
        String ownerId = findOwnerIdFromObject(target);
        ReadAuthorizationContext readContext = buildReadAuthorizationContext(context, ownerId);
        return readContext;

    }

    public static ReadAuthorizationContext from(ReadAuthorizationContext context, String targetOwnerId) throws BadRequest {
        if (targetOwnerId == null) {
            throw new BadRequest(OwnerId.FIELD_NAME);
        }
        return buildReadAuthorizationContext(context, targetOwnerId);
    }


    public static ReadAuthorizationContext from(ReadAuthorizationContext context, Object target) throws BadRequest {
        String ownerId = findOwnerIdFromObject(target);
        return buildReadAuthorizationContext(context, ownerId);
    }

    private static ReadAuthorizationContext buildReadAuthorizationContext(WriteAuthorizationContext context, String ownerId) {
        ReadAuthorizationContext readContext = new ReadAuthorizationContext(
                UUID.randomUUID().toString(),
                ownerId,
                context.getOperatorId(),
                context.getAuthorization());
        readContext.referId = context.getProcessId();
        return readContext;
    }

    private static ReadAuthorizationContext buildReadAuthorizationContext(ReadAuthorizationContext context, String ownerId) {
        ReadAuthorizationContext readContext = new ReadAuthorizationContext(
                UUID.randomUUID().toString(),
                ownerId,
                context.getVisitorId(),
                context.getAuthorization());
        readContext.referId = context.getProcessId();
        return readContext;
    }

}

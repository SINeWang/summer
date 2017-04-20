package one.kii.summer.io.context;

import one.kii.summer.io.annotations.OwnerId;
import one.kii.summer.io.exception.BadRequest;

import java.util.UUID;

/**
 * Created by WangYanJiong on 20/04/2017.
 */
public class WriteForwarder extends ActionForwarder {

    public static WriteContext from(WriteContext context, String targetOwnerId) throws BadRequest {
        if (targetOwnerId == null) {
            throw new BadRequest(OwnerId.FIELD_NAME);
        }
        return buildWriteContext(context, targetOwnerId);

    }

    public static WriteContext from(WriteAuthorizationContext context, String targetOwnerId) throws BadRequest {
        if (targetOwnerId == null) {
            throw new BadRequest(OwnerId.FIELD_NAME);
        }
        return buildWriteContext(context, targetOwnerId);

    }

    public static WriteContext from(ReadContext context, String targetOwnerId) throws BadRequest {
        if (targetOwnerId == null) {
            throw new BadRequest(OwnerId.FIELD_NAME);
        }
        return buildWriteContext(context, targetOwnerId);
    }

    public static WriteContext from(ReadAuthorizationContext context, String targetOwnerId) throws BadRequest {
        if (targetOwnerId == null) {
            throw new BadRequest(OwnerId.FIELD_NAME);
        }
        return buildWriteContext(context, targetOwnerId);
    }

    public static WriteContext from(ReadAuthorizationContext context, Object target) throws BadRequest {
        String ownerId = findOwnerIdFromObject(target);
        return buildWriteContext(context, ownerId);
    }



    public static WriteContext from(ReadContext context, Object target) throws BadRequest {
        String ownerId = findOwnerIdFromObject(target);
        return buildWriteContext(context, ownerId);
    }

    public static WriteContext from(WriteContext context, Object target) throws BadRequest {
        String ownerId = findOwnerIdFromObject(target);
        return buildWriteContext(context, ownerId);
    }


    public static WriteContext from(WriteAuthorizationContext context, Object target) throws BadRequest {
        String ownerId = findOwnerIdFromObject(target);
        return buildWriteContext(context, ownerId);

    }

    private static WriteContext buildWriteContext(ReadContext context, String ownerId) {
        WriteContext writeContext = new WriteContext(
                UUID.randomUUID().toString(),
                ownerId,
                context.getVisitorId());
        writeContext.referId = context.getProcessId();
        return writeContext;
    }

    private static WriteContext buildWriteContext(ReadAuthorizationContext context, String targetOwnerId) {
        WriteContext writeContext = new WriteContext(
                UUID.randomUUID().toString(),
                targetOwnerId,
                context.getVisitorId());
        writeContext.referId = context.getProcessId();
        return writeContext;
    }

    private static WriteContext buildWriteContext(WriteContext context, String targetOwnerId) {
        WriteContext writeContext = new WriteContext(
                UUID.randomUUID().toString(),
                targetOwnerId,
                context.getOperatorId());
        writeContext.referId = context.getProcessId();
        return writeContext;
    }

    private static WriteContext buildWriteContext(WriteAuthorizationContext context, String targetOwnerId) {
        WriteContext writeContext = new WriteContext(
                UUID.randomUUID().toString(),
                targetOwnerId,
                context.getOperatorId());
        writeContext.referId = context.getProcessId();
        return writeContext;
    }
}

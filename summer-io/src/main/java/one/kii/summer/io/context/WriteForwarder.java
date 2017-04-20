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
        WriteContext writeContext = new WriteContext(
                UUID.randomUUID().toString(),
                targetOwnerId,
                context.getOperatorId());
        writeContext.referId = context.getProcessId();
        return writeContext;

    }

    public static WriteContext from(ReadContext context, String targetOwnerId) throws BadRequest {
        if (targetOwnerId == null) {
            throw new BadRequest(OwnerId.FIELD_NAME);
        }
        WriteContext writeContext = new WriteContext(
                UUID.randomUUID().toString(),
                targetOwnerId,
                context.getVisitorId());
        writeContext.referId = context.getProcessId();
        return writeContext;
    }

    public static WriteContext from(ReadContext context, Object target) throws BadRequest {
        String ownerId = findOwnerIdFromObject(target);
        WriteContext writeContext = new WriteContext(
                UUID.randomUUID().toString(),
                ownerId,
                context.getVisitorId());
        writeContext.referId = context.getProcessId();
        return writeContext;
    }

    public static WriteContext from(WriteContext context, Object target) throws BadRequest {
        String ownerId = findOwnerIdFromObject(target);
        WriteContext writeContext = new WriteContext(
                UUID.randomUUID().toString(),
                ownerId,
                context.getOperatorId());
        writeContext.referId = context.getProcessId();
        return writeContext;
    }

}

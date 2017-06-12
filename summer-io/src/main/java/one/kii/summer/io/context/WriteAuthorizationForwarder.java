package one.kii.summer.io.context;

import one.kii.summer.io.annotations.OwnerId;
import one.kii.summer.io.exception.BadRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.UUID;

/**
 * Created by WangYanJiong on 20/04/2017.
 */
public class WriteAuthorizationForwarder extends ActionForwarder {


    public static WriteAuthorizationContext from(WriteAuthorizationContext context, String targetOwnerId) throws BadRequest {
        if (targetOwnerId == null) {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.put(OwnerId.FIELD_NAME, null);
            throw new BadRequest(map);
        }
        return buildWriteAuthorizationContext(context, targetOwnerId);

    }

    public static WriteAuthorizationContext from(WriteAuthorizationContext context, Object target) throws BadRequest {
        String ownerId = findOwnerIdFromObject(target);
        return buildWriteAuthorizationContext(context, ownerId);

    }

    public static WriteAuthorizationContext from(ReadAuthorizationContext context, String targetOwnerId) throws BadRequest {
        if (targetOwnerId == null) {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.put(OwnerId.FIELD_NAME, null);
            throw new BadRequest(map);
        }
        return buildWriteAuthorizationContext(context, targetOwnerId);
    }

    public static WriteAuthorizationContext from(ReadAuthorizationContext context, Object target) throws BadRequest {
        String ownerId = findOwnerIdFromObject(target);
        return buildWriteAuthorizationContext(context, ownerId);
    }

    private static WriteAuthorizationContext buildWriteAuthorizationContext(ReadAuthorizationContext context, String targetOwnerId) {
        WriteAuthorizationContext writeContext = new WriteAuthorizationContext(
                UUID.randomUUID().toString(),
                targetOwnerId,
                context.getVisitorId(),
                context.getAuthorization());
        writeContext.referId = context.getRequestId();
        return writeContext;
    }

    private static WriteAuthorizationContext buildWriteAuthorizationContext(WriteAuthorizationContext context, String targetOwnerId) {
        WriteAuthorizationContext writeContext = new WriteAuthorizationContext(
                UUID.randomUUID().toString(),
                targetOwnerId,
                context.getOperatorId(),
                context.getAuthorization());
        writeContext.referId = context.getRequestId();
        return writeContext;
    }

}

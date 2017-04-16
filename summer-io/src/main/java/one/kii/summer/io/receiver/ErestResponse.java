package one.kii.summer.io.receiver;

import one.kii.summer.io.context.ErestHeaders;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.UUID;

/**
 * Created by WangYanJiong on 02/04/2017.
 */
public class ErestResponse {

    private static HttpHeaders buildHttpHeaders(String requestId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(ErestHeaders.TIME, new Date().toString());
        headers.set(ErestHeaders.REQUEST_ID, requestId);
        headers.set(ErestHeaders.RESPONSE_ID, UUID.randomUUID().toString());
        return headers;
    }

    public static <T> ResponseEntity<T> badRequest(String requestId, String[] fields) {
        HttpHeaders headers = buildHttpHeaders(requestId);
        if (fields != null && fields.length > 0) {
            for(String field : fields){
                headers.add(ErestHeaders.BAD_FIELDS, field);
            }
        }
        return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }

    /**
     * For <b>WRITE</b> operations use only.
     */
    public static <T> ResponseEntity<T> created(String requestId, T object) {
        HttpHeaders headers = buildHttpHeaders(requestId);
        return new ResponseEntity<>(object, headers, HttpStatus.CREATED);
    }

    /**
     * For <b>Async Write</b> operations use only.
     */
    public static ResponseEntity accepted(String requestId) {
        HttpHeaders headers = buildHttpHeaders(requestId);
        return new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
    }

    /**
     * For <b>READ</b> operations use only.
     */
    public static <T> ResponseEntity<T> ok(String requestId, T object) {
        HttpHeaders headers = buildHttpHeaders(requestId);
        return new ResponseEntity<>(object, headers, HttpStatus.OK);
    }

    /**
     * For <b>READ</b> operations use only. Not Exists or Visit Permission NOT satisfied.
     */
    public static <T> ResponseEntity<T> notFound(String requestId, String key) {
        HttpHeaders headers = buildHttpHeaders(requestId);
        headers.set(ErestHeaders.BAD_FIELDS, key);
        return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    /**
     * For <b>WRITE</b> operations use only, but Write Permission NOT satisfied.
     */
    public static <T> ResponseEntity<T> forbidden(String requestId, String key) {
        HttpHeaders headers = buildHttpHeaders(requestId);
        headers.set(ErestHeaders.BAD_FIELDS, key);
        return new ResponseEntity<>(headers, HttpStatus.FORBIDDEN);
    }

    /**
     * For <b>WRITE</b> operations use only.
     */
    public static <T> ResponseEntity<T> conflict(String requestId, String key) {
        HttpHeaders headers = buildHttpHeaders(requestId);
        headers.set(ErestHeaders.BAD_FIELDS, key);
        return new ResponseEntity<>(headers, HttpStatus.CONFLICT);
    }

    /**
     * For <b>READ</b> operations use only.
     */
    public static <T> ResponseEntity<T> notModified(String requestId) {
        HttpHeaders headers = buildHttpHeaders(requestId);
        return new ResponseEntity<>(headers, HttpStatus.NOT_MODIFIED);
    }

}

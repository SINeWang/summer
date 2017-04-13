package one.kii.summer.erest;

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

    public static <T> ResponseEntity<T> badRequest(String requestId, String reason) {
        HttpHeaders headers = buildHttpHeaders(requestId);
        headers.set(ErestHeaders.REASON, reason);
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
     * For <b>READ</b> operations use only.
     */
    public static <T> ResponseEntity<T> notFound(String requestId, String key) {
        HttpHeaders headers = buildHttpHeaders(requestId);
        headers.set(ErestHeaders.REASON, key);
        return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    /**
     * For <b>WRITE</b> operations use only.
     */
    public static <T> ResponseEntity<T> conflict(String requestId, String key) {
        HttpHeaders headers = buildHttpHeaders(requestId);
        headers.set(ErestHeaders.REASON, key);
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

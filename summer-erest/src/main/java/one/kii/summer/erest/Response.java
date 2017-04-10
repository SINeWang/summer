package one.kii.summer.erest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.UUID;

/**
 * Created by WangYanJiong on 02/04/2017.
 */
public class Response {


    public static <T> ResponseEntity<T> badRequest(String requestId, String reason) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-SUMMER-Time", new Date().toString());
        headers.set("X-SUMMER-RequestId", requestId);
        headers.set("X-SUMMER-ResponseId", UUID.randomUUID().toString());
        headers.set("X-SUMMER-Reason", reason);
        return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<T> created(String requestId, T object) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-SUMMER-Time", new Date().toString());
        headers.set("X-SUMMER-RequestId", requestId);
        headers.set("X-SUMMER-ResponseId", UUID.randomUUID().toString());
        return new ResponseEntity<>(object, headers, HttpStatus.CREATED);
    }

    public static <T> ResponseEntity<T> ok(String requestId, T object) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-SUMMER-Time", new Date().toString());
        headers.set("X-SUMMER-RequestId", requestId);
        headers.set("X-SUMMER-ResponseId", UUID.randomUUID().toString());
        return new ResponseEntity<>(object, headers, HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> notFound(String requestId, T resource) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-SUMMER-Time", new Date().toString());
        headers.set("X-SUMMER-RequestId", requestId);
        headers.set("X-SUMMER-ResponseId", UUID.randomUUID().toString());
        return new ResponseEntity<>(resource, headers, HttpStatus.NOT_FOUND);
    }

    public static <T> ResponseEntity<T> conflict(String requestId, T object) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-SUMMER-Time", new Date().toString());
        headers.set("X-SUMMER-RequestId", requestId);
        headers.set("X-SUMMER-ResponseId", UUID.randomUUID().toString());
        return new ResponseEntity<>(object, headers, HttpStatus.CONFLICT);
    }

    public static <T> ResponseEntity<T> notModified(String requestId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-SUMMER-Time", new Date().toString());
        headers.set("X-SUMMER-RequestId", requestId);
        headers.set("X-SUMMER-ResponseId", UUID.randomUUID().toString());
        return new ResponseEntity<>(headers, HttpStatus.NOT_MODIFIED);
    }

}

package one.kii.summer.bound.factory;

import one.kii.summer.bound.Summary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.UUID;

/**
 * Created by WangYanJiong on 02/04/2017.
 */
public class ResponseFactory {


    public static <T> ResponseEntity<T> rejected(HttpStatus status, String reason) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-SUMMER-Summary", Summary.Status.REJECTED.name());
        headers.set("X-SUMMER-Time", new Date().toString());
        headers.set("X-SUMMER-ResponseId", UUID.randomUUID().toString());
        headers.set("X-SUMMER-Reason", reason);
        return new ResponseEntity<>(headers, status);
    }

    public static <T> ResponseEntity<T> badRequest(String reasons) {
        return rejected(HttpStatus.BAD_REQUEST, reasons);
    }

    public static <T> ResponseEntity<T> accepted(T object) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-SUMMER-Summary", Summary.Status.ACCEPTED.name());
        headers.set("X-SUMMER-Time", new Date().toString());
        headers.set("X-SUMMER-ResponseId", UUID.randomUUID().toString());
        return new ResponseEntity<>(object, headers, HttpStatus.BAD_REQUEST);
    }

}

package one.kii.summer.context.exception;

/**
 * Created by WangYanJiong on 4/13/17.
 */
public class NotFound extends Exception {

    String key;

    public NotFound(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}

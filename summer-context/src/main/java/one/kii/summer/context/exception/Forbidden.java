package one.kii.summer.context.exception;

/**
 * Created by WangYanJiong on 14/04/2017.
 */
public class Forbidden extends Exception {

    private String key;

    public Forbidden(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}

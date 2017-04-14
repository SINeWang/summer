package one.kii.summer.io.exception;

/**
 * Created by WangYanJiong on 4/13/17.
 */
public class Conflict extends Exception {

    Object object;

    String key;

    public Conflict(Object object) {
        this.object = object;
    }

    public Conflict(String key) {
        this.key = key;
    }

    public Object getObject() {
        return object;
    }

    public String getKey() {
        return key;
    }
}

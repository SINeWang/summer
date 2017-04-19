package one.kii.summer.io.exception;

/**
 * Created by WangYanJiong on 4/13/17.
 */
public class NotFound extends Exception {

    String[] keys;

    public NotFound(String[] keys) {
        this.keys = keys;
    }

    public String[] getKeys() {
        return keys;
    }

    public String getKey() {
        if (keys == null) {
            return null;
        }
        if (keys.length == 0) {
            return null;
        }
        return keys[0];
    }
}

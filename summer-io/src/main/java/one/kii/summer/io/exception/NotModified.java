package one.kii.summer.io.exception;

import java.util.Set;

/**
 * Created by WangYanJiong on 4/13/17.
 */
public class NotModified extends Exception {

    String[] keys;

    public NotModified(Set<String> keys) {
        this.keys = keys.toArray(new String[0]);
    }

    public NotModified(String keys) {
        this.keys = new String[]{keys};
    }

    public NotModified(String[] keys) {
        this.keys = keys;
    }

    public String[] getKeys() {
        return keys;
    }
}

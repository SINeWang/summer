package one.kii.summer.io.exception;

import java.util.Set;

/**
 * Created by WangYanJiong on 14/04/2017.
 */
public class Forbidden extends Exception {

    private String[] keys;

    public Forbidden(String key) {
        this.keys = new String[]{key};
    }

    public Forbidden(String[] keys) {
        this.keys = keys;
    }

    public Forbidden(Set<String> keys) {
        this.keys = keys.toArray(new String[0]);
    }

    public String[] getKeys() {
        return keys;
    }
}

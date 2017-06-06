package one.kii.summer.io.exception;

import java.util.Set;

/**
 * Created by WangYanJiong on 4/13/17.
 */
public class Conflict extends Exception {

    String[] keys;

    public Conflict(String key) {
        this.keys = new String[]{key};
    }

    public Conflict(String[] keys) {
        this.keys = keys;
    }

    public Conflict(Set<String> keys) {
        this.keys = keys.toArray(new String[0]);
    }

    public String[] getKeys() {
        return keys;
    }
}

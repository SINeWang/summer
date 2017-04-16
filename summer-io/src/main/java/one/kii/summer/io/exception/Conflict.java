package one.kii.summer.io.exception;

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

    public String[] getKeys() {
        return keys;
    }
}

package one.kii.summer.io.exception;

/**
 * Created by WangYanJiong on 4/13/17.
 */
public class BadRequest extends Exception {

    String[] keys;

    String[] reasons;

    public BadRequest(String keys) {
        this.keys = new String[]{keys};
    }

    public BadRequest(String[] keys) {
        this.keys = keys;
    }

    public BadRequest(String[] keys, String[] reasons) {
        this.keys = keys;
        this.reasons = reasons;
    }


    public String[] getKeys() {
        return keys;
    }

    public String[] getReasons() {
        return reasons;
    }

}

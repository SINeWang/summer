package one.kii.summer.io.exception;

/**
 * Created by WangYanJiong on 14/04/2017.
 */
public class Panic extends Exception {


    String[] keys;

    String[] reasons;

    public Panic() {
    }

    public Panic(String keys) {
        this.keys = new String[]{keys};
    }

    public Panic(String[] keys) {
        this.keys = keys;
    }

    public Panic(String[] keys, String[] reasons) {
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

package one.kii.summer.io.exception;

import java.util.Set;

/**
 * Created by WangYanJiong on 14/04/2017.
 */
public class Panic extends Exception {


    String[] keys;

    String[] reasons;

    public Panic(Set<String> keys) {
        this.keys = keys.toArray(new String[0]);
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

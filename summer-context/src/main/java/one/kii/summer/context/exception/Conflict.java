package one.kii.summer.context.exception;

/**
 * Created by WangYanJiong on 4/13/17.
 */
public class Conflict extends Exception {

    String[] fields;

    public Conflict(String[] fields) {
        this.fields = fields;
    }
}

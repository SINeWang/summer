package one.kii.summer.context.exception;

/**
 * Created by WangYanJiong on 4/13/17.
 */
public class NotFound extends Exception {

    String[] fields;

    public NotFound(String[] fields) {
        this.fields = fields;
    }

    public String[] getFields() {
        return fields;
    }
}

package one.kii.summer.io.exception;

/**
 * Created by WangYanJiong on 4/13/17.
 */
public class NotModified extends Exception {

    String[] fields;

    public NotModified(String fields){
        this.fields = new String[]{fields};
    }
    public NotModified(String[] fields) {
        this.fields = fields;
    }

    public String[] getFields() {
        return fields;
    }
}

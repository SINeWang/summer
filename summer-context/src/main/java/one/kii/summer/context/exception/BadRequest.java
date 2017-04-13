package one.kii.summer.context.exception;

/**
 * Created by WangYanJiong on 4/13/17.
 */
public class BadRequest extends Exception {

    String[] fields;

    String[] reasons;

    public BadRequest(String fields){
        this.fields = new String[]{fields};
    }

    public BadRequest(String[] fields) {
        this.fields = fields;
    }

    public BadRequest(String[] fields, String[] reasons) {
        this.fields = fields;
        this.reasons = reasons;
    }


    public String[] getFields() {
        return fields;
    }

    public String[] getReasons() {
        return reasons;
    }

}

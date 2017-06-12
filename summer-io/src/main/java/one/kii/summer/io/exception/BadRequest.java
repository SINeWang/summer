package one.kii.summer.io.exception;

import org.springframework.util.MultiValueMap;

/**
 * Created by WangYanJiong on 4/13/17.
 */
public class BadRequest extends Exception {

    private MultiValueMap<String, String> reasons;

    public BadRequest(MultiValueMap<String, String> reasons) {
        this.reasons = reasons;
    }

    public MultiValueMap<String, String> getReasons() {
        return reasons;
    }

    public String[] getKeys() {
        return reasons.keySet().toArray(new String[0]);
    }


}

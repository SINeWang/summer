package one.kii.summer.io.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

/**
 * Created by WangYanJiong on 11/04/2017.
 */
public class ErestPost extends ErestWrite {

    private static Logger logger = LoggerFactory.getLogger(ErestPost.class);

    public ErestPost(String operatorId) {
        super(operatorId);
        super.httpMethod = HttpMethod.POST;
    }
}

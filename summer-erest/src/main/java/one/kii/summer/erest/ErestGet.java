package one.kii.summer.erest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by WangYanJiong on 02/04/2017.
 */
public class ErestGet {

    private static Logger logger = LoggerFactory.getLogger(ErestGet.class);
    
    public <T> T forAny(String urlTemplate, Class<T> klass, Object... uriVariables) {
        logger.debug("forAny:{}, return:{}", urlTemplate, klass.getSimpleName());

        RestTemplate restTemplate = new RestTemplate();

        Class type = new Type(klass).getType();

        ResponseEntity<T> response = restTemplate.getForEntity(urlTemplate, klass, uriVariables);
        return response.getBody();
    }

    private class Type {

        private Class type;

        private Type(Class type) {
            this.type = type;
        }

        Class getType() {
            return type;
        }
    }
}

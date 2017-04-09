package one.kii.summer.erest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * Created by WangYanJiong on 4/7/17.
 */
public class ErestPostForm {

    private static Logger logger = LoggerFactory.getLogger(ErestPostForm.class);

    public <T> T doPost(String urlTemplate, HttpHeaders headers, MultiValueMap<String, String> bodyMap, Class<T> klass, Object... uriVariables) {
        logger.debug("forAny:{}, return:{}", urlTemplate, klass.getSimpleName());

        headers.set("X-SUMMER-RequestId", UUID.randomUUID().toString());
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);


        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(bodyMap, headers);


        ResponseEntity<T> response = restTemplate.exchange(urlTemplate, HttpMethod.POST, request, klass, uriVariables);
        return response.getBody();

    }

}
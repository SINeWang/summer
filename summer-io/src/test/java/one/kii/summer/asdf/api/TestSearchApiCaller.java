package one.kii.summer.asdf.api;

import one.kii.summer.io.context.ReadContext;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.Panic;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangYanJiong on 05/06/2017.
 */
public class TestSearchApiCaller {


    static String[] keys = new String[]{};
    static MultiValueMap map = new LinkedMultiValueMap();

    ReadContext context = new ReadContext("requestId", "ownerId", "visitorId");
    Object form = new Object();

    @Test
    public void testOK() {
        SearchApi api = new TestSearchApiCaller.TestOK();
        Errors errors = null;
        Object resp = SearchApiCaller.sync(api, context, form, errors);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }


    @Test
    public void testBadRequest() {
        SearchApi api = new TestSearchApiCaller.TestBadRequest();
        Errors errors = null;
        Object resp = SearchApiCaller.sync(api, context, form, errors);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }

    @Test
    public void testPanic() {
        SearchApi api = new TestSearchApiCaller.TestPanic();
        Errors errors = null;
        Object resp = SearchApiCaller.sync(api, context, form, errors);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }


    public static class TestOK implements SearchApi {
        @Override
        public List search(ReadContext context, Object form) throws BadRequest, Panic {
            return new ArrayList();
        }
    }

    public static class TestBadRequest implements SearchApi {
        @Override
        public List search(ReadContext context, Object form) throws BadRequest, Panic {
            throw new BadRequest(map);
        }
    }


    public static class TestPanic implements SearchApi {
        @Override
        public List search(ReadContext context, Object form) throws BadRequest, Panic {
            throw new Panic(keys);
        }
    }
}

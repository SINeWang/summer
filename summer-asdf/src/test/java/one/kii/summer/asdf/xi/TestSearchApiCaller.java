package one.kii.summer.asdf.xi;

import one.kii.summer.io.context.ReadContext;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.Panic;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangYanJiong on 05/06/2017.
 */
public class TestSearchApiCaller {


    static String[] keys = new String[]{};
    ReadContext context = new ReadContext("requestId", "ownerId", "visitorId");
    Object form = new Object();

    @Test
    public void testOK() {
        SearchApi api = new TestSearchApiCaller.TestOK();
        Object resp = SearchApiCaller.sync(api, context, form);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }


    @Test
    public void testBadRequest() {
        SearchApi api = new TestSearchApiCaller.TestBadRequest();
        Object resp = SearchApiCaller.sync(api, context, form);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }

    @Test
    public void testPanic() {
        SearchApi api = new TestSearchApiCaller.TestPanic();
        Object resp = SearchApiCaller.sync(api, context, form);
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
            throw new BadRequest(keys);
        }
    }


    public static class TestPanic implements SearchApi {
        @Override
        public List search(ReadContext context, Object form) throws BadRequest, Panic {
            throw new Panic(keys);
        }
    }
}

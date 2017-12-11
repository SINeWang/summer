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
 * Created by WangYanJiong on 22/07/2017.
 */
public class TestPagingSearchApiCaller {


    static String[] keys = new String[]{};
    static MultiValueMap map = new LinkedMultiValueMap();

    ReadContext context = new ReadContext("requestId", "ownerId", "visitorId");
    Object form = new Object();

    @Test
    public void testOK() {
        PagingSearchApi api = new TestPagingSearchApiCaller.TestOK();
        PagingSearchApi.Paging paging = new PagingSearchApi.Paging();
        Errors errors = null;
        Object resp = PagingSearchApiCaller.sync(api, context, form, paging, errors);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }


    @Test
    public void testBadRequest() {
        PagingSearchApi api = new TestPagingSearchApiCaller.TestBadRequest();
        PagingSearchApi.Paging paging = new PagingSearchApi.Paging();
        Errors errors = null;
        Object resp = PagingSearchApiCaller.sync(api, context, form, paging, errors);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }

    @Test
    public void testPanic() {
        PagingSearchApi api = new TestPagingSearchApiCaller.TestPanic();
        PagingSearchApi.Paging paging = new PagingSearchApi.Paging();
        Errors errors = null;
        Object resp = PagingSearchApiCaller.sync(api, context, form, paging, errors);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }


    public static class TestOK implements PagingSearchApi {
        @Override
        public Receipt<Object> search(ReadContext context, Object form, Paging paging) throws BadRequest, Panic {
            return new Receipt<>();
        }
    }

    public static class TestBadRequest implements PagingSearchApi {
        @Override
        public Receipt search(ReadContext context, Object form, Paging paging) throws BadRequest, Panic {
            throw new BadRequest(map);
        }
    }


    public static class TestPanic implements PagingSearchApi {
        @Override
        public Receipt search(ReadContext context, Object form, Paging paging) throws BadRequest, Panic {
            throw new Panic(keys);
        }
    }
}

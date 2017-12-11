package one.kii.summer.asdf.api;

import one.kii.summer.io.context.WriteContext;
import one.kii.summer.io.exception.*;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.Errors;

/**
 * Created by WangYanJiong on 03/06/2017.
 */
public class TestCommitApiCaller {

    static String[] keys = new String[]{};
    static MultiValueMap map = new LinkedMultiValueMap();
    WriteContext context = new WriteContext("requestId", "ownerId", "operatorId");
    Object form = new Object();

    @Test
    public void testOK() {
        CommitApi api = new TestOK();
        Errors errors = null;
        Object resp = CommitApiCaller.sync(api, context, form, errors);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }


    @Test
    public void testBadRequest() {
        CommitApi api = new TestBadRequest();
        Errors errors = null;
        Object resp = CommitApiCaller.sync(api, context, form, errors);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }

    @Test
    public void testConflict() {
        CommitApi api = new TestConflict();
        Errors errors = null;
        Object resp = CommitApiCaller.sync(api, context, form, errors);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }

    @Test
    public void testForbidden() {
        CommitApi api = new TestForbidden();
        Errors errors = null;
        Object resp = CommitApiCaller.sync(api, context, form, errors);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }

    @Test
    public void testNotFound() {
        CommitApi api = new TestNotFound();
        Errors errors = null;
        Object resp = CommitApiCaller.sync(api, context, form, errors);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }

    @Test
    public void testPanic() {
        CommitApi api = new TestPanic();
        Errors errors = null;
        Object resp = CommitApiCaller.sync(api, context, form, errors);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }


    public static class TestOK implements CommitApi {
        @Override
        public Object commit(WriteContext context, Object form) throws BadRequest, Conflict, Forbidden, NotFound, Panic {
            return new Object();
        }
    }

    public static class TestNotFound implements CommitApi {
        @Override
        public Object commit(WriteContext context, Object form) throws BadRequest, Conflict, Forbidden, NotFound, Panic {
            throw new NotFound(map);
        }
    }

    public static class TestConflict implements CommitApi {
        @Override
        public Object commit(WriteContext context, Object form) throws BadRequest, Conflict, Forbidden, NotFound, Panic {
            throw new Conflict(keys);
        }
    }

    public static class TestBadRequest implements CommitApi {
        @Override
        public Object commit(WriteContext context, Object form) throws BadRequest, Conflict, Forbidden, NotFound, Panic {
            throw new BadRequest(map
            );
        }
    }

    public static class TestForbidden implements CommitApi {
        @Override
        public Object commit(WriteContext context, Object form) throws BadRequest, Conflict, Forbidden, NotFound, Panic {
            throw new Forbidden(keys);
        }
    }

    public static class TestPanic implements CommitApi {
        @Override
        public Object commit(WriteContext context, Object form) throws BadRequest, Conflict, Forbidden, NotFound, Panic {
            throw new Panic(keys);
        }
    }
}

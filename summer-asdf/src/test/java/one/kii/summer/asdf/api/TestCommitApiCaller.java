package one.kii.summer.asdf.api;

import one.kii.summer.io.context.WriteContext;
import one.kii.summer.io.exception.*;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

/**
 * Created by WangYanJiong on 03/06/2017.
 */
public class TestCommitApiCaller {

    static String[] keys = new String[]{};
    WriteContext context = new WriteContext("requestId", "ownerId", "operatorId");
    Object form = new Object();

    @Test
    public void testOK() {
        CommitApi api = new TestOK();
        Object resp = CommitApiCaller.sync(api, context, form);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }


    @Test
    public void testBadRequest() {
        CommitApi api = new TestBadRequest();
        Object resp = CommitApiCaller.sync(api, context, form);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }

    @Test
    public void testConflict() {
        CommitApi api = new TestConflict();
        Object resp = CommitApiCaller.sync(api, context, form);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }

    @Test
    public void testForbidden() {
        CommitApi api = new TestForbidden();
        Object resp = CommitApiCaller.sync(api, context, form);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }

    @Test
    public void testNotFound() {
        CommitApi api = new TestNotFound();
        Object resp = CommitApiCaller.sync(api, context, form);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }

    @Test
    public void testPanic() {
        CommitApi api = new TestPanic();
        Object resp = CommitApiCaller.sync(api, context, form);
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
            throw new NotFound(keys);
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
            throw new BadRequest(keys);
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

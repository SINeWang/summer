package one.kii.summer.asdf.xi;

import one.kii.summer.io.context.ReadContext;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.NotFound;
import one.kii.summer.io.exception.Panic;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

/**
 * Created by WangYanJiong on 03/06/2017.
 */
public class TestVisitApiCaller {


    static String[] keys = new String[]{};
    ReadContext context = new ReadContext("requestId", "ownerId", "visitorId");
    Object form = new Object();

    @Test
    public void testOK() {
        VisitApi api = new TestOK();
        Object resp = VisitApiCaller.sync(api, context, form);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }


    @Test
    public void testBadRequest() {
        VisitApi api = new TestBadRequest();
        Object resp = VisitApiCaller.sync(api, context, form);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }

    @Test
    public void testNotFound() {
        VisitApi api = new TestNotFound();
        Object resp = VisitApiCaller.sync(api, context, form);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }

    @Test
    public void testPanic() {
        VisitApi api = new TestPanic();
        Object resp = VisitApiCaller.sync(api, context, form);
        Assert.isInstanceOf(ResponseEntity.class, resp);
    }


    public static class TestOK implements VisitApi {
        @Override
        public Object visit(ReadContext context, Object form) throws BadRequest, NotFound, Panic {
            return new Object();
        }
    }

    public static class TestNotFound implements VisitApi {
        @Override
        public Object visit(ReadContext context, Object form) throws BadRequest, NotFound, Panic {
            throw new NotFound(keys);
        }
    }


    public static class TestBadRequest implements VisitApi {
        @Override
        public Object visit(ReadContext context, Object form) throws BadRequest, NotFound, Panic {
            throw new BadRequest(keys);
        }
    }


    public static class TestPanic implements VisitApi {
        @Override
        public Object visit(ReadContext context, Object form) throws BadRequest, NotFound, Panic {
            throw new Panic(keys);
        }
    }
}

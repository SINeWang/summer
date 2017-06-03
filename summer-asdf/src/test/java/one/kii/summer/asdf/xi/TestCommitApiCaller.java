package one.kii.summer.asdf.xi;

import one.kii.summer.io.context.WriteContext;
import one.kii.summer.io.exception.*;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

/**
 * Created by WangYanJiong on 03/06/2017.
 */
public class TestCommitApiCaller {

    @Test
    public void test() {

        CommitApi api = new TestCommitApi();

        WriteContext context = new WriteContext("requestId", "ownerId", "operatorId");

        Object form = new Object();

        Object resp = CommitApiCaller.call(api, context, form);
        Assert.isInstanceOf(ResponseEntity.class, resp);

    }

    public static class TestCommitApi implements CommitApi {

        @Override
        public Object commit(WriteContext context, Object form) throws BadRequest, Conflict, Forbidden, NotFound, Panic {
            return new Object();
        }
    }
}

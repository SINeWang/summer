package one.kii.summer.io.validator;

import one.kii.summer.io.annotations.MayHave;
import one.kii.summer.io.exception.BadRequest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.MultiValueMap;

/**
 * Created by WangYanJiong on 20/04/2017.
 */
public class TestNotBadRequest {


    @Test
    public void test1() {
        TestClass1 tc1 = new TestClass1();

        MultiValueMap map = null;
        try {
            NotBadRequest.from(tc1);
        } catch (BadRequest badRequest) {
            Assert.assertEquals(1, badRequest.getReasons().size());
            Assert.assertNull(badRequest.getReasons().getFirst("b"));
        }
    }

    @Test
    public void test2() throws BadRequest {
        TestClass2 tc2 = new TestClass2();
        NotBadRequest.from(tc2);
    }

    class TestClass1 {
        String a = "12";
        String b = null;
        String c = "  ";
        String d = "123";
    }


    class TestClass2 {
        String a = "12";
        @MayHave
        String b = null;
        String c = "  ";
        String d = "123";
    }
}

package one.kii.summer.io.validator;

import one.kii.summer.io.exception.BadRequest;
import org.junit.Test;

/**
 * Created by WangYanJiong on 20/04/2017.
 */
public class TestMust {

    @Test
    public void test() {

        TestClass tc = new TestClass();

        try {
            Must.have(tc, new String[]{"a"});
        } catch (BadRequest badRequest) {
            badRequest.printStackTrace();
        }
    }

    @Test(expected = BadRequest.class)
    public void test1() throws BadRequest {

        TestClass tc = new TestClass();

        Must.have(tc, new String[]{"b"});
    }

    @Test(expected = BadRequest.class)
    public void test2() throws BadRequest {

        TestClass tc = new TestClass();

        Must.have(tc, new String[]{"c"});
    }

    @Test
    public void test3() throws BadRequest {
        TestClass tc = new TestClass();
        Must.have(tc, new String[]{"a", "d"});
    }


    class TestClass {
        String a = "12";
        String b = null;
        String c = "  ";
        String d = "123";
    }
}

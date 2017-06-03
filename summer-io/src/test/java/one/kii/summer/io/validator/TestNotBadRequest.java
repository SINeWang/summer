package one.kii.summer.io.validator;

import one.kii.summer.io.annotations.MayHave;
import one.kii.summer.io.exception.BadRequest;
import org.junit.Test;

/**
 * Created by WangYanJiong on 20/04/2017.
 */
public class TestNotBadRequest {

    @Test
    public void test() {

        TestClass tc = new TestClass();

        try {
            NotBadRequest.from(tc, new String[]{"a"});
        } catch (BadRequest badRequest) {
            badRequest.printStackTrace();
        }
    }

    @Test(expected = BadRequest.class)
    public void test1() throws BadRequest {

        TestClass tc = new TestClass();

        NotBadRequest.from(tc, new String[]{"b"});
    }

    @Test
    public void test2() throws BadRequest {

        TestClass tc = new TestClass();

        NotBadRequest.from(tc, new String[]{"c"});
    }

    @Test
    public void test3() throws BadRequest {
        TestClass tc = new TestClass();
        NotBadRequest.from(tc, new String[]{"a", "d"});
    }

    @Test
    public void test4() throws BadRequest {
        TestClass2 tc2 = new TestClass2();
        NotBadRequest.from(tc2);
    }


    class TestClass {
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

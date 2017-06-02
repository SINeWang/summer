package one.kii.summer.io.validator;

import one.kii.summer.io.annotations.MustHave;
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

    @Test
    public void test4() throws BadRequest {
        TestClass2 tc2 = new TestClass2();
        Must.have(tc2);
    }

    @Test(expected = BadRequest.class)
    public void test11() throws BadRequest {
        Must.have(TestClass2.class);
    }


    class TestClass {
        String a = "12";
        String b = null;
        String c = "  ";
        String d = "123";
    }

    class TestClass2 {
        @MustHave
        String a = "12";
        String b = null;
        String c = "  ";
        String d = "123";
    }
}

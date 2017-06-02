package one.kii.summer.io.validator;

import one.kii.summer.io.annotations.MayHave;
import one.kii.summer.io.exception.NotFound;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by WangYanJiong on 02/06/2017.
 */
public class TestNone {

    @Test
    public void testNoneOf1() {
        try {
            None.of(TestClass1.class);
        } catch (NotFound notFound) {
            Assert.assertEquals(4, notFound.getKeys().length);
        }

        try {
            None.of(TestClass2.class);
        } catch (NotFound notFound) {
            Assert.assertEquals(3, notFound.getKeys().length);
        }
    }

    @Test
    public void testNoneOf2() {
        try {
            None.of(TestClass1.class, MayHave.class);
        } catch (NotFound notFound) {
            Assert.assertEquals(3, notFound.getKeys().length);
        }
        try {
            None.of(TestClass2.class, MayHave.class);
        } catch (NotFound notFound) {
            Assert.assertEquals(3, notFound.getKeys().length);
        }
    }

    static class TestClass2 {

        String a;
        @MayHave
        String b;
        String c;
        String d;
    }

    class TestClass1 {

        String a;
        @MayHave
        String b;
        String c;
        String d;
    }

}

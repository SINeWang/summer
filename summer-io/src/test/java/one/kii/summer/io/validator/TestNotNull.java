package one.kii.summer.io.validator;

import one.kii.summer.io.annotations.MayHave;
import one.kii.summer.io.exception.NotFound;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by WangYanJiong on 02/06/2017.
 */
public class TestNotNull {

    @Test
    public void testNoneOf11() {
        try {
            NotNull.of(TestClass1.class);
        } catch (NotFound notFound) {
            Assert.assertEquals(4, notFound.getKeys().length);
        }

        try {
            NotNull.of(TestClass2.class);
        } catch (NotFound notFound) {
            Assert.assertEquals(4, notFound.getKeys().length);
        }
    }

    @Test
    public void testNoneOf12() {
        try {
            NotNull.of(TestClass1.class, MayHave.class);
        } catch (NotFound notFound) {
            Assert.assertEquals(3, notFound.getKeys().length);
        }
        try {
            NotNull.of(TestClass2.class, MayHave.class);
        } catch (NotFound notFound) {
            Assert.assertEquals(3, notFound.getKeys().length);
        }
    }

    @Test
    public void testNoneOf21() {
        try {
            NotNull.of(TestClass1.class, (TestClass1) null);
        } catch (NotFound notFound) {
            Assert.assertEquals(4, notFound.getKeys().length);
        }
    }

    @Test
    public void testNoneOf22() {
        try {
            NotNull.of(TestClass1.class, MayHave.class);
        } catch (NotFound notFound) {
            Assert.assertEquals(3, notFound.getKeys().length);
        }
    }

    @Test
    public void testNoneOf23() throws NotFound {
        NotNull.of(TestClass2.class, MayHave.class, new TestClass2());
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

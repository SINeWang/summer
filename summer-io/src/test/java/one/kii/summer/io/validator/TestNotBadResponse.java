package one.kii.summer.io.validator;

import one.kii.summer.io.annotations.MayHave;
import one.kii.summer.io.exception.NotFound;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by WangYanJiong on 02/06/2017.
 */
public class TestNotBadResponse {

    @Test
    public void testNoneOf11() {
        try {
            NotBadResponse.of(TestClass1.class);
        } catch (NotFound notFound) {
            Assert.assertEquals(4, notFound.getKeys().length);
        }

        try {
            NotBadResponse.of(TestClass2.class);
        } catch (NotFound notFound) {
            Assert.assertEquals(4, notFound.getKeys().length);
        }
    }

    @Test
    public void testNoneOf12() {
        try {
            NotBadResponse.of(TestClass1.class, MayHave.class);
        } catch (NotFound notFound) {
            Assert.assertEquals(3, notFound.getKeys().length);
        }
        try {
            NotBadResponse.of(TestClass2.class, MayHave.class);
        } catch (NotFound notFound) {
            Assert.assertEquals(3, notFound.getKeys().length);
        }
    }

    @Test
    public void testNoneOf21() {
        try {
            NotBadResponse.of(TestClass1.class, (TestClass1) null);
        } catch (NotFound notFound) {
            Assert.assertEquals(4, notFound.getKeys().length);
        }
    }

    @Test
    public void testNoneOf22() {
        try {
            NotBadResponse.of(TestClass1.class, MayHave.class);
        } catch (NotFound notFound) {
            Assert.assertEquals(3, notFound.getKeys().length);
        }
    }

    @Test(expected = NotFound.class)
    public void testNoneOf23() throws NotFound {
        NotBadResponse.of(TestClass2.class, MayHave.class, new TestClass2());
    }

    @Test(expected = NotFound.class)
    public void testNoneOf24() throws NotFound {
        NotBadResponse.of(TestClass2.class, new TestClass2());
    }

    @Test
    public void testNoneOf31() {

        try {
            NotBadResponse.of(TestClass2.class, new TestClass2());
        } catch (NotFound notFound) {
            Assert.assertEquals(4, notFound.getKeys().length);
        }
    }

    @Test
    public void testNoneOf32() {
        TestClass2 tc2 = new TestClass2();
        tc2.setA("a");
        try {
            NotBadResponse.of(TestClass2.class, tc2);
        } catch (NotFound notFound) {
            Assert.assertEquals(3, notFound.getKeys().length);
        }
    }

    @Test
    public void testNoneOf33() {
        TestClass2 tc2 = new TestClass2();
        tc2.setA("a");
        try {
            NotBadResponse.of(TestClass2.class, MayHave.class, tc2);
        } catch (NotFound notFound) {
            Assert.assertEquals(2, notFound.getKeys().length);
        }
    }

    static class TestClass2 {

        String a;
        @MayHave
        String b;
        String c;
        String d;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }

    class TestClass1 {

        String a;
        @MayHave
        String b;
        String c;
        String d;
    }

}

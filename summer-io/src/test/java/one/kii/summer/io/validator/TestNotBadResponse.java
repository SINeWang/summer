package one.kii.summer.io.validator;

import one.kii.summer.io.annotations.MayHave;
import one.kii.summer.io.exception.NotFound;
import one.kii.summer.io.exception.Panic;
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
        } catch (Panic panic) {
            Assert.assertEquals(4, panic.getKeys().length);
        }

        try {
            NotBadResponse.of(TestClass2.class);
        } catch (Panic panic) {
            Assert.assertEquals(4, panic.getKeys().length);
        }
    }

    @Test
    public void testNoneOf12() {
        try {
            NotBadResponse.of(TestClass1.class, MayHave.class);
        } catch (Panic panic) {
            Assert.assertEquals(3, panic.getKeys().length);
        }
        try {
            NotBadResponse.of(TestClass2.class, MayHave.class);
        } catch (Panic panic) {
            Assert.assertEquals(3, panic.getKeys().length);
        }
    }

    @Test
    public void testNoneOf21() {
        try {
            NotBadResponse.of(TestClass1.class, (TestClass1) null);
        } catch (Panic panic) {
            Assert.assertEquals(4, panic.getKeys().length);
        }
    }

    @Test
    public void testNoneOf22() {
        try {
            NotBadResponse.of(TestClass1.class, MayHave.class);
        } catch (Panic panic) {
            Assert.assertEquals(3, panic.getKeys().length);
        }
    }

    @Test(expected = Panic.class)
    public void testNoneOf23() throws Panic {
        NotBadResponse.of(TestClass2.class, MayHave.class, new TestClass2());
    }

    @Test(expected = Panic.class)
    public void testNoneOf24() throws Panic {
        NotBadResponse.of(TestClass2.class, new TestClass2());
    }

    @Test
    public void testNoneOf31() {

        try {
            NotBadResponse.of(TestClass2.class, new TestClass2());
        } catch (Panic panic) {
            Assert.assertEquals(4, panic.getKeys().length);
        }
    }

    @Test
    public void testNoneOf32() {
        TestClass2 tc2 = new TestClass2();
        tc2.setA("a");
        try {
            NotBadResponse.of(TestClass2.class, tc2);
        } catch (Panic panic) {
            Assert.assertEquals(3, panic.getKeys().length);
        }
    }

    @Test
    public void testNoneOf33() {
        TestClass2 tc2 = new TestClass2();
        tc2.setA("a");
        try {
            NotBadResponse.of(TestClass2.class, MayHave.class, tc2);
        } catch (Panic panic) {
            Assert.assertEquals(2, panic.getKeys().length);
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

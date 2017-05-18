package one.kii.summer.beans.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by WangYanJiong on 18/05/2017.
 */
public class TestMagicCopy {


    @Test
    public void testMagicCopy() {
        TestData0 d0 = MagicCopy.from(TestData0.class, new TestData1(), new TestData2());

        Assert.assertEquals("1", d0.f1);
        Assert.assertEquals("2", d0.f2);
    }


    public static class TestData0 {
        String f1;
        String f2;

        public String getF1() {
            return f1;
        }

        public void setF1(String f1) {
            this.f1 = f1;
        }

        public String getF2() {
            return f2;
        }

        public void setF2(String f2) {
            this.f2 = f2;
        }
    }


    public static class TestData1 {
        String f1 = "1";

        public String getF1() {
            return f1;
        }
    }

    public static class TestData2 {

        String f2 = "2";

        public String getF2() {
            return f2;
        }
    }
}

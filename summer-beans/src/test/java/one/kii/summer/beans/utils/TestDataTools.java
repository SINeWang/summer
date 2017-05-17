package one.kii.summer.beans.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangYanJiong on 26/04/2017.
 */
public class TestDataTools {


    @Test
    public void testCopy() {
        List<TestData> test1 = new ArrayList<>();
        TestData td1 = new TestData();
        td1.setAaa("aaa");

        TestData td2 = new TestData();
        td2.setAaa("bbb");
        test1.add(td1);
        test1.add(td2);

        List<TestData> test2 = DataTools.copy(test1, TestData.class);
        Assert.assertEquals(test1.size(), test2.size());
        Assert.assertEquals("aaa", test1.get(0).getAaa());
        Assert.assertEquals("bbb", test2.get(1).getAaa());
    }

    @Test
    public void testMagicCopy() {
        TestData0 d0 = DataTools.magicCopy(TestData0.class, new TestData1(), new TestData2());

        Assert.assertEquals("1", d0.f1);
        Assert.assertEquals("2", d0.f2);
    }

    public static class TestData {

        String aaa;

        public String getAaa() {
            return aaa;
        }

        public void setAaa(String aaa) {
            this.aaa = aaa;
        }
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
        public String getF1() {
            return f1;
        }

        String f1 = "1";
    }

    public static class TestData2 {

        String f2 = "2";

        public String getF2() {
            return f2;
        }
    }
}

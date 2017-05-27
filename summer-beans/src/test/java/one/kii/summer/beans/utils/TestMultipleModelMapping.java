package one.kii.summer.beans.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WangYanJiong on 18/05/2017.
 */
public class TestMultipleModelMapping {

    @Test
    public void case0() {
        TestData0 d0 = MultipleModelMapping.from(TestData0.class, new TestData1());

        Assert.assertEquals("1", d0.f1);
    }


    @Test
    public void case1() {
        TestData0 d0 = MultipleModelMapping.from(TestData0.class, new TestData1(), new TestData2());

        Assert.assertEquals("1", d0.f1);
        Assert.assertEquals("2", d0.f2);
    }

    @Test
    public void case2() {
        Map map = new HashMap();
        map.put("f1", "1");
        TestData0 d0 = MultipleModelMapping.from(TestData0.class, map, new TestData2());

        Assert.assertEquals("1", d0.f1);
        Assert.assertEquals("2", d0.f2);
    }


    @Test
    public void case3() {
        Map map1 = new HashMap();
        map1.put("f1", "1");
        Map map2 = new HashMap();
        map2.put("f2", "2");

        TestData0 d0 = MultipleModelMapping.from(TestData0.class, map1, map2);

        Assert.assertEquals("1", d0.f1);
        Assert.assertEquals("2", d0.f2);
    }

    @Test
    public void case4() {
        Map map1 = new HashMap();
        map1.put("f1", 1);


        TestData0 d0 = MultipleModelMapping.from(TestData0.class, map1, new TestData3());

        Assert.assertEquals("1", d0.f1);
        Assert.assertEquals("2", d0.f2);
    }

    @Test
    public void case5() {
        Map map1 = new HashMap();
        map1.put("f1", 1);
        Map map2 = new HashMap();
        map2.put("f2", 2);


        TestData0 d0 = MultipleModelMapping.from(TestData0.class, map1, map2);

        Assert.assertEquals("1", d0.f1);
        Assert.assertEquals("2", d0.f2);
    }
    @Test
    public void case6() {

        TestData0 d0 = MultipleModelMapping.from(TestData0.class, new TestData3(), new TestData4());

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

    public static class TestData3 extends TestData2 {

    }

    public static class TestData4 {
        int f1 = 1;
    }
}

package one.kii.summer.beans.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WangYanJiong on 26/04/2017.
 */
public class TestSingleValueMapping {


    @Test
    public void testSameClass() {

        List<TestData3> test1 = new ArrayList<>();

        TestData3 td1 = new TestData3();
        td1.setCcc(333L);

        test1.add(td1);


        List<TestData3> test2 = SingleValueMapping.from(TestData3.class, test1);
        Assert.assertEquals(Long.valueOf(333L), test2.get(0).getCcc());
    }


    @Test
    public void test1() {
        List<TestData> test1 = new ArrayList<>();
        TestData td1 = new TestData();
        td1.setAaa("aaa");

        TestData td2 = new TestData();
        td2.setAaa("bbb");
        test1.add(td1);
        test1.add(td2);

        List<TestData> test2 = SingleValueMapping.from(TestData.class, test1);
        Assert.assertEquals(test1.size(), test2.size());
        Assert.assertEquals("aaa", test1.get(0).getAaa());
        Assert.assertEquals("bbb", test2.get(1).getAaa());
    }

    @Test
    public void test2() {
        Map map = new HashMap();
        map.put("bbb", 111);

        TestData2 td = SingleValueMapping.from(TestData2.class, map);
        Assert.assertEquals(111, td.getBbb());
    }

    @Test
    public void test3() {
        Map map = new HashMap();
        map.put("ccc", "3333");

        TestData3 td = SingleValueMapping.from(TestData3.class, map);
        Assert.assertEquals(Long.valueOf(3333L), td.getCcc());
    }

    @Test
    public void test41() {
        TestData4 testData4 = new TestData4();
        testData4.setCcc("444");

        TestData3 td = SingleValueMapping.from(TestData3.class, testData4);
        Assert.assertEquals(Long.valueOf(444L), td.getCcc());
    }

    @Test
    public void test42() {
        TestData3 testData3 = new TestData3();
        testData3.setCcc(333L);

        TestData4 td = SingleValueMapping.from(TestData4.class, testData3);
        Assert.assertEquals("333", td.getCcc());
    }

    @Test
    public void testComplex() {
        TestComplexSrc1 src = new TestComplexSrc1();
        TestData3 testData3 = new TestData3();
        testData3.setCcc(333L);
        src.setComplex(testData3);

        TestComplexDest1 dest = ValueMapping.from(TestComplexDest1.class, src);
        Assert.assertEquals("333", dest.getComplex().getCcc());

        Assert.assertEquals(TestData4.class, dest.getComplex().getClass());
    }

    @Test
    public void testComplexList1() {
        TestComplexSrcList1 src = new TestComplexSrcList1();
        List<TestData3> testData3s = new ArrayList<>();
        TestData3 testData3 = new TestData3();
        testData3.setCcc(333L);
        testData3s.add(testData3);
        src.setComplexs(testData3s);


        TestComplexDestList1 dests = ValueMapping.from(TestComplexDestList1.class, src);
        Assert.assertEquals("333", dests.getComplexs().get(0).getCcc());

        Assert.assertEquals(TestData4.class, dests.getComplexs().get(0).getClass());
    }


    @Test
    public void testComplexList2() {
        TestComplexDestList1 src = new TestComplexDestList1();
        List<TestData4> testData4s = new ArrayList<>();
        TestData4 testData3 = new TestData4();
        testData3.setCcc("333");
        testData4s.add(testData3);
        src.setComplexs(testData4s);


        TestComplexSrcList1 dests = ValueMapping.from(TestComplexSrcList1.class, src);
        Assert.assertEquals(Long.valueOf(333L), dests.getComplexs().get(0).getCcc());

        Assert.assertEquals(TestData3.class, dests.getComplexs().get(0).getClass());
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

    public static class TestData2 {

        int bbb;

        public int getBbb() {
            return bbb;
        }

        public void setBbb(int bbb) {
            this.bbb = bbb;
        }
    }

    public static class TestData3 {

        Long ccc;

        public Long getCcc() {
            return ccc;
        }

        public void setCcc(Long ccc) {
            this.ccc = ccc;
        }
    }

    public static class TestData4 {

        String ccc;

        public String getCcc() {
            return ccc;
        }

        public void setCcc(String ccc) {
            this.ccc = ccc;
        }
    }

    public static class TestComplexSrc1 {

        TestData3 complex;

        public TestData3 getComplex() {
            return complex;
        }

        public void setComplex(TestData3 complex) {
            this.complex = complex;
        }
    }

    public static class TestComplexDest1 {
        TestData4 complex;

        public TestData4 getComplex() {
            return complex;
        }

        public void setComplex(TestData4 complex) {
            this.complex = complex;
        }
    }

    public static class TestComplexSrcList1 {

        List<TestData3> complexs;

        public List<TestData3> getComplexs() {
            return complexs;
        }

        public void setComplexs(List<TestData3> complexs) {
            this.complexs = complexs;
        }
    }

    public static class TestComplexDestList1 {
        List<TestData4> complexs;

        public List<TestData4> getComplexs() {
            return complexs;
        }

        public void setComplexs(List<TestData4> complexs) {
            this.complexs = complexs;
        }
    }


}

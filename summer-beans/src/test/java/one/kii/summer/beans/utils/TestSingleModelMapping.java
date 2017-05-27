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
public class TestSingleModelMapping {


    @Test
    public void test1() {
        List<TestData> test1 = new ArrayList<>();
        TestData td1 = new TestData();
        td1.setAaa("aaa");

        TestData td2 = new TestData();
        td2.setAaa("bbb");
        test1.add(td1);
        test1.add(td2);

        List<TestData> test2 = SingleModelMapping.from(TestData.class, test1);
        Assert.assertEquals(test1.size(), test2.size());
        Assert.assertEquals("aaa", test1.get(0).getAaa());
        Assert.assertEquals("bbb", test2.get(1).getAaa());
    }

    @Test
    public void test2() {
        Map map = new HashMap();
        map.put("bbb", 111);

        TestData2 td = SingleModelMapping.from(TestData2.class, map);
        Assert.assertEquals(111, td.getBbb());
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
}

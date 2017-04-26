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
        Assert.assertEquals("aaa",test1.get(0).getAaa());
        Assert.assertEquals("bbb",test2.get(1).getAaa());
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
}

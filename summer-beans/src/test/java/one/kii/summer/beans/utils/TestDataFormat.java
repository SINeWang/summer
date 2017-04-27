package one.kii.summer.beans.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by WangYanJiong on 25/04/2017.
 */
public class TestDataFormat {


    @Test
    public void test() {

        TestCase tc = new TestCase();
        TestCase newTc =  DataFormat.toLowerHyphen(tc);

        Assert.assertEquals("ab-cd", newTc.getSomeField());
        Assert.assertEquals(1, newTc.getI());

    }


    public static class TestCase {

        public TestCase() {
        }

        String someField = "abCd";
        int i = 1;

        public String getSomeField() {
            return someField;
        }

        public void setSomeField(String someField) {
            this.someField = someField;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }
}

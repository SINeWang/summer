package one.kii.summer.beans.utils;

import one.kii.summer.beans.annotations.ConflictFactor;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by WangYanJiong on 06/06/2017.
 */
public class TestConflictFactorTools {

    @Test
    public void test1() {

        String[] expect = {"fieldA=1", "fieldZ=2"};

        String[] actual = ConflictFactorTools.find(new TestKeys1());

        Assert.assertArrayEquals(expect, actual);
    }

    @Test
    public void test2() {

        String[] expect = {"fieldA", "fieldZ"};

        String[] actual = ConflictFactorTools.find(TestKeys2.class);

        Assert.assertArrayEquals(expect, actual);
    }

    public static class TestKeys1 {

        String fieldB;


        @ConflictFactor
        Long fieldZ = 2L;

        @ConflictFactor
        private String fieldA = "1";

        public String getFieldA() {
            return fieldA;
        }
    }

    public static class TestKeys2 {

        @ConflictFactor
        String fieldA = "1";

        String fieldB;

        @ConflictFactor
        Long fieldZ = 2L;
    }
}

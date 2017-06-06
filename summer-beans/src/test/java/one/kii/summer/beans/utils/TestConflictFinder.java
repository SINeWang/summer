package one.kii.summer.beans.utils;

import one.kii.summer.beans.annotations.ConflictFactor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Created by WangYanJiong on 06/06/2017.
 */
public class TestConflictFinder {

    @Test
    public void test1() {

        Map<String, Object> actual = ConflictFinder.find(new TestKeys1());

        Assert.assertEquals(2L, actual.get("fieldZ"));

        Assert.assertEquals("1", actual.get("fieldA"));
    }

    @Test
    public void test2() {

        String[] expect = {"fieldA", "fieldZ"};

        String[] actual = ConflictFinder.find(TestKeys2.class);

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

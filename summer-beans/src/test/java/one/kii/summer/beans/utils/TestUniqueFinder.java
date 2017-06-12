package one.kii.summer.beans.utils;

import one.kii.summer.beans.annotations.Unique;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * Created by WangYanJiong on 06/06/2017.
 */
public class TestUniqueFinder {

    @Test
    public void test1() {

        MultiValueMap<String, String> actual = UniqueFinder.find(new TestKeys1());

        Assert.assertEquals("2", actual.getFirst("fieldZ"));

        Assert.assertEquals("1", actual.getFirst("fieldA"));
    }

    @Test
    public void test2() {

        String[] expect = {"fieldA", "fieldZ"};

        String[] actual = UniqueFinder.find(TestKeys2.class);

        Assert.assertArrayEquals(expect, actual);
    }

    public static class TestKeys1 {

        String fieldB;


        @Unique
        Long fieldZ = 2L;

        @Unique
        private String fieldA = "1";

        public String getFieldA() {
            return fieldA;
        }
    }

    public static class TestKeys2 {

        @Unique
        String fieldA = "1";

        String fieldB;

        @Unique
        Long fieldZ = 2L;
    }
}

package one.kii.summer.beans.utils;

import one.kii.summer.beans.annotations.KeyFactor;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by WangYanJiong on 18/05/2017.
 */
public class TestKeyFactorTools {

    @Test
    public void test1(){

        String[] expect = {"fieldA", "fieldZ"};

        String[] actual =  KeyFactorTools.find(new TestKeys());

        Assert.assertArrayEquals(expect, actual);
    }

    @Test
    public void test2(){

        String[] expect = {"fieldA", "fieldZ"};

        String[] actual =  KeyFactorTools.find(TestKeys.class);

        Assert.assertArrayEquals(expect, actual);
    }



    public static class TestKeys{

        @KeyFactor
        String fieldA;

        String fieldB;

        @KeyFactor
        String fieldZ;
    }
}

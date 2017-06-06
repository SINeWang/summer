package one.kii.summer.beans.utils;

import one.kii.summer.beans.annotations.Commit;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by WangYanJiong on 18/05/2017.
 */
public class TestCommitCalculator {

    @Test
    public void test1(){

        String[] expect = {"fieldA", "fieldZ"};

        String[] actual =  CommitCalculator.find(new TestKeys());

        Assert.assertArrayEquals(expect, actual);
    }

    @Test
    public void test2(){

        String[] expect = {"fieldA", "fieldZ"};

        String[] actual =  CommitCalculator.find(TestKeys.class);

        Assert.assertArrayEquals(expect, actual);
    }

    public static class TestKeys{

        @Commit
        String fieldA;

        String fieldB;

        @Commit
        String fieldZ;
    }
}

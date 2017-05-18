package one.kii.summer.beans.utils;

import one.kii.summer.beans.annotations.KeyFactor;
import org.junit.Assert;
import org.junit.Test;

import static one.kii.summer.beans.utils.HashTools.NULL_HEX;


/**
 * Created by WangYanJiong on 04/05/2017.
 */
public class TestHashTools {

    @Test
    public void testHashHex() {
        String hash1 = HashTools.hashHex("");
        Assert.assertEquals(NULL_HEX, hash1);

        String hash2 = HashTools.hashHex((String) null);
        Assert.assertEquals(NULL_HEX, hash2);
    }

    @Test
    public void testHashHexObject() {
        String hash1 = HashTools.hashHex(new TestNoFactorObject());
        Assert.assertEquals(NULL_HEX, hash1);

        String hash2 = HashTools.hashHex(new TestSingleFactorObject());
        Assert.assertNotNull(hash2);
        Assert.assertNotEquals(NULL_HEX, hash2);

        String hash3 = HashTools.hashHex(new TestDoubleFactorObject());
        Assert.assertNotNull(hash2);
        Assert.assertNotEquals(NULL_HEX, hash3);
        Assert.assertNotEquals(hash2, hash3);

        String hash4 = HashTools.hashHex(new TestPartFactorObject());
        Assert.assertNotNull(hash4);
        Assert.assertNotEquals(NULL_HEX, hash4);
        Assert.assertEquals(hash4, hash3);

        String hash5 = HashTools.hashHex(new TestPartFactorPrimitiveObject());
        Assert.assertNotNull(hash4);
        Assert.assertNotEquals(NULL_HEX, hash4);
        Assert.assertNotEquals(hash4, hash5);
    }

    public static class TestNoFactorObject {
        String field = "abc";

    }

    public static class TestSingleFactorObject {
        @KeyFactor
        String field = "abc";

    }

    public static class TestDoubleFactorObject {
        @KeyFactor
        String field1 = "abc";

        @KeyFactor
        String field2 = "def";

    }

    public static class TestPartFactorObject {
        @KeyFactor
        String field1 = "abc";

        @KeyFactor
        String field2 = "def";

        String field3 = "ghi";
    }

    public static class TestPartFactorPrimitiveObject {
        @KeyFactor
        String field1 = "abc";

        @KeyFactor
        String field2 = "def";

        String field3 = "ghi";

        @KeyFactor
        int field4 = 1000;

        @KeyFactor
        boolean field5 = true;

    }

}

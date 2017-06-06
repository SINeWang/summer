package one.kii.summer.beans.utils;

import one.kii.summer.beans.annotations.Commit;
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
        @Commit
        String field = "abc";

    }

    public static class TestDoubleFactorObject {
        @Commit
        String field1 = "abc";

        @Commit
        String field2 = "def";

    }

    public static class TestPartFactorObject {
        @Commit
        String field1 = "abc";

        @Commit
        String field2 = "def";

        String field3 = "ghi";
    }

    public static class TestPartFactorPrimitiveObject {
        @Commit
        String field1 = "abc";

        @Commit
        String field2 = "def";

        String field3 = "ghi";

        @Commit
        int field4 = 1000;

        @Commit
        boolean field5 = true;

    }

}

package one.kii.summer.io.utils;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

/**
 * Created by WangYanJiong on 29/04/2017.
 */
public class TestMultiValueForm {

    private static String EXPECTED_SINGLE = "single";

    private static String[] EXPECTED_MULTI = new String[]{"a", "lot", "of"};

    @Test
    public void testFrom() {
        MultiValueMap map = MultiValueForm.from(new TestMultiValue());

        String single = map.getFirst("single").toString();

        Assert.assertEquals(EXPECTED_SINGLE, single);

        Arrays.sort(EXPECTED_MULTI);

        List<String> objects = (List<String>) map.get("multi");

        String[] objects1 = objects.toArray(new String[0]);

        Assert.assertArrayEquals(EXPECTED_MULTI, objects1);

        Assert.assertEquals("true", map.getFirst("tf").toString());
    }

    @Test
    public void testNull() {
        MultiValueMap map = MultiValueForm.from(null);
        Assert.assertNotNull(map);
        Assert.assertEquals(0, map.size());
    }


    public static class TestMultiValue {

        String single = EXPECTED_SINGLE;
        String[] multi = EXPECTED_MULTI;

        boolean tf = true;

        public boolean isTf() {
            return tf;
        }

        public void setTf(boolean tf) {
            this.tf = tf;
        }

        public String getSingle() {
            return single;
        }

        public void setSingle(String single) {
            this.single = single;
        }

        public String[] getMulti() {
            return multi;
        }

        public void setMulti(String[] multi) {
            this.multi = multi;
        }
    }
}

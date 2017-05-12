package one.kii.summer.beans.utils;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.MultiValueMap;

/**
 * Created by WangYanJiong on 12/05/2017.
 */
public class TestMultiValueMapTools {

    @Test
    public void testFrom() {
        MultiValueMap<String, String> multiValueMap =  MultiValueMapTools.from("{\"group\":null,\"name\":null,\"tree\":null,\"instances\":[\"11\",\"22\",\"33\"],\"complex\":[{\"aaa\":\"111\"},{\"bbb\":\"222\"}]}");
        Assert.assertNotNull(multiValueMap);

        Assert.assertNull(multiValueMap.get("name"));
        Assert.assertNull(multiValueMap.get("tree"));
        Assert.assertNull(multiValueMap.get("group"));
        Assert.assertNull(multiValueMap.get("complex"));
        Assert.assertEquals(3, multiValueMap.get("instances").size());
    }
}

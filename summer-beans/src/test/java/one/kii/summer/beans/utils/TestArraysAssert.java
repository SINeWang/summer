package one.kii.summer.beans.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangYanJiong on 18/04/2017.
 */


public class TestArraysAssert {

    @Test
    public void test() {
        String[] array1 = {"a"};
        List<ComplexType> list1 = new ArrayList<>();

        list1.add(new ComplexType("a"));
        boolean equal1 = ArraysAssert.same(array1, list1, "someField");
        Assert.assertTrue(equal1);


        list1.add(new ComplexType("b"));
        boolean equal2 = ArraysAssert.same(array1, list1, "someField");
        Assert.assertFalse(equal2);


        String[] array2 = {"b", "a"};
        boolean equal3 = ArraysAssert.same(array2, list1, "someField");

        Assert.assertTrue(equal3);
    }

    class ComplexType {
        String someField;

        public ComplexType(String someField) {
            this.someField = someField;
        }

        public String getSomeField() {
            return someField;
        }
    }
}

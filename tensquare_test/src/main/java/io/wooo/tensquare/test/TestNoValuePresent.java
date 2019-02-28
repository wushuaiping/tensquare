package io.wooo.tensquare.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: wushuaiping
 * @date: 2019/2/20 16:09
 * @description:
 */
public class TestNoValuePresent {

    public static void main(String[] args) {
        Set<Person> bigDecimals = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        map.put("AAA", 11);
        bigDecimals.parallelStream().forEach(person -> {
            map.get(person.getName());
        });
    }

    class Person {
        private int age;
        private String name;

        public int getAge() {
            return this.age;
        }

        public String getName() {
            return this.name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

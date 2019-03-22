package io.wooo.tensquare.test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
//        Set<Person> bigDecimals = new HashSet<>();
//        Map<String, Integer> map = new HashMap<>();
//        map.put("AAA", 11);
//        bigDecimals.parallelStream().forEach(person -> {
//            map.get(person.getName());
//        });

        final Long timestamp = getTimestamp(LocalDateTime.of(2018, 1, 1, 0, 0));
        final Long timestamp1 = getTimestamp(LocalDateTime.of(2018, 12, 31, 23, 59));
        System.out.println(timestamp);
        System.out.println(timestamp1);
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

    private static Long getTimestamp(LocalDateTime localDateTime){
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
}

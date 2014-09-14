package com.litao.guava;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;

/**
 * Created by Tao Li on 9/9/14.
 */
public class HelloGuava {
    public static void main(String[] args) {
        Optional<Integer> possible = Optional.of(5);
        System.out.println(possible.isPresent());
        System.out.println(possible.get());

        List<String> theseElements = Lists.newArrayList("alpha", "beta", "gamma");
        System.out.println(theseElements);
    }
}

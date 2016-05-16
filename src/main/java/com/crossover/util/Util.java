package com.crossover.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Elkfrawy on 5/16/2016.
 */
public class Util {
    public static <E> List<E> makeCollection(Iterable<E> iter) {
        List<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }
}

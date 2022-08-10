package csproblem.injava.chapter2;

import java.util.List;

public class GenericSearch {

    private GenericSearch() {
    }

    public static <T extends Comparable<T>> boolean linearContains(List<T> list, T key) {
        for (T t : list) {
            if (t.compareTo(key) == 0) {
                return true;
            }
        }
        return false;
    }
}

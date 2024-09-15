package human.comparators;

import human.Human;

import java.util.Comparator;

public class ComparatorByBirthday<T extends Human> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        return o1.getBirthday().compareTo(o2.getBirthday());
    }
}

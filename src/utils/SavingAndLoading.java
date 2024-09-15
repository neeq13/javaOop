package utils;

import human.Human;

import java.util.List;

public interface SavingAndLoading {
    boolean save(List<Human> humans);
    List<Human> read();
}

package tree;

import human.Human;
import human.comparators.ComparatorByBirthday;
import human.comparators.ComparatorByName;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class FamilyTree<T extends Human> implements Iterable<T> {
    private final List<T> humanList;

    public  FamilyTree(List<T> list){
        humanList = list;
    }

    public T search(String str){
        return humanList.stream().filter(human -> human.getName().toLowerCase().equals(str.toLowerCase())).findFirst().get();
    }

    public boolean add(T t){
        if (!humanList.contains(t)){
            humanList.add(t);
            return true;
        }
        return false;
    }

    public boolean addChild(T mather, T father, T child){
        if (Objects.nonNull(child.getFather()) && Objects.nonNull(child.getMother())){
            return false;
        }
        if (Objects.nonNull(child.getFather())){
            return false;
        }else {
            child.setFather(father);
            father.getChildren().add(child);
        }
        if (Objects.nonNull(child.getMother())){
            return false;
        }{
            child.setMother(mather);
            mather.getChildren().add(child);
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return humanList.iterator();
    }

    public void sortByName(){
        humanList.sort(new ComparatorByName<>());
    }

    public void sortByBirthday(){
        humanList.sort(new ComparatorByBirthday<>());
    }

    public List<T> getHumanList() {
        return humanList;
    }
}

package tree;

import human.Human;
import human.comparators.ComparatorByBirthday;
import human.comparators.ComparatorByName;
import utils.SavingAndLoading;
import utils.WriteAndRead;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class FamilyTree implements Iterable<Human> {
    private final List<Human> humanList;
    private final SavingAndLoading writeAndRead;

    public  FamilyTree(){
        writeAndRead = new WriteAndRead();
        humanList = writeAndRead.read();
    }

    public Human search(String str){
        return humanList.stream().filter(human -> human.getName().toLowerCase().equals(str.toLowerCase())).findFirst().get();
    }

    public boolean add(Human human){
        if (!humanList.contains(human)){
            humanList.add(human);
            return true;
        }
        return false;
    }

    public boolean addChild(Human mather, Human father, Human child){
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

    public boolean save(){
        return writeAndRead.save(humanList);
    }

    public List<Human> read(){
        humanList.addAll(writeAndRead.read());
        return humanList;
    }

    @Override
    public Iterator<Human> iterator() {
        return humanList.iterator();
    }

    public void sortByName(){
        humanList.sort(new ComparatorByName());
    }

    public void sortByBirthday(){
        humanList.sort(new ComparatorByBirthday());
    }

    public List<Human> getHumanList() {
        return humanList;
    }
}

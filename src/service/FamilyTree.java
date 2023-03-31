package service;

import human.Human;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FamilyTree {
    private final List<Human> humanList;

    public  FamilyTree(){
        humanList = new ArrayList<>();
    }

    public Human search(String str){
        return humanList.stream().filter(human -> human.getName().toLowerCase().equals(str.toLowerCase())).findFirst().get();
    }

    public String all(){
        StringBuilder all = new StringBuilder();
        all.append("Всего человек в древе: ");
        all.append(humanList.size());
        all.append("\n");
        for (Human human: humanList){
            all.append(human.getFullName());
            all.append("\n----------------");
            all.append("\n");
        }
        return all.toString();
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

}

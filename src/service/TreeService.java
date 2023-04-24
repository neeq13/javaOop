package service;

import human.Human;
import tree.FamilyTree;
import utils.SavingAndLoading;
import utils.WriteAndRead;

import java.util.List;
import java.util.Objects;

public class TreeService {
    private final SavingAndLoading writeAndRead;
    private final FamilyTree<Human> tree;

    public TreeService() {
        writeAndRead = new WriteAndRead();
        tree = new FamilyTree<>(writeAndRead.read());
    }

    public String sorted(boolean check) {
        if (check){
            tree.sortByName();
        }else {
            tree.sortByBirthday();
        }
        return all();
    }

    public void addChild(List<Human> humans) {
        tree.addChild(humans.get(1), humans.get(0), humans.get(2));
    }

    public Human search(String name){
        return tree.search(name);
    }

    public Human add(Human human){
        tree.add(human);
        return human;
    }

    public String all(){
        StringBuilder all = new StringBuilder();
        all.append("Всего человек в древе: ");
        all.append(tree.getHumanList().size());
        all.append("\n");
        for (Human human: tree){
            all.append(human.getFullName());
            all.append(", г.р. ");
            all.append(human.getBirthday());
            all.append(" ");
            if (Objects.nonNull(human.getDied())){
                all.append("умер: ");
                all.append(human.getDied());
            }
            all.append("\n----------------");
            all.append("\n");
        }
        return all.toString();
    }

    public void save() {
        writeAndRead.save(tree.getHumanList());
    }
}

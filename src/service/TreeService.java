package service;

import human.Human;
import human.Sex;
import tree.FamilyTree;
import ui.Menu;
import ui.View;
import utils.SavingAndLoading;
import utils.WriteAndRead;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class TreeService implements View {
    private Scanner scanner;
    private final SavingAndLoading writeAndRead;
    private final FamilyTree<Human> tree;
    private boolean flag;

    public TreeService() {
        writeAndRead = new WriteAndRead();
        tree = new FamilyTree<>(writeAndRead.read());
    }

    public void start(){
        scanner = new Scanner(System.in);
        Menu menu = new Menu(this);
        flag = true;
        while (flag){
            print(menu.printMenu());
            String command = scanner.nextLine();
            if (checkInput(command)){
                menu.execute(Integer.parseInt(command));
            } else {
                System.out.println("что-то пошло не так");
            }
        }
        scanner.close();
    }


    private boolean checkInput(String text){
        return text.matches("[0-9]+");
    }

    public void sorted() {
        System.out.println("1. Сортировать по имени 2. Сортировать по дате рождения");
        String command = scanner.nextLine();
        if (checkInput(command)){
            tree.sortByName();
        }else {
            tree.sortByBirthday();
        }
        System.out.println(all());
    }

    public void exit() {
        save();
        flag = false;
    }

    @Override
    public void print(String text) {
        System.out.println(text);
    }

    public void addChild() {
        int temp;
        Human mother = null;
        Human father = null;
        Human child = null;
        System.out.print("""
                Если есть данные об отце:
                1. Он уже есть в генеалогическом древе?
                2. Данные имеются, но в древо он ещё не внесён
                3. Данных не имеется:\s""");
        temp = scanner.nextInt();
        if(temp == 1){
            father = search();
        }else if(temp == 2){
            father = add();
        }
        System.out.print("""
                Если есть данные о матери:
                1. Она уже есть в генеалогическом древе?
                2. Данные имеются, но в древо она ещё не внесена
                3. Данных не имеется:\s""");
        temp = scanner.nextInt();
        if(temp == 1){
            mother = search();
        }else if(temp == 2){
            mother = add();
        }
        System.out.print("""
                Данные о ребёнке:
                1. Он уже есть в генеалогическом древе?
                2. Данные имеются, но в древо он ещё не внесён\s""");
        temp = scanner.nextInt();
        if(temp == 1){
            child = search();
        }else if(temp == 2){
            child = add();
        }
        assert child != null;
        tree.addChild(mother, father, child);
    }

    public Human search(){
        scanner.nextLine();
        System.out.println("Введите имя человека");
        String str = scanner.nextLine();
        return tree.search(str);
    }

    public Human add(){
        Human newHuman;
        Sex sex;
        LocalDate died = null;
        System.out.println("""
                Введите данные человека в формате:Фамилия Имя Отчество,\s
                дата рождения(год месяц число),\s
                дата смерти(год месяц число) если человек уже умер""");
        String[] str = scanner.nextLine().split(" ");
        String name = str[2];
        String patronymic = str[1];
        String surname = str[0];
        LocalDate birthday = LocalDate.of(Integer.parseInt(str[3]), Integer.parseInt(str[4]), Integer.parseInt(str[5]));
        if(str.length > 6) {
            died = LocalDate.of(Integer.parseInt(str[6]), Integer.parseInt(str[7]), Integer.parseInt(str[8]));
        }
        System.out.println("Выберите пол: 1. Мужской 2. Женский");
        String command = scanner.nextLine();
        if (checkInput(command)){
            sex = Sex.MAN;
        }else {
            sex= Sex.WOMEN;
        }

        newHuman = new Human(name, surname, patronymic, sex, birthday, died);
        tree.add(newHuman);
        return newHuman;
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

    public void save(){
        writeAndRead.save(tree.getHumanList());
    }
}

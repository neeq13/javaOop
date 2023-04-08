package service;

import human.Human;
import human.Sex;
import tree.FamilyTree;
import utils.SavingAndLoading;
import utils.WriteAndRead;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class TreeService {
    private final Scanner scanner = new Scanner(System.in);
    private final SavingAndLoading writeAndRead;
    private final FamilyTree<Human> tree;

    public TreeService() {
        writeAndRead = new WriteAndRead();
        tree = new FamilyTree<>(writeAndRead.read());
    }

    public void start(){
//        tree.add(new Human("Алексей", "Романов", "Николаевич", Sex.MAN, LocalDate.of(1904, 8, 12), LocalDate.of(1918, 7, 17)));
//        tree.add(new Human("Николай", "Романов", "Александрович", Sex.MAN, LocalDate.of(1868, 5, 18), LocalDate.of(1918, 7, 17)));
//        tree.add(new Human("Александра", "Романова", "Фёдоровна", Sex.WOMEN, LocalDate.of(1872, 6, 6), LocalDate.of(1918, 7, 17)));
//        tree.add(new Human("Александр", "Романов", "Александрович", Sex.MAN, LocalDate.of(1845, 3, 10), LocalDate.of(1894, 11, 1)));
//        tree.add(new Human("Мария", "Романова", "Фёдоровна", Sex.WOMEN, LocalDate.of(1847, 11, 26), LocalDate.of(1928, 10, 13)));
//        tree.addChild(tree.search("Мария"), tree.search("Александр"), tree.search("Николай"));
//        tree.addChild(tree.search("Александра"), tree.search("Николай"), tree.search("Алексей"));
        boolean flag = true;
        while (flag){
            System.out.println("""
                    1. Вывести всё генеалогическое древо
                    2. Поиск конкретного человека по его имени
                    3. Добавить нового члена семьи
                    4. Добавить ребёнка
                    0. Выход""");
            System.out.print("Ваш выбор: ");
            int result = scanner.nextInt();
            switch (result) {
                case 1 -> {
                    System.out.println("1. Сортировать по имени 2. Сортировать по дате рождения");
                    int temp = scanner.nextInt();
                    if (temp == 1){
                        tree.sortByName();
                    }else {
                        tree.sortByBirthday();
                    }
                    System.out.println(all());
                }
                case 2 -> System.out.println(search().getInfo());
                case 3 -> {
                    add().getInfo();
                    save();
                }
                case 4 -> {
                    if(addChild()){
                        System.out.println("Родственные связи успешно установлены");
                        save();
                    } else {
                        System.out.println("Что-то пошло не так, возможно нужно проверить данные и повторить");
                    }
                }
                case 0 -> {
                    save();
                    flag = false;
                }
            }
        }
        scanner.close();
    }

    private boolean addChild() {
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
        return tree.addChild(mother, father, child);
    }

    private Human search(){
        scanner.nextLine();
        System.out.println("Введите имя человека");
        String str = scanner.nextLine();
        return tree.search(str);
    }

    private Human add(){
        int temp;
        Human newHuman;
        Sex sex;
        LocalDate died = null;
        scanner.nextLine();
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
        temp = scanner.nextInt();
        if(temp == 1){
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

    public boolean save(){
        return writeAndRead.save(tree.getHumanList());
    }
}

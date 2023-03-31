package service;

import human.Human;
import human.Sex;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class StartTree {
    private final Scanner scanner = new Scanner(System.in);

    private final FamilyTree tree = new FamilyTree();

    public void start(){
        tree.add(new Human("Алексей", "Романов", "Николаевич", Sex.MAN, LocalDate.of(1904, 8, 12), LocalDate.of(1918, 7, 17)));
        tree.add(new Human("Николай", "Романов", "Александрович", Sex.MAN, LocalDate.of(1868, 5, 18), LocalDate.of(1918, 7, 17)));
        tree.add(new Human("Александра", "Романова", "Фёдоровна", Sex.WOMEN, LocalDate.of(1872, 6, 6), LocalDate.of(1918, 7, 17)));
        tree.add(new Human("Александр", "Романов", "Александрович", Sex.MAN, LocalDate.of(1845, 3, 10), LocalDate.of(1894, 11, 1)));
        tree.add(new Human("Мария", "Романова", "Фёдоровна", Sex.WOMEN, LocalDate.of(1847, 11, 26), LocalDate.of(1928, 10, 13)));
        tree.addChild(tree.search("Мария"), tree.search("Александр"), tree.search("Николай"));
        tree.addChild(tree.search("Александра"), tree.search("Николай"), tree.search("Алексей"));

        boolean flag = true;
        while (flag){
            System.out.println("""
                    1. Вывести всё генеалогическое древо
                    2. Поиск конкретного человека по его имени
                    3. Добавить нового члена семьи
                    0. Выход""");
            System.out.print("Ваш выбор: ");
            int result = scanner.nextInt();
            switch (result) {
                case 1 -> System.out.println(tree.all());
                case 2 -> System.out.println(search().getInfo());
                case 3 -> add();
                case 0 -> flag = false;
            }
        }
        scanner.close();
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
        Human pater = null;
        Human mom = null;
        Human kid = null;
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
        System.out.print("""
                Если есть данные об отце:
                1. Он уже есть в генеалогическом древе?
                2. Данные имеются, но в древо он ещё не внесён
                3. Данных не имеется:\s""");
        temp = scanner.nextInt();
        if(temp == 1){
            pater = search();
        }else if(temp == 2){
            pater = add();
        }
        System.out.print("""
                Если есть данные о матери:
                1. Она уже есть в генеалогическом древе?
                2. Данные имеются, но в древо она ещё не внесена
                3. Данных не имеется:\s""");
        temp = scanner.nextInt();
        if(temp == 1){
            mom = search();
        }else if(temp == 2){
            mom = add();
        }
        System.out.print("""
                        Имеется ли ребёнок?
                        1. Да 2. Нет: \s""");
        if (scanner.nextInt() == 1) {
            System.out.print("""
                    Если есть данные о ребёнке:
                    1. Он уже есть в генеалогическом древе?
                    2. Данные имеются, но в древо он ещё не внесён
                    3. Данных не имеется:\s""");
            temp = scanner.nextInt();
            if(temp == 1){
                kid = search();
            }else if(temp == 2){
                kid = add();
            }
        }
        newHuman = new Human(name, surname, patronymic, sex, birthday, died, mom, pater);
        if (Objects.nonNull(kid)){
            newHuman.getChildren().add(kid);
        }
        tree.add(newHuman);
        return newHuman;
    }
}

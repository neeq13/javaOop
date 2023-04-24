package ui;

import Presenter.Presenter;
import human.Human;
import human.Sex;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console implements View {
    private static Scanner scanner;
    private static boolean flag = true;
    private static final Presenter presenter = new Presenter();

    public void start() {
        scanner = new Scanner(System.in);
        Menu menu = new Menu(presenter);

        while (flag) {
            print(menu.printMenu());
            String command = scanner.nextLine();
            if (checkInput(command)) {
                menu.execute(Integer.parseInt(command));
            } else {
                System.out.println("что-то пошло не так");
            }
        }
        scanner.close();
    }

    public static void exit(boolean check){
        flag = check;
    }


    private static boolean checkInput(String text) {
        return text.matches("[0-9]+");
    }

    public static boolean sorted() {
        System.out.println("1. Сортировать по имени 2. Сортировать по дате рождения");
        int temp = Integer.parseInt(scanner.nextLine());
        return temp == 1;
    }

    public static void print(String text) {
        System.out.println(text);
    }

    public static List<Human> addChild() {
        int temp;
        List<Human> humans = new ArrayList<>();
        System.out.print("""
                Если есть данные об отце:
                1. Он уже есть в генеалогическом древе?
                2. Данные имеются, но в древо он ещё не внесён
                3. Данных не имеется:\s""");
        temp = scanner.nextInt();
        if (temp == 1) {
            humans.add(presenter.search());
        } else if (temp == 2) {
            humans.add(addHuman());
        } else {
            humans.add(new Human());
        }
        System.out.print("""
                Если есть данные о матери:
                1. Она уже есть в генеалогическом древе?
                2. Данные имеются, но в древо она ещё не внесена
                3. Данных не имеется:\s""");
        temp = scanner.nextInt();
        if (temp == 1) {
            humans.add(presenter.search());
        } else if (temp == 2) {
            humans.add(addHuman());
        } else {
            humans.add(new Human());
        }
        System.out.print("""
                Данные о ребёнке:
                1. Он уже есть в генеалогическом древе?
                2. Данные имеются, но в древо он ещё не внесён\s""");
        temp = scanner.nextInt();
        if (temp == 1) {
            humans.add(presenter.search());
        } else {
            humans.add(addHuman());
        }
        return humans;
    }

    public static String search() {
        System.out.println("Введите имя человека");
        return scanner.nextLine();
    }

    public static Human addHuman() {
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
        if (str.length > 6) {
            died = LocalDate.of(Integer.parseInt(str[6]), Integer.parseInt(str[7]), Integer.parseInt(str[8]));
        }
        System.out.println("Выберите пол: 1. Мужской 2. Женский");
        String command = scanner.nextLine();
        if (checkInput(command)) {
            sex = Sex.MAN;
        } else {
            sex = Sex.WOMEN;
        }

        newHuman = new Human(name, surname, patronymic, sex, birthday, died);
        return newHuman;
    }
}

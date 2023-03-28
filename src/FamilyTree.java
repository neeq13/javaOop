import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FamilyTree {
    private final Scanner scanner = new Scanner(System.in);

    private Human father;
    private Human mother;
    private Human husband;
    private Human wife;
    private final List<Human> humanList = new ArrayList<>();

    public  FamilyTree(){
        Human child = new Human("Алексе́й", "Романов", "Николаевич", LocalDate.of(1904, 8, 12), LocalDate.of(1918, 7, 17), wife, husband, null);
        List<Human> generation = new ArrayList<>();
        generation.add(child);

        husband = new Human("Николай", "Романов", "Александрович", LocalDate.of(1868, 5, 18), LocalDate.of(1918, 7, 17), mother, father, generation);
        wife = new Human("Александра", "Романов", "Фёдоровна", LocalDate.of(1872, 6, 6), LocalDate.of(1918, 7, 17), null, null, generation);
        List<Human> children = new ArrayList<>();
        children.add(husband);

        father = new Human("Алекса́ндр", "Романов", "Александрович", LocalDate.of(1845, 3, 10), LocalDate.of(1894, 11, 1), null, null, children);
        mother = new Human("Мари́я", "Романов", "Фёдоровна", LocalDate.of(1847, 11, 26), LocalDate.of(1928, 10, 13), null, null, children);

        humanList.add(father);
        humanList.add(mother);
        humanList.add(husband);
        humanList.add(wife);
        humanList.add(child);
    }

    public void start(){
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
                case 1 -> System.out.println(humanList);
                case 2 -> System.out.println(search());
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
        return humanList.stream().filter(human -> human.getName().equals(str.toLowerCase())).findFirst().get();
    }

    private Human add(){
        Human newHuman;
        Human pater;
        Human mom;
        Human kid = new Human();
        LocalDate died = null;
        List<Human> babies = new ArrayList<>();
        scanner.nextLine();
        System.out.println("""
                Введите данные человека в формате: Имя Отчество Фамилия,\s
                дата рождения(год месяц число),\s
                дата смерти(год месяц число) если человек уже умер""");
        String[] str = scanner.nextLine().split(" ");
        String name = str[0];
        String patronymic = str[1];
        String surname = str[2];
        LocalDate birthday = LocalDate.of(Integer.parseInt(str[3]), Integer.parseInt(str[4]), Integer.parseInt(str[5]));
        if(str.length > 6) {
            died = LocalDate.of(Integer.parseInt(str[6]), Integer.parseInt(str[7]), Integer.parseInt(str[8]));
        }
        System.out.print("""
                Если есть данные об отце:
                1. Он уже есть в генеалогическом древе?
                2. Данные имеются, но в древо он ещё не внесён:\s""");
        if(scanner.nextInt() == 1){
            pater = search();
        }else {
            pater = add();
        }
        System.out.print("""
                Если есть данные о матери:
                1. Она уже есть в генеалогическом древе?
                2. Данные имеются, но в древо она ещё не внесена:\s""");
        if(scanner.nextInt() == 1){
            mom = search();
        }else {
            mom = add();
        }
        System.out.print("""
                        Имеется ли ребёнок?
                        1. Да 2. Нет: \s""");
        if (scanner.nextInt() == 1) {
            System.out.print("""
                    Если есть данные о ребёнке:
                    1. Он уже есть в генеалогическом древе?
                    2. Данные имеются, но в древо он ещё не внесён:\s""");
            if (scanner.nextInt() == 1) {
                kid = search();
            } else {
                kid = add();
            }
        }
        babies.add(kid);
        newHuman = new Human(name, surname, patronymic, birthday, died, mom, pater, babies);
        humanList.add(newHuman);
        return newHuman;
    }

}

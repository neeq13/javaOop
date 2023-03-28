import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Human {
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthday;
    private LocalDate died = null;
    private Human mother;
    private Human father;
    private List<Human> children;

    public Human(String name, String surname, String patronymic, LocalDate birthday, LocalDate died, Human mother, Human father, List<Human> children) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.died = died;
        this.mother = mother;
        this.father = father;
        this.children = children;
    }

    public Human(String name, String surname, String patronymic, LocalDate birthday,  Human mother, Human father, List<Human> children){
        this(name, surname, patronymic, birthday, null, mother, father, children);
    }

    public Human(){
        this(null, null, null, null, null, null, new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getDied() {
        return died;
    }

    public void setDied(LocalDate died) {
        this.died = died;
    }

    public Human getMother() {
        return mother;
    }

    public void setMother(Human mother) {
        this.mother = mother;
    }

    public Human getFather() {
        return father;
    }

    public void setFather(Human father) {
        this.father = father;
    }

    public List<Human> getChildren() {
        return children;
    }

    public void setChildren(List<Human> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        if (Objects.nonNull(died)){
            return "Human{" +
                    "name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", patronymic='" + patronymic + '\'' +
                    ", birthday=" + birthday +
                    ", mother=" + mother +
                    ", father=" + father +
                    ", children= " + children +
                    '}';
        }else {
            return "Human{" +
                    "name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", patronymic='" + patronymic + '\'' +
                    ", birthday=" + birthday +
                    ", died=" + died +
                    ", mother=" + mother +
                    ", father=" + father +
                    ", children= " + children.toString() +
                    '}';
        }
    }
}

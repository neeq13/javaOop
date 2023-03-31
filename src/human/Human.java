package human;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Human implements Serializable {
    UUID id = UUID.randomUUID();
    private String name;
    private String surname;
    private String patronymic;
    private final Sex sex;
    private LocalDate birthday;
    private LocalDate died;
    private Human mother;
    private Human father;
    private List<Human> children;

    public Human(String name, String surname, String patronymic, Sex sex, LocalDate birthday, LocalDate died, Human mother, Human father) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.sex = sex;
        this.birthday = birthday;
        this.died = died;
        this.mother = mother;
        this.father = father;
        this.children = new ArrayList<>();
    }

    public Human(String name, String surname, String patronymic, Sex sex, LocalDate birthday, Human mother, Human father){
        this(name, surname, patronymic, sex, birthday, null, mother, father);
    }

    public Human(String name, String surname, String patronymic, Sex sex, LocalDate birthday, LocalDate died){
        this(name, surname, patronymic, sex, birthday, died, null, null);
    }
    public Human(String name, String surname, String patronymic, Sex sex, LocalDate birthday){
        this(name, surname, patronymic, sex, birthday, null, null, null);
    }

    public Human(){
        this(null, null, null, null, null, null, null);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Human human)){
            return false;
        }
        return human.getId().equals(getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    public String getInfo(){
        return "ФИО: " +
                getSurname() +
                " " +
                getName() +
                " " +
                getPatronymic() +
                "\n" +
                "Родился: " +
                getBirthday() +
                "\n" +
                "Умер: " +
                isAlive() +
                "\n" +
                "Мать: " +
                motherIdent() +
                "\n" +
                "Отец: " +
                fatherIdent() +
                "\n" +
                "Дети: " +
                childIdent();
    }

    private String motherIdent() {
        if (Objects.nonNull(getMother())){
            return getMother().getFullName();
        }
        return "Неизвестна";
    }

    private String fatherIdent() {
        if (Objects.nonNull(getFather())){
            return getFather().getFullName();
        }
        return "Неизвестен";
    }

    private String childIdent() {
        StringBuilder result = new StringBuilder();
        if (!children.isEmpty()){
            for (Human human: children){
                result.append(human.getFullName());
                result.append("\n");
            }
            return result.toString();
        }
        return "Нет";
    }

    private String isAlive() {
        if (Objects.isNull(getDied())) {
            return "Жив, пока ещё!";
        }
        return getDied().toString();
    }

    public String getFullName(){
        return getSurname() + " " + getName() + " " + getPatronymic();
    }

    public Sex getSex() {
        return sex;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

}

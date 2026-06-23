class Human {

    private int age;
    private String name;

    public Human() {
        age = 12;
        name = "John";
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class Main {

    public static void main(String[] args) {

        Human obj = new Human();

        obj.setAge(11);
        obj.setName("Navin");

        System.out.println(obj.getName() + " : " + obj.getAge());
    }
}

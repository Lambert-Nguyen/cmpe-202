package uml;

public class Dog {
    private String name;
    private int age = 0;
    private String breed;

    public void bark(){
        System.out.println("Woof!");
    }
    public void eat() {
        System.out.println("Yum yum!");
    }
    public void setAge(int newAge){
        this.age = newAge;
    }
    public int getAge(){
        return this.age;
    }
}
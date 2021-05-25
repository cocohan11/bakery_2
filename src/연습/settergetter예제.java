package 연습;

public class settergetter예제 {

    private String name = "김한이";
    private int age = 27;
    private boolean happy = true;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isHappy() {
        return happy;
    }


    public void setAge(int age) {
        this.age = age;
    }

}

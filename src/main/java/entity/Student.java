package entity;


import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private Integer id;
    private String first_name;
    private Integer age;
    private Date birth_date;
    private Course course;

    public Student(Integer id, String first_name, Integer age, Date birth_date, Course course) {
        this.id = id;
        this.first_name = first_name;
        this.age = age;
        this.birth_date = birth_date;
        this.course = course;
    }
    public Student(){

    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":%d," +
                "\n\"firstName\":\"%s\", " +
                "\n\"course\":\"%s\"," +
                "\n\"age\":%d}", id, first_name, course, age);
    }
}

package entity;


import java.util.Date;

public class Student {
    private Integer id;
    private String first_name;
    private Integer age;
    private Date birth_date;
    private String course;

    public Student(Integer id, String first_name, Integer age, Date birth_date, String course) {
        this.id = id;
        this.first_name = first_name;
        this.age = age;
        this.birth_date = birth_date;
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
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

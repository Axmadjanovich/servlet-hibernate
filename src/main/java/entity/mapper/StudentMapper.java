package entity.mapper;

import entity.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setAge(rs.getInt("age"));
        student.setBirth_date(rs.getDate("birth_date"));
        student.setFirst_name(rs.getString("first_name"));
        student.setCourse(rs.getString("course"));

        return student;
    }
}

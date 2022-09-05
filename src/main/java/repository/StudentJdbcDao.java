package repository;

import congifuration.DatabaseConfiguration;
import entity.Student;
import entity.mapper.StudentMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class StudentJdbcDao {

    private JdbcTemplate jdbcTemplate;

    public StudentJdbcDao(){
        DataSource dataSource = DatabaseConfiguration.getInstance().postgres();

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Student> getAll(){
        return jdbcTemplate.query("select * from student", new StudentMapper());
    }
}

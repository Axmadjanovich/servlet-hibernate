package repository;

import congifuration.DatabaseConfiguration;
import entity.Student;
import entity.mapper.StudentMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import javax.sql.DataSource;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class StudentJdbcDao {

    private JdbcTemplate jdbcTemplate;

    public StudentJdbcDao(){
        DataSource dataSource = DatabaseConfiguration.getInstance().postgres();

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Student> getAll(TreeMap<String, String> params){
        String query = "select * from student where 1=1 ";

        StringBuilder queryParams = new StringBuilder();
        buildParams(params, queryParams);

        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                setParameters(ps, params);
            }
        };

        return jdbcTemplate.query(query + queryParams, preparedStatementSetter, new StudentMapper());
    }

    public List<Student> getAllWithPrepared(TreeMap<String, String> params) throws ClassNotFoundException, SQLException {
        getClass().getClassLoader().loadClass("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "imueaa0131");


        {
            String query = "select * from student where 1=1 ";

            StringBuilder queryParams = new StringBuilder();
            buildParams(params, queryParams);

            PreparedStatement ps = connection.prepareStatement(query + queryParams);
            setParameters(ps, params);

            ResultSet rs = ps.executeQuery();

            List<Student> students = new ArrayList<>();

            while (rs.next()) {
               Student student = new StudentMapper().mapRow(rs, rs.getRow());
               students.add(student);
            }

            return students;
        }

    }

    public Integer add(Student student){
        return jdbcTemplate.update("insert into student (age, birth_date, course, first_name) values (?, ?, ?, ?)",
                student.getAge(),
                student.getBirth_date(),
                student.getCourse(),
                student.getFirst_name());
    }

    private void buildParams(TreeMap<String, String> params, StringBuilder queryParams){
        if (params.containsKey("age")){
            queryParams.append(" AND age = ?");
        }
        if (params.containsKey("birth_date")){
            queryParams.append(" AND birth_date = ?");
        }
        if (params.containsKey("course")){
            queryParams.append(" AND course = ?");
        }
        if (params.containsKey("first_name")){
            queryParams.append(" AND first_name = ?");
        }
        if (params.containsKey("id")){
            queryParams.append(" AND id = ?");
        }
    }// and age = 13 and course = java and id = 5

    private void setParameters(PreparedStatement ps, TreeMap<String, String> params){
        AtomicInteger index = new AtomicInteger(1);

            params.forEach((k, v) -> {
                try {
                    switch (k) {
                        case "id":
                        case "age":
                            ps.setInt(index.getAndIncrement(), Integer.parseInt(v));
                            break;
                        case "birth_date" :
                            ps.setDate(index.getAndIncrement(), (Date) new SimpleDateFormat("dd.MM.yyyy").parse(v));
                            break;
                        case "course":
                        case "first_name":
                            ps.setString(index.getAndIncrement(), v);
                            break;
                    }

                }catch (ParseException | SQLException e){
                    e.printStackTrace();
                }
            });
    }
}

package servlet;

import entity.Student;
import repository.StudentJdbcDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class StudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentJdbcDao studentJdbcDao = new StudentJdbcDao();
        List<Student> students = studentJdbcDao.getAll();

        resp.setContentType("application/json");

        PrintWriter out = resp.getWriter();
        out.print(students);

        out.close();
    }
}

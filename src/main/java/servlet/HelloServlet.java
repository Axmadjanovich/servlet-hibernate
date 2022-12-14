package servlet;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Student;
import repository.StudentRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloServlet extends HttpServlet{

    @Override
    public void init() throws ServletException {
        System.out.println("Servlet created: " + new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
    }

    @Override
    public void destroy() {
        System.out.println("Servlet destroyed: " + new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        PrintWriter out = resp.getWriter();
        out.println("{\"Hello\":\"name\"}");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        byte[] body = req.getInputStream().readAllBytes();
        String bodyStr = new String(body);

        System.out.println(bodyStr);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonParser jsonParser = objectMapper.createParser(bodyStr);
        Student student = jsonParser.readValueAs(Student.class);

//        Student student = new Student(null, "Ulug'bek", 22, new Date(), "Java");
//
        StudentRepository repo = StudentRepository.getInstance();
        repo.addStudent(student);

        PrintWriter out = resp.getWriter();
        out.println(student);
    }
}

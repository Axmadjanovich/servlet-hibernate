package servlet;

import entity.Student;
import repository.StudentJdbcDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class StudentServlet extends HttpServlet {

    private StudentJdbcDao studentJdbcDao;

    @Override
    public void init() throws ServletException {
        studentJdbcDao = new StudentJdbcDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> paramNames = req.getParameterNames();
        TreeMap<String, String> params = new TreeMap<>();
        while (paramNames.hasMoreElements()){
            String name = paramNames.nextElement();
            params.put(name, req.getParameter(name));
        }

        System.out.println(params);

        List<Student> students = studentJdbcDao.getAll(params);

        resp.setContentType("application/json");

        PrintWriter out = resp.getWriter();
        out.print(students);

        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        byte[] body = req.getInputStream().readAllBytes();
        String name = req.getParameter("name"); // => @RequestParam String name
        Enumeration<String> parameterNames = req.getParameterNames();
        System.out.println(parameterNames.asIterator());
        System.out.println(name);
    }
}

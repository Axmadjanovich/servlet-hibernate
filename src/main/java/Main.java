import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Main.class.getClassLoader().loadClass("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "imueaa0131");
        Statement statement = connection.createStatement();
//        String query = "5; drop table book;";
//        statement.executeUpdate("select * from student where id = " + query);
//        while (resultSet.next()){
//            System.out.println(resultSet.getString("first_name"));
//        }

        System.out.println("=========================");

        PreparedStatement preparedStatement = connection.prepareStatement("select * from student where id = ? or course = ? or age = ?");
        preparedStatement.setInt(3, 13);
        preparedStatement.setInt(1, 5);
        preparedStatement.setString(2, "Go");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("first_name"));
        }
    }
}

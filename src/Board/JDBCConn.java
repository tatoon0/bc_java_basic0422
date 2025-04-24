package Board;

import java.sql.*;

public class JDBCConn {
    String url = "jdbc:mariadb://localhost:3306/test";
    String userId = "root";
    String pwd = "1234";

    Connection connection;
    Statement statement; // 정적쿼리
    PreparedStatement preparedStatement; // 동적쿼리
    ResultSet resultSet; // 쿼리 결과를 담는 객체

    public JDBCConn() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("드라이버 찾음");
        } catch (ClassNotFoundException e) {
            System.out.println("마리아db 드라이버 못 찾음");
        }

        try {
            connection = DriverManager.getConnection(url, userId, pwd);
            System.out.println("db연결 성공");
        } catch (SQLException e) {
            System.out.println("url, id, pwd 입력 오류");
        }
    }// JDBCConn

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println("db연결 해제 실패");
        }
    }
}

package Board;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class TestMain {
    public static void main(String[] args) {
        JDBCConn jdbcConn = new JDBCConn();

        try {
            jdbcConn.statement = jdbcConn.connection.createStatement();
            String query = "select * from board";
            jdbcConn.resultSet = jdbcConn.statement.executeQuery(query);
            while (jdbcConn.resultSet.next()){
                int bno = jdbcConn.resultSet.getInt("bno");
                String title = jdbcConn.resultSet.getString("title");
                String content = jdbcConn.resultSet.getString("content");
                String writer = jdbcConn.resultSet.getString("writer");
                Date regdate = jdbcConn.resultSet.getDate("regdate");

                System.out.printf("글번호 : %d\t| 제목 : %s\t| 작성자 : %s\t 작성시간 : %s\n내용\n%s\n", bno, title, writer, regdate, content);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
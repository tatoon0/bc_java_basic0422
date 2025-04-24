package Board;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class BoardDAO extends JDBCConn {
    ArrayList<Board> boardArray;

    public BoardDAO() {
        super();
    }

//    public BoardDAO(ArrayList<Board> boardArray) {
//        System.out.println("DB대신 배열로 대체");
//        this.boardArray = boardArray;
//    }

//    등록
    public void create(Board board) {
        boardArray.add(board);
    } // 등록

//    등록 db.ver
    public void createD(Board board) {
        String query = "insert into board(title, content, writer, regdate) values(?, ?, ?, now())";
        try {
            this.preparedStatement = this.connection.prepareStatement(query);
            this.preparedStatement.setString(1, board.getTitle());
            this.preparedStatement.setString(2, board.getContent());
            this.preparedStatement.setString(3, board.getWriter());
            int rs = this.preparedStatement.executeUpdate(); // 성공 여부를 반환

            if (rs == 1) {
                System.out.println("글 등록 성공");
            } else {
                System.out.println("글 등록 실패");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    } // 등록 db.ver

    //    목록
    public ArrayList<Board> findAll() {
        return boardArray;
    } // 목록

//    목록 db.ver
    public ArrayList<Board> findAllD() {
        try {
            this.statement = this.connection.createStatement();
            String query = "select * from board";
            this.resultSet = this.statement.executeQuery(query);
            ArrayList<Board> result = new ArrayList<>();

            while (this.resultSet.next()) {
                Board board = new Board();

                board.setBno(this.resultSet.getInt("bno"));
                board.setTitle(this.resultSet.getString("title"));
                board.setContent(this.resultSet.getString("content"));
                board.setWriter(this.resultSet.getString("writer"));
                board.setLocalTime(this.resultSet.getDate("regdate").toLocalDate().atStartOfDay());

                result.add(board);
            }
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return boardArray;
    }// 목록 db.ver

//    읽기
    public Board findOne(int bno) {
        try {
            return boardArray.get(bno - 1);
        } catch (Exception e) {
            return null;
        }
    } // 읽기

//    수정
    public void update(Board board, String content) {
        board.setContent(content);
    } // 수정

//    삭제
    public void delete(Board board) {
        boardArray.remove(board);
    } // 삭제
}
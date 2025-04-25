package Board;

import java.awt.desktop.QuitResponse;
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
        ArrayList<Board> result = new ArrayList<>();
        try {
            this.statement = this.connection.createStatement();
            String query = "select * from board";
            this.resultSet = this.statement.executeQuery(query);

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
        return result;
    }// 목록 db.ver

//    읽기
    public Board findOne(int bno) {
        try {
            return boardArray.get(bno - 1);
        } catch (Exception e) {
            return null;
        }
    } // 읽기

//    일기 db.ver
    public Board findOneD(int bno) {
        String query = "select * from board where bno = ?";
        Board board = new Board();
        try {
            this.preparedStatement = this.connection.prepareStatement(query);
            this.preparedStatement.setInt(1,bno);
            this.resultSet = this.preparedStatement.executeQuery();
            if (this.resultSet.next()) {
                board.setBno(this.resultSet.getInt("bno"));
                board.setTitle(this.resultSet.getString("title"));
                board.setContent(this.resultSet.getString("content"));
                board.setWriter(this.resultSet.getString("writer"));
                board.setLocalTime(this.resultSet.getDate("regdate").toLocalDate().atStartOfDay());
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return board;
    } // 읽기 db.ver

//    수정
    public void update(Board board, String content) {
        board.setContent(content);
    } // 수정

//    수정 db.ver
    public void updateD(Board board, String content) {
        try {
            String query = "update board set content = ? where bno = ?";
            this.preparedStatement = this.connection.prepareStatement(query);
            this.preparedStatement.setString(1, content);
            this.preparedStatement.setInt(2, board.getBno());
            int res = this.preparedStatement.executeUpdate();
            if (res == 1) {
                System.out.println("수정완료");
            } else {
                System.out.println("수정실패");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//    삭제
    public void delete(Board board) {
        boardArray.remove(board);
    } // 삭제

//    삭제 db.ver
    public void deleteD(Board board) {
        try {
            String query = "delete from board where bno = ?";
            this.preparedStatement = this.connection.prepareStatement(query);
            this.preparedStatement.setInt(1, board.getBno());
            int res = this.preparedStatement.executeUpdate();
            if (res == 1) {
                System.out.println("삭제완료");
            } else {
                System.out.println("삭제실패");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    } // 삭제 db.ver
}
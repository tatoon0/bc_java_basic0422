package Board;

import java.time.LocalTime;
import java.util.ArrayList;

public class BoardDAO {
    ArrayList<Board> boardArray;

    public BoardDAO(ArrayList<Board> boardArray) {
        System.out.println("DB대신 배열로 대체");
        this.boardArray = boardArray;
    }

//    등록
    public void create(Board board) {
        board.setLocalTime(LocalTime.now());
        boardArray.add(board);
    } // 등록

//    목록
    public ArrayList<Board> findAll() {
        return boardArray;
    } // 목록

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
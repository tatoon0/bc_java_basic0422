package Board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class BoardMain {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Board> boardArray = new ArrayList<>();

    public static void main(String[] args) {
        BoardDAO boardDAO = new BoardDAO(boardArray);
        boardDAO.create(new Board("제목1","내용1","작성자1"));
        boardDAO.create(new Board("제목2","내용2","작성자2"));
        boardDAO.create(new Board("제목3","내용3","작성자3"));
        boardDAO.create(new Board("제목4","내용4","작성자4"));



        boolean exit = false;
        while(!exit) {
            int sel = menu();
            scanner.nextLine(); //버퍼지우기
            switch (sel) {
                case 1 -> { // 게시물 작성
                    System.out.println("게시물을 작성합니다");
                    System.out.print("제목을 입력해주세요 : ");
                    String title = scanner.nextLine();
                    System.out.println("내용을 입력해주세요");
                    String content = scanner.nextLine();
                    System.out.print("작성자를 입력해주세요 : ");
                    String writer = scanner.nextLine();
                    boardDAO.create(new Board(title, content, writer));
                } // case 1

                case 2 -> { // 전체글 보기
                    AtomicInteger count = new AtomicInteger(1);
                    boardDAO.findAll().forEach(board -> {
                        System.out.println("글번호 : " + count.getAndIncrement() + "\t| 제목 : " + board.getTitle());
                    });
                } // case 2

                case 3 -> { // 게시물 보기
                    System.out.print("읽으실 글 번호를 입력해주세요 : ");
                    int num = scanner.nextInt();
                    scanner.nextLine(); // 버퍼지우기
                    Board board = boardDAO.findOne(num);
                    if (board == null) {
                        System.out.println("해당 게시물이 존재하지 않습니다");
                        continue;
                    }
                    System.out.println("제목 : " + board.getTitle() + "\t| 작성자 : " + board.getWriter());
                    System.out.println(board.getContent());
                } // case 3

                case 4 -> { // 게시물 수정
                    System.out.print("수정할 게시물 번호를 입력해주세요 : ");
                    int num = scanner.nextInt();
                    scanner.nextLine(); // 버퍼지우기
                    Board board = boardDAO.findOne(num);
                    if (board == null) {
                        System.out.println("해당 게시물이 존재하지 않습니다");
                        continue;
                    }
                    System.out.println("내용을 재작성해주세요");
                    String content = scanner.nextLine();
                    boardDAO.update(board, content);
                } // case 4

                case 5 -> { // 게시물 삭제
                    System.out.print("삭제할 게시물 번호를 입력해주세요 : ");
                    int num = scanner.nextInt();
                    scanner.nextLine(); // 버퍼지우기
                    Board board = boardDAO.findOne(num);
                    if (board == null) {
                        System.out.println("해당 게시물이 존재하지 않습니다");
                        continue;
                    }
                    boardDAO.delete(board);
                    System.out.println("게시물이 삭제되었습니다");
                } // case 5

                case 0 -> { // 종료
                    System.out.println("종료합니다");
                    exit = true;
                } // case 0

                default -> {
                    System.out.println("유효하지 않은 값입니다");
                } // default
            } // switch
        } // while
    } // main

    public static int menu() {
        System.out.println("1.게시물 작성\t2.전체글 보기\t3.게시물 보기\t4.게시물 수정\t5.게시물 삭제\t0.종료");
        return scanner.nextInt();
    }
}
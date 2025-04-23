import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        sample account data
        accounts.add(new Account("123-123", "홍길동", 1000));
        accounts.add(new Account("456-456", "신짱구", 2000));

        boolean exit = false;
        while(!exit) {
            menu();
            int sel = scanner.nextInt();
            switch (sel) {
                case 1 -> { // 계좌생성
                    Account account = createAccount();
                    accounts.add(account);
                    System.out.println(account.getOwner() + "님의 계좌 개설을 축하드립니다");
                }
                case 2 -> { // 계좌목록
                    accounts.forEach(account -> {
                        System.out.println(account.toString());
                    });
                }
                case 3 -> { // 예금
                    System.out.print("계좌번호를 입력해주세요 : ");
                    Account account = findAccount(scanner.next());

                    if (account != null) {
                        deposit(account);
                    } else {
                        System.out.println("계좌를 찾을 수 없습니다");
                    }
                }
                case 4 -> { // 출금
                    System.out.print("계좌번호를 입력해주세요 : ");
                    Account account = findAccount(scanner.next());

                    if (account != null) {
                        withdraw(account);
                    } else {
                        System.out.println("계좌를 찾을 수 없습니다");
                    }
                }
                case 5 -> { // 종료
                    System.out.println("종료합니다");
                    exit = true;
                }
                case 99 -> { // 송금
                    remit();
                }
                default -> System.out.println("유효하지 않은 값입니다");
            }
        }
    }

    public static void menu() {
        System.out.println("=======================================================");
        System.out.println("1.계좌생성\t2.계좌목록\t3.예금\t4.출금\t5.종료\t99.송금");
        System.out.println("=======================================================");
        System.out.print("메뉴를 선택해주세요 : ");
    }

//    계좌생성
    public static Account createAccount () {
        boolean exit = false;

        String ano = null;
        while(!exit) {
            System.out.print("계좌번호를 입력해주세요 : ");
            ano = scanner.next();
            Account duplicateAccount = findAccount(ano);
            if (duplicateAccount == null) { // 동일한 계좌번호가 없다면
                exit = true;
            } else { // 동일한 계좌번호가 있다면
                System.out.println("이미 존재하는 계좌번호 입니다\n다른 계좌번호를 입력해주세요");
            }
        }

        System.out.print("이름을 입력해주세요 : ");
        String owner = scanner.next();

        long balance = 0;

        return new Account(ano, owner, balance);
    }

//    예금
    public static void deposit(Account account) {
        System.out.print("입금액을 입력해주세요 : ");
        account.setBalance(account.getBalance() + scanner.nextLong());
    }

//    출금
    public static void withdraw(Account account) {
        System.out.print("출금액을 입력해주세요 : ");
        long input = scanner.nextLong();

        if (account.getBalance() < input) {
            System.out.println("잔액이 부족합니다");
        } else {
            account.setBalance(account.getBalance() - input);
        }
    }

//    송금
    public static void remit() {
//        두 계좌 정보 확인
        Account from, to;

        System.out.println("송금하실 계좌의 계좌번호를 입력해주세요");
        from = findAccount(scanner.next());
        if (from == null) {
            System.out.println("해당 계좌가 존재하지 않습니다");
            return;
        }

        System.out.println("송금받을 계좌의 계좌번호를 입력해주세요");
        to = findAccount(scanner.next());
        if (to == null) {
            System.out.println("해당 계좌가 존재하지 않습니다");
            return;
        }

//        송금액 확인
        System.out.print("송금할 금액을 입력해주세요 : ");
        long money = scanner.nextLong();
        if (from.getBalance() < money) {
            System.out.println("잔액이 부족합니다");
            return;
        } // 잔액이 부족할 시 종료

        from.setBalance(from.getBalance() - money);
        to.setBalance(to.getBalance() + money);
        System.out.println("송금이 완료되었습니다");
    }

//    계좌 찾기 (select * from account where ano = ?)
    public static Account findAccount(String ano) {
        return accounts.stream()
                .filter(
                        account -> {
                            return account.getAno().equals(ano);
                        })
                .findFirst()
                .orElse(null);
    }
}
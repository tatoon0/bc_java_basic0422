package w0425;

public abstract class Phone {
//    추상 클래스는 익명의 구현객체로 만들 수 있다
    public String owner;

    public Phone() {
        this.owner = "홍길동";
    }

    public void turnOn() {
        System.out.println("전원을 켭니다");
    }

    public void turnOff() {
        System.out.println("전원을 끕니다");
    }

    public abstract void call();
}

package w0425;

public class Smartphone extends Phone{
    @Override
    public void turnOn() {
        System.out.println("스마트폰의 전원을 켭니다");
    }

    @Override
    public void turnOff() {
        System.out.println("스마트폰의 전원을 끕니다");
    }

    @Override
    public void call() {
        System.out.println("스마트폰");
    }
}

package w0425;

public interface RemoteControl {
//    인터페이스는 객체의 사용방법을 정의
//    객체의 교환성을 높여주기 때문에 다형성을 구현하는데 매우 중요함
//    개발 코드와 객체가 서로 통신하는 접점역할을 함
//    개발코드가 인터페이스 메소드를 호출하면
//    인터페이스는 구현한 자식의 객체의 메소드를 호출
//    개발 코드는 객체의 내부구조를 알 필요없이 인터페이스 메소드만 알면 됨

//    인터페이스를 사용하는 이유
//    개발코드를 수정하지 않고 사용하는 객체를 변경가능
//    하나의 객체가 아니라 여러 객체들과 사용가능
//    다중상속 가능
//    리턴 값 다양화

//    정적필드 선언 x
//    상수만 가능

    public static final int MAX_VOLUME = 10;
    public int MIN_VOLUME = 0; // static final 생략가능

    public abstract void turnOn();
    public void turnOff(); // abstract 생략가능
    public void setVolume(int volume);
}

package w0425;

public class SmTvImpl implements SmTv{
    private int volume;

    @Override
    public void call() {
        System.out.println("스마트tv");
    }

    @Override
    public void turnOn() {
        System.out.println("스마트tv를 켭니다");
    }

    @Override
    public void turnOff() {
        System.out.println("스마트tv를 끕니다");
    }

    @Override
    public void setVolume(int volume) {
        this.volume = MAX_VOLUME;
        System.out.println("현재 스마트tv의 음량 : " + this.volume);
    }
}

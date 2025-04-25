package w0425;

public class Audio implements RemoteControl{
    int volume;

    @Override
    public void turnOn() {
        System.out.println("오디오를 켬");
    }

    @Override
    public void turnOff() {
        System.out.println("오디오를 끔");
    }

    @Override
    public void setVolume(int volume) {
        if (volume > MAX_VOLUME) {
            this.volume = MAX_VOLUME;
        } else if (volume < MIN_VOLUME) {
            this.volume = MIN_VOLUME;
        } else {
            this.volume = volume;
        }
        System.out.println("현재 볼륨 : " + this.volume);
    }
}

package w0425;

public class Main {
    public static void main(String[] args) {
        Phone phone = new Phone() {
            @Override
            public void call() {
                System.out.println(this.owner);
            }
        };

        phone.turnOff();
        phone.turnOn();
        phone.call();

        Smartphone smartphone = new Smartphone();
        smartphone.turnOff();
        smartphone.turnOn();

        CellPhone cellPhone = new CellPhone();
        cellPhone.turnOn();
        cellPhone.call();
        cellPhone.turnOff();

        Tv tv = new Tv();
        tv.setVolume(99);

        RemoteControl rc1 = new Tv();
        rc1.setVolume(-1);
        RemoteControl rc2 = new Audio();
        rc2.setVolume(100);

        SmTvImpl smTv = new SmTvImpl();
        smTv.call();
        smTv.turnOn();
        smTv.turnOff();
        smTv.setVolume(1);
    }
}

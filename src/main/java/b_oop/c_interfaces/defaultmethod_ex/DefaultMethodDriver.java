package b_oop.c_interfaces.defaultmethod_ex;

public class DefaultMethodDriver {

    public static void main(String[] args) {
        PlayButton button = new PlayButton();
        button.buttonClicked("Clicked");
        button.buttonLongClicked("LongClicked");
    }
}

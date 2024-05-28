package b_oop.c_interfaces.defaultmethod_ex;

public class PlayButton extends BaseButton implements LongButtonListener, ButtonListener {

    // Same method in Superclass BaseButton and Interface ButtonListener:
    // - No implementation for buttonClicked() here since BaseButton has a method
    //   with same name and parameter. In this case, superclass BaseButton
    //   implementation is executed instead of ButtonListener


    // Same method in Interface ButtonListener and LongButtonListener
    //  - In this case, we explicitly choose the interface to be called
    //    in the overridden method.
    @Override
    public void buttonLongClicked(String event) {
        LongButtonListener.super.buttonLongClicked(event);  // explicitly choosing interface
    }
}

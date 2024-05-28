package b_oop.c_interfaces.callback_ex.notifylistener;

import java.util.ArrayList;
import java.util.List;

public class Adapter {

    private List<String> result = null;

    private Listener listener;

    interface Listener {
        void onFinish(List<String> list);
    }

    public void addListener(UserComponent listener) {
        this.listener = listener;
    }

    public void loadData() {
        result = TextService.getString();
        listener.onFinish(result); // callback
    }
}

class TextService {

    public static ArrayList<String> getString() {
        ArrayList<String> list = new ArrayList<>(){{
            add("C");
            add("Java");
            add("Kotlin");
        }};
        return list;
    }
}
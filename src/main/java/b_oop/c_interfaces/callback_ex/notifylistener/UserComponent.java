package b_oop.c_interfaces.callback_ex.notifylistener;

import java.util.*;
import java.util.function.Consumer;

public class UserComponent implements Adapter.Listener {

    Adapter adapter = new Adapter();
    ViewModel viewModel = new ViewModel();  // this viewModel is needed in adapter class

    public UserComponent() {
        adapter.addListener(this);
    }

    public void runComponent() {
        adapter.loadData();
    }

    // This function executes in the scope of function present in Adapter class
    @Override
    public void onFinish(List<String> list) {
        List<String> result = viewModel.capitalize(list);
        result.forEach(System.out::println);
    }
}

class ViewModel {

    public List<String> capitalize(List<String> items) {
        ArrayList<String> result = new ArrayList<>();
        items.forEach(s -> {
            result.add(s.toUpperCase(Locale.ENGLISH));
        });
        return result;
    }
}

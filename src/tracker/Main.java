package tracker;

import tracker.view.CommandView;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new CommandView());
        controller.run();
    }
}

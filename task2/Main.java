import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Toy toy1 = new Toy(1, "bird (common)", 5, 10);
        Toy toy2 = new Toy(2, "snake (rare)", 5, 5);
        Toy toy3 = new Toy(3, "shark (legendary)", 1, 1);
        List<Toy> toys = new ArrayList<>(Arrays.asList(toy1, toy2, toy3));
        Store store = new Store(toys);

        Presenter presenter = new Presenter(store);
        presenter.run();


    }
}

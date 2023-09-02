import java.util.HashMap;
import java.util.Random;

public class WeightSystemShowcase {
    private static final int[] ITEMS = {1, 2, 3, 4, 5};
    private static final int[] WEIGHTS = {5, 5, 1, 1, 1};
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        HashMap<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            int selectedItem = selectWeightedItem();
            Integer count = result.containsKey(selectedItem) ? result.get(selectedItem) : 0;
            result.put(selectedItem, count + 1);
        }
        System.out.println(result);
    }

    private static int selectWeightedItem() {
        int totalWeight = 0;
        for (int weight : WEIGHTS) {
            totalWeight += weight;
        }

        int randomNumber = RANDOM.nextInt(totalWeight);

        int currentWeight = 0;
        for (int i = 0; i < ITEMS.length; i++) {
            int weight = WEIGHTS[i];
            if (randomNumber >= currentWeight && randomNumber < currentWeight + weight) {
                return ITEMS[i];
            }
            currentWeight += weight;
        }

        return -1; // this should never happen
    }
}
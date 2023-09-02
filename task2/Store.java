import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Store {
    private List<Toy> toys;
    // для потребителя не имеет значение какой id и сколько их осталось, поэтому String
    private List<String> toysRewards;  
    private int totalWeight;
    private Random random = new Random();

    // конструктор принимающий лист игрушек
    public Store(List<Toy> toys) {
        toysRewards = new ArrayList<>();
        totalWeight = 0;  // нужно для подсчитывания шанса
        this.toys = toys;
        for (Toy toy : toys) {
            totalWeight += toy.getChanceWeight();
        }
    }
    // конструктор без аргументов
    public Store() {
        this(new ArrayList<Toy>());
    }

    // геттер общего веса (шанса)
    public int getTotalWeight() {
        return totalWeight;
    }
    // геттер листа с игрушками
    public List<Toy> getToys() {
        return toys;
    }
    // геттер призов
    public List<String> getToysRewards() {
        return toysRewards;
    }

    // добавить игрушку
    public void addToy(Toy toy) {
        toys.add(toy);
        totalWeight += toy.getChanceWeight();
    }

    // удаляет игрушку по id
    public void removeToy(int id) {
        for (int i = 0; i < toys.size(); i++) {
            Toy toy = toys.get(i);
            if (toy.getId() == id) {
                totalWeight -= toys.get(i).getChanceWeight();  // удаляет вес удалённой игрушки и totalWeight
                toys.remove(i);
            }
        }
    }
    // убрать 1 еденицу игрушки из quantity
    public void removeOneToy(int id) {
        for (int i = 0; i < toys.size(); i++) {
            Toy toy = toys.get(i);
            if (toy.getId() == id) {
                toys.get(i).setQuantity(toy.getQuantity() - 1);
                if (toys.get(i).getQuantity() == 0) {
                    totalWeight -= toy.getChanceWeight();
                    toys.remove(i);
                    System.out.println(toy.getName() + " кончился в магазине");
                }
            }
        }
    }

    // выбрать случайную игрушку используя систему веса, используется в giveaway()
    public Toy getRandomToy() {
        int currentWeight = 0;
        int randomNumber = random.nextInt(totalWeight);  // случайное число в диапозоне общего веса
        
        for (int i = 0; i < toys.size(); i++) {
            int weight = toys.get(i).getChanceWeight();  // вес игрушки на которой сейчас итерируемся
            if (randomNumber >= currentWeight && randomNumber < currentWeight + weight) {  // если в диапозоне весе
                return toys.get(i);  // нашли случайный приз
            }
            // cвоего рода шаг, но перескакивая все позиции одной и той же игрушки если её вес допустим >1
            currentWeight += weight; 
        }
        return null;  // этого быть не должно
    }

    // выйграть игрушку и положить её к выдачи, убрать 1 еденицу из магазина
    public void giveaway() {
        if (toys.size() > 0) {  // если магазин не пуст
            Toy awardToy = getRandomToy();
            if (awardToy != null) {
                System.out.println("вы выйграли: " + awardToy.getName());
                System.out.println("Ваш выйгрыш будет ожидать вас в пункте выдачи");
                toysRewards.add(awardToy.getName());
    
                // -1 quantity
                removeOneToy(awardToy.getId());
            }
            else {
                System.out.println("!!!!!произошла какая то ошибка");  // надеюсь вы это не увидите никогда
            }
        }
        else {  // если все таки магазин пуст
            System.out.println("магазин пуст, выйграть ничего не выйдет");
        }
    }

    // забрать приз, забирает по одному за раз из пула призов
    public String drawPrize() {
        if (toysRewards.size() > 0) {
            String reward = toysRewards.get(0);
            toysRewards.remove(0);  // аналог .pop(), выдаем и удаляем из очереди
            System.out.println("вы забрали " + reward);
            return reward;  
        } else {
            System.out.println("Игрушки кончились");
        }
        return null;  // если кончились все игрушки
    }

    // Забрать все призы разом
    public List<String> drawAllPrize(){
        List<String> tempToysRewards = toysRewards;
        toysRewards = new ArrayList<>();  // опустошаем выйгрыши
        return tempToysRewards;
    }

}

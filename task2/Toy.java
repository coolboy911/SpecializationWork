public class Toy {
    private int id;
    private String name;
    private int quantity;
    private int chanceWeight;

    public Toy(int id, String name, int quantity, int chanceWeight){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.chanceWeight = chanceWeight;
    }

    @Override
    public String toString() {
        return "id: " + id + "; name: " + name + "; quantity: " + quantity + "; chanceWeight: " + chanceWeight;
    }

    // геттер id
    public int getId(){
        return id;
    }
    // геттер имени
    public String getName(){
        return name;
    }
    // геттер веса шанса выпадения
    public int getChanceWeight(){
        return chanceWeight;
    }
    // геттер колличества игрушек
    public int getQuantity(){
        return quantity;
    }

    // изменить шанс выпадения
    public void setChanceWeight(int chanceWeight){
        this.chanceWeight = chanceWeight;
    }
    // изменить колличество игрушек
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }


}

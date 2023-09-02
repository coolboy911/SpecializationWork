import java.io.FileWriter;
import java.util.Scanner;

public class Presenter {
    private Scanner iScanner = new Scanner(System.in);

    private Store store;

    public Presenter(Store store){
        this.store = store;
    }

    private void writeRewardInFile(String toy){
        try (FileWriter fw = new FileWriter("Prize.txt", true)) {
            if (toy != null) {
                fw.append(toy + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void run(){
        boolean getNewIteration = true;
        while (getNewIteration) {
            System.out.println("-----");
            System.out.println("1 - посмотреть какие игрушки можно выйграть");
            System.out.println("2 - выйграть игрушку");
            System.out.println("3 - посмотреть какие выйгранные игрушки можно забрать");
            System.out.println("4 - забрать первую игрушку");
            System.out.println("5 - забрать все игрушки");
            System.out.println("6 - завершить работу");

            System.out.println("введите команду");
            try {
                int command = iScanner.nextInt();
                if (command < 1 || command>6) throw new Exception("неизвестная команда");

                switch (command) {
                    case 1:
                        for (Toy toy : store.getToys()) {
                            System.out.println(toy);
                        }
                        break;
                    case 2:
                        store.giveaway();
                        break;
                    case 3:
                        System.out.println(store.getToysRewards());
                        break;
                    case 4:
                        writeRewardInFile(store.drawPrize());
                        break;
                    case 5:
                        for (String toy : store.drawAllPrize()) {
                            writeRewardInFile(toy);
                        }
                        break;
                    case 6:
                        getNewIteration = false;
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } 
        }
    }
}

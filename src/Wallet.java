import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private int number;
    private String name;
    private double money;

    public Wallet() {
    }

    public Wallet(int number, String name, double money) {
        this.number = number;
        this.name = name;
        this.money = money;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public static List<Wallet> loadFromFileWallet(String path) {
        List<Wallet> listWallet = new ArrayList<>();
        List<String> lines = FinancialSystemApp.getLoadFromFile(path);
        for (String line : lines) {
            String[] fragments = line.split(" ");
            if (fragments.length == 0) {
                System.out.println("Ошибка чтения файла");
            } else {
                listWallet.add(new Wallet(
                        Integer.parseInt(fragments[0]),
                        fragments[1],
                        Double.valueOf(fragments[2]))
                );
            }
        }
        return listWallet;
    }

    public String print(){
        return "\n" + getNumber() + " " + getName() + " " + " " + getMoney();
    }

    @Override
    public String toString() {
        return "Кошелек: " + name +
                "\n сумма = $" + money + "\n" ;
    }
}

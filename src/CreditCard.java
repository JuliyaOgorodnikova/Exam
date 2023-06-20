import java.util.ArrayList;
import java.util.List;

public class CreditCard {
    private int number;
    private double money;

    public CreditCard() {
    }

    public CreditCard(int number, double money) {
        this.number = number;
        this.money = money;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public static List<CreditCard> loadFromFileCredit(String path) {
        List<CreditCard> listCredit = new ArrayList<>();
        List<String> lines = FinancialSystemApp.getLoadFromFile(path);
        for (String line : lines) {
            String[] fragments = line.split(" ");
            if (fragments.length == 0) {
                System.out.println("Ошибка чтения файла");
            } else {
                listCredit.add(new CreditCard(
                        Integer.parseInt(fragments[0]),
                        Double.valueOf(fragments[1]))
                );
            }
        }
        return listCredit;
    }

    @Override
    public String toString() {
        return "Кредитная карта: N " + getNumber() +
                "\n сумма = $ " + getMoney() + "\n";
    }
}

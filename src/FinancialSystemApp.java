import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FinancialSystemApp {
    private static List<User> users;

    //private List<Expense> currentExpenses;
    //private List<Expense> potentialExpenses;
    //private List<Income> potentialIncomes;

    private static Scanner sc = new Scanner(System.in);
    private static final String txtUser = "data/user.txt";

    public FinancialSystemApp() {
        // Инициализация списков и других переменных
    }

    public static void run() {
        // Главный метод приложения, запускающий его работу
        users = User.loadFromFileUser(txtUser);
        while (true) {
            System.out.println(Menu.MENU);
            System.out.print("Выберите действия (5 - выход): ");
            int num = sc.nextInt();
            if (num == 5)
                break;
            switch (num) {
                case 1 -> {
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Введите ФИО или id владельца карты банка: ");
                    String fioClient = sc.nextLine();
                    System.out.println(Menu.MENU_LINE);
                    int id = -1;
                    if (fioClient != "") {
                        if (Character.isDigit(fioClient.charAt(0))) {
                            id = Integer.parseInt(fioClient);
                        }
                        User user;
                        if (id != -1)
                            user = getUser(id);
                        else user = getUser(fioClient);
                        if (user != null)
                            InformationFinances.run(user);
                        else System.out.println("Владелец карты банка не найден!");
                    }
                }
                case 2 -> printUser();
            }
        }
        System.out.println("Спасибо за использование системы учета финансов!");
    }



    public void removeWallet(Wallet wallet) {
        // Удаление информации о кошельке
    }

    public void editWallet(Wallet wallet) {
        // Редактирование информации о кошельке
    }

    /*public void addCreditCard(CreditCard creditCard) {
        // Добавление информации о кредитной карте
    }

    public void removeCreditCard(CreditCard creditCard) {
        // Удаление информации о кредитной карте
    }

    public void editCreditCard(CreditCard creditCard) {
        // Редактирование информации о кредитной карте
    }*/

    public void managePotentialExpenses() {
        // Работа со списком потенциальных затрат
    }

    public void manageCurrentExpenses() {
        // Работа со списком текущих затрат
    }

    public void managePotentialIncomes() {
        // Работа со списком потенциальных доходов
    }

    public static List<String> getLoadFromFile(String path) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(path));
            if (lines.isEmpty())
                System.out.println("Ошибка чтения файла");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lines;
    }

    public static User getUser(int id) {
        for (User user : users) {
            if (user.getIdUser() == id)
                return user;
        }
        return new User();
    }

    public static User getUser(String fio) {
        String[] split = fio.split(" ");
        if (split.length == 0 || split.length != 3)
            System.out.println("Ошибка ввода");
        else {
            for (User user : users) {
                if (user.getLastName().equalsIgnoreCase(split[0]) &&
                        user.getName().equalsIgnoreCase(split[1]) &&
                        user.getSurname().equalsIgnoreCase(split[2]))
                    return user;
            }
        }
        return new User();
    }

    public static void printUser() {
        System.out.println(users.toString());
    }
}

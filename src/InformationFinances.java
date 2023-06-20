import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class InformationFinances {
    private static final String txtWallet = "data/wallet.txt";
    private static final String txtCredit = "data/credit.txt";
    private static final String txtUserWallet = "data/userWallet.txt";
    private static List<Wallet> wallets = new ArrayList<>();
    private static List<CreditCard> credits = new ArrayList<>();
    private static Map<User, List<Integer>> mapUserWallet = new HashMap<>();
    private static Map<User, List<Integer>> mapUserCredit = new HashMap<>();
    private List<CreditCard> creditCards = new ArrayList<>();

    public static void run(User user) {
        System.out.println("Владелец карты банка: " + user + "\n");
        Scanner sc = new Scanner(System.in);
        wallets = Wallet.loadFromFileWallet(txtWallet);
        credits = CreditCard.loadFromFileCredit(txtCredit);
        mapUserWallet = loadFromFileMap(txtUserWallet, false);
        mapUserCredit = loadFromFileMap(txtUserWallet, true);
        Menu.dispList();
        int num = 0;
        while (true) {
            System.out.print("Выберите действия (15 - назад): ");
            num = sc.nextInt();
            if (num == 15)
                break;
            switch (num) {
                case 1 -> {
                    List<Wallet> list = displayWalletState(user, Menu.MENU_1);
                    if (!list.isEmpty())
                        System.out.println(list);
                }
                case 2 -> {
                    List<Wallet> list = displayWalletState(user, Menu.MENU_2);
                    if (!list.isEmpty())
                        System.out.println("Общая сумма по всем кашелькам: $" + getTotalWalletAmount(list));
                }
                case 3 -> {
                    List<CreditCard> list = displayCreditState(user, Menu.MENU_3);
                    if (!list.isEmpty())
                        System.out.println(list);
                }
                case 4 -> {
                    List<CreditCard> list = displayCreditState(user, Menu.MENU_4);
                    if (!list.isEmpty())
                        System.out.println("Общая сумма по кредитным картам: $" + getTotalCreditAmount(list));
                }
                case 5 -> {
                    List<Wallet> listWallet = displayWalletState(user, Menu.MENU_5);
                    List<CreditCard> listCredit = displayCreditState(user, "");
                    if (!listWallet.isEmpty())
                        System.out.println("Общая сумма по всем кашелькам: $" + getTotalWalletAmount(listWallet));
                    if (!listCredit.isEmpty())
                        System.out.println("Общая сумма по кредитным картам: $" + getTotalCreditAmount(listCredit));
                }
                case 6 -> addWallet(user);
            }
            System.out.println(Menu.MENU_LINE);
        }
    }

    public static List<Wallet> displayWalletState(User user, String info) {
        // Получение суммы по всем кошелькам
        System.out.println(info);
        List<Integer> listFinance = getUserIdFIO(user, mapUserWallet);
        List<Wallet> listWallet = new ArrayList<>();
        if (listFinance.isEmpty()) {
            System.out.println("Данные не найдены!");
        } else {
            for (int intNum : listFinance) {
                Wallet wal = getWallet(intNum);
                if (wal != null)
                    listWallet.add(wal);
                else System.out.println("Данные кошелька не найдены!");
            }
        }
        return listWallet;
    }

    public static List<CreditCard> displayCreditState(User user, String info) {
        // Получение суммы по всем кошелькам
        System.out.println(info);
        List<Integer> listFinance = getUserIdFIO(user, mapUserCredit);
        List<CreditCard> listCredit = new ArrayList<>();
        if (listFinance.isEmpty()) {
            System.out.println("Данные не найдены!");
        } else {
            for (int intNum : listFinance) {
                CreditCard creditCard = getCredit(intNum);
                if (creditCard != null)
                    listCredit.add(creditCard);
                else System.out.println("Данные кредитной карты не найдены!");
            }
        }
        return listCredit;
    }

    public static double getTotalWalletAmount(List<Wallet> listWallet) {
        // Получение суммы по всем кошелькам
        double total = listWallet.stream().mapToDouble(w -> w.getMoney()).sum();
        return total;
    }

    public static double getTotalCreditAmount(List<CreditCard> listCredit) {
        // Получение суммы по всем кошелькам
        double total = listCredit.stream().mapToDouble(w -> w.getMoney()).sum();
        return total;
    }

    public static void addWallet(User user) {
        System.out.print("Введите номер кошелька: ");
        int number = new Scanner(System.in).nextInt();
        System.out.print("Введите название кошелька: ");
        String name = new Scanner(System.in).nextLine();
        System.out.print("Введите сумму кошелька: ");
        double money = new Scanner(System.in).nextDouble();
        Wallet wallet = new Wallet(number, name, money);
        List<Wallet> list = new ArrayList<>();
        if (foundWalletMap(user, wallet))
            System.out.println("Кошелек с таким номером уже существует");
        else {
            List<Wallet> listWallet = displayWalletState(user, "Поиск данных...");
            String text = user.getIdUser() + "," + getStringWallet(listWallet);
            list.addAll(listWallet);
            list.add(wallet);
            addWalletFiles(user, list, wallet, text);
            List<Integer> listInt = list.stream().map(w -> w.getNumber()).toList();
            mapUserWallet.put(user, listInt);
        }
    }

    private static String getStringWallet(List<Wallet> listWallet) {
        String str = "";
        for (Wallet w: listWallet) {
            str += w.getNumber();
        }
        return str;
    }

    private static boolean foundWalletMap(User user, Wallet wallet) {
        List<Wallet> listWallet = displayWalletState(user, "Поиск данных...");
        if (!listWallet.isEmpty()) {
            for (Wallet wal : listWallet) {
                if (wal.getNumber() == wallet.getNumber())
                    return true;
            }
        }
        return false;
    }

    public static void addWalletFiles(User user, List<Wallet> wallet, Wallet newWallet, String strOld) {
        // Добавление информации о кошельке
        StringBuilder stringBuilder = new StringBuilder();
        for (Wallet w : wallet) {
            stringBuilder.append(user.getIdUser() + "," + w.getNumber() + "\n");
        }
        Stream<String> lines = Files.lines(input);
        try (BufferedWriter writer = Files.newBufferedWriter(temp)) {
            lines
                    .filter(line -> !line.startsWith("hello"))
                    .forEach(line -> {
                        try {
                            writer.write(line);
                            writer.newLine();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }

        Path temp;
        try {
            temp = Files.createTempFile("temp", ".txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.write(temp, stringBuilder.toString().getBytes(), StandardOpenOption.APPEND);
            Files.move(temp, Path.of(txtUserWallet), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.write(Paths.get(txtWallet), newWallet.print().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Wallet getWallet(int num) {
        Wallet wallet = null;
        for (Wallet ob : wallets) {
            if (ob.getNumber() == num)
                return ob;
        }
        return wallet;
    }

    public static CreditCard getCredit(int num) {
        CreditCard creditCard = null;
        for (CreditCard credit : credits) {
            if (credit.getNumber() == num)
                return credit;
        }
        return creditCard;
    }

    public static List<Integer> getUserIdFIO(User user, Map<User, List<Integer>> mapUser) {
        List<Integer> listNum = new ArrayList<>();
        for (Map.Entry<User, List<Integer>> entry : mapUser.entrySet()) {
            User name = entry.getKey();
            List<Integer> list = entry.getValue();
            if (user.getIdUser() == name.getIdUser()) {
                listNum.addAll(list);
            }
        }
        return listNum;
    }

    public static Map<User, List<Integer>> loadFromFileMap(String path, boolean isCredit) {
        Map<User, List<Integer>> map = new HashMap<>();
        List<String> lines = FinancialSystemApp.getLoadFromFile(path);
        boolean isNotUser = false;
        for (String line : lines) {
            String[] fragments = line.split(",");
            if (fragments.length == 0) {
                System.out.println("Ошибка чтени файла");
            } else {
                List<Integer> list = new ArrayList<>();
                int k = 1;
                if (isCredit) k = 2;
                for (int i = k; i < fragments.length; i++) {
                    if (fragments[i].equalsIgnoreCase("K") ||
                            fragments[i].equalsIgnoreCase("К")) {
                        isNotUser = true;
                        break;
                    } else
                        list.add(Integer.parseInt(fragments[i]));
                }
                if (!isNotUser) {
                    User user = FinancialSystemApp.getUser(Integer.parseInt(fragments[0]));
                    if (user == null)
                        System.out.println("Клиент банка с id " + fragments[0] + "не найден");
                    else
                        map.put(user, list);
                }
            }
        }
        return map;
    }
}

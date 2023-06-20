import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {
    public static final String MENU = "=== Система учета финансов. ===\n" +
            "1. Поиск данных по ФИО владельца карты банка\n" +
            "2. Список всех владельцев карт банка\n" +
            "3. Добавить владельца карты банка\n" +
            "4. Удалить владельца карты банка\n" +
            "5. Выход\n" +
            "================================================";
    public static final String MENU_1 = "1. Отображение состояния кошелька";
    public static final String MENU_2 = "2. Получение суммы по всем кошелькам";
    public static final String MENU_3 = "3. Отображение состояния кредитной карты";
    public static final String MENU_4 = "4. Получение суммы по всем кредитным картам";
    public static final String MENU_5 = "5. Получение общей суммы по кошелькам и картам";
    public static final String MENU_6 = "6. Добавление информации о кошельке";
    public static final String MENU_7 = "7. Удаление информации о кошельке";
    public static final String MENU_8 = "8. Редактирование информации о кошельке";
    public static final String MENU_9 = "9. Добавление информации о кредитной карте";
    public static final String MENU_10 = "10. Удаление информации о кредитной карте";
    public static final String MENU_11 = "11. Редактирование информации о кредитной карте";
    public static final String MENU_12 = "12. Работа со списком потенциальных затрат";
    public static final String MENU_13 = "13. Работа со списком текущих затрат";
    public static final String MENU_14 = "14. Работа со списком потенциальных доходов";
    public static final String MENU_15 = "15. Назад";
    public static final String MENU_LINE = "================================================";

    public static List<String> MENU_LIST = Arrays.asList(MENU_LINE,
            MENU_1,
            MENU_2,
            MENU_3,
            MENU_4,
            MENU_5,
            MENU_6,
            MENU_7,
            MENU_8,
            MENU_9,
            MENU_10,
            MENU_11,
            MENU_12,
            MENU_13,
            MENU_14,
            MENU_15,
            MENU_LINE
    );

    public static void dispList(){
        for (String str: MENU_LIST) {
            System.out.println(str);
        }
    }
}

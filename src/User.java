import java.util.ArrayList;
import java.util.List;

public class User {
    public int idUser;
    public String lastName;
    public String name;
    public String surname;

    public User(int idUser, String lastName, String name, String surname) {
        this.idUser = idUser;
        this.lastName = lastName;
        this.name = name;
        this.surname = surname;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public static List<User> loadFromFileUser(String path) {
        List<User> listUser = new ArrayList<>();
        List<String> lines = FinancialSystemApp.getLoadFromFile(path);
        for (String line : lines) {
            String[] fragments = line.split(" ");
            if (fragments.length == 0) {
                System.out.println("Ошибка чтения файла");
            } else {
                    listUser.add(new User(Integer.parseInt(fragments[0]),
                            fragments[1], fragments[2], fragments[3]));
            }
        }
        return listUser;
    }

    @Override
    public String toString() {
        return "id: " + idUser + " ФИО: " + lastName + ' ' + name + ' ' + surname;
    }
}

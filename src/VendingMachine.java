
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VendingMachine {
    private List<Toy> toys = new ArrayList<>();
    private String shopFileName = "shop.txt";
    private String logFileName = "log.txt";

    public VendingMachine() {
        loadToysFromFile(shopFileName);
    }

    public void showToys() {
        System.out.println("Список игрушек в автомате:");
        for (Toy toy : toys) {
            System.out.println(toy);
        }
    }

    public void addToy(String id, String name, double weight) {
        Toy newToy = new Toy(id, name, weight);
        toys.add(newToy);
        saveToysToFile(shopFileName);
        System.out.println("Игрушка добавлена в автомат.");
    }

    public Toy getToy() {
        if (toys.isEmpty()) {
            System.out.println("Автомат пуст. Нет доступных игрушек.");
            return null;
        }

        // сумма веса всех игрушек в списке
        double totalWeight = toys.stream().mapToDouble(Toy::getWeight).sum();
        // генерируем случайное число от 0 до нашей суммы веса игрушек в списке
        Random random = new Random();
        double randomNumb = random.nextDouble() * totalWeight;
        //
        Toy selectedToy = null;
        double currentWeight = 0;
        // выборка игрушки
        for (Toy item : this.toys) {
            currentWeight += item.getWeight();
            if (randomNumb <= currentWeight) {
                selectedToy = item;
                break;
            }
        }

        if (selectedToy != null) {
            toys.remove(selectedToy);
            saveToysToFile(shopFileName);
            saveToyToLog(selectedToy);
            System.out.println("Вы получили игрушку: " + selectedToy);
        }

        return selectedToy;
    }

    private void saveToysToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Toy toy : toys) {
                writer.println(toy.getId() + "," + toy.getName() + "," + toy.getWeight());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи игрушек в файл.");
        }
    }

    private void saveToyToLog(Toy toy) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFileName, true))) {
            writer.println(toy.getId() + "," + toy.getName() + "," + toy.getWeight());
        } catch (IOException e) {
            System.err.println("Ошибка при записи игрушки в лог.");
        }
    }

    private void loadToysFromFile(String fileName) {
        toys.clear();  // Очищаем список игрушек перед загрузкой новых данных
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    double weight = Double.parseDouble(parts[2].trim());
                    toys.add(new Toy(id, name, weight));
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке игрушек из файла.");
        }
    }
}

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VendingMachine vendingMachine = new VendingMachine();

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Показать все игрушки");
            System.out.println("2. Добавить игрушку");
            System.out.println("3. Вытянуть игрушку");
            System.out.println("4. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Чтение символа новой строки после ввода числа

            switch (choice) {
                case 1:
                    vendingMachine.showToys();
                    break;
                case 2:
                    System.out.print("Введите ID игрушки: ");
                    String id = scanner.nextLine();
                    System.out.print("Введите имя игрушки: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите вес игрушки: ");
                    double weight = scanner.nextDouble();
                    vendingMachine.addToy(id, name, weight);
                    break;
                case 3:
                    vendingMachine.getToy();
                    break;
                case 4:
                    System.out.println("Выход из программы.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите действие из меню.");
            }
        }
    }
}
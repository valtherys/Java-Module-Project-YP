import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String name;
    static int speed;

    public static void main(String[] args) {
        int carsAmount = 3;
        Race race = new Race();
        Car[] cars = new Car[carsAmount];

        System.out.println("Добро пожаловать на \"24 часа Ле-Мана\"!");
        System.out.println("Давайте посмотрим, кто сегодня участвует в гонке!");

        for (int i = 0; i < carsAmount; i++){
            cars[i] = registerCar(i);
        }

        race.defineWinner(cars);

        scanner.close();

        System.out.println("Ура! Гонка состоялась! Победитель - " + race.winner.name);
    }

    static Car registerCar(int i){
        System.out.println("Введите имя машины " + (i + 1) + ":");
        name = checkCarName(scanner.nextLine().trim());

        System.out.println("Введите скорость машины " + (i + 1) + ":");
        speed = checkCarSpeed(scanner.nextLine().trim());

        return new Car(name, speed);
    }

    static String checkCarName(String name){
        while (name.isEmpty()){
            System.out.println("Имя машины не может быть пустым. Введите еще раз:");
            name = scanner.nextLine().trim();
        }

        return name;
    }

    static int checkCarSpeed(String speedString){
        while (true){
            if(!speedString.matches("-?\\d+")){
                System.out.println("Величина скорости не может быть дробным числом, содержать нечисловые символы или быть пустой. Повторите ввод:");
            } else {
                speed = Integer.parseInt(speedString);
                if (!((speed >= 0) && (speed <= 250))){
                    System.out.println("Скорость не должна быть меньше 0 или больше 250. Введите еще раз:");
                } else {
                    return speed;
                }
            }
            speedString = scanner.nextLine().trim();
        }
    }
}

class Car {
    int speed;
    String name;

    Car(String name, int speed){
        this.name = name;
        this.speed = speed;
    }
}

class Race {
    Car winner;
    static int raceDuration = 24;

    static int calculateDistance(Car car){
        return car.speed * raceDuration;
    }

   void defineWinner(Car[] cars){
        winner = cars[0];
        for(Car car : cars) {
            if (calculateDistance(car) > calculateDistance(winner)) {
                winner = car;
            }
        }
    }
}

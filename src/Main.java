import java.util.Arrays;
import java.util.Scanner;
class Main {

    public static void showRoom(char[][] cinema) {
        int num = 1;
        System.out.print("Cinema:\n  ");
        for (int i = 0; i < cinema[0].length; i++) {
            System.out.print(num++ + " ");
        }
        num = 1;
        System.out.println();
        for (char[] chars : cinema) {
            System.out.print(num++ + " ");
            for (int j = 0; j < cinema[0].length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
    }


    public static int setRows() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        return sc.nextInt();
    }

    public static int setSeats() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of seats in each row:");
        return sc.nextInt();
    }

    public static void calculateIncome(int rows, int seats) {
        int price = 10;
        int income;
        if (rows * seats < 60) {
            income = rows * seats * price;
        } else {
            int lowPrice = 8;
            int halfRows = rows / 2;
            int lowPriceHalf = rows - halfRows;
            income = halfRows * seats * price + lowPriceHalf *seats * lowPrice;
        }
        System.out.printf("%nTotal income: $%d%n", income);
    }

    public static char[][] fillRoom(int rows, int seats) {
        char[][] cinema = new char[rows][seats];
        for (char[] row : cinema) {
            Arrays.fill(row, 'S');
        }
        return cinema;
    }

    public static void checkThePrice(char[][] cinema, int[][] info) {
        //In info [0][0] is tickets and [1][0] is income!
        Scanner sc = new Scanner(System.in);
        int row, seat;
        while (true) {
            System.out.println("Enter a row number:");
            row = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            seat = sc.nextInt();
            if (row > cinema.length || seat > cinema[0].length) {
                System.out.println("Wrong input!");
            } else if (cinema[row - 1][seat - 1] != 'B') {
                cinema[row - 1][seat - 1] = 'B';
                info[0][0]++;
                break;
            } else {
                System.out.println("That ticket has already been purchased!");
            }
        }
        if (cinema.length * cinema[0].length < 60) {
            info[1][0] += 10;
            System.out.printf("%nTicket price: $%d%n%n", 10);
        } else {
            if (row > cinema.length / 2) {
                info[1][0] += 8;
                System.out.printf("%nTicket price: $%d%n%n", 8);
            } else {
                info[1][0] += 10;
                System.out.printf("%nTicket price: $%d%n%n", 10);
            }
        }
        showRoom(cinema);
    }

    public static void showStatistics(int[][] info, int rows, int seats) {
        System.out.printf("%nNumber of purchased tickets: %d", info[0][0]);
        double percentage = ((double) info[0][0] / (double) (rows * seats)) * 100;
        System.out.printf("%nPercentage: %.2f%%", percentage);
        System.out.printf("%nCurrent income: $%d", info[1][0]);
        calculateIncome(rows, seats);
    }

    public static void showMenu(char[][] cinema) {
        int[][] soldTicketsAndCurrentIncome = new int[2][1];
        Scanner sc = new Scanner(System.in);
        boolean menuOn = true;
        while (menuOn) {
            System.out.println("""
                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit
                    """);
            int input = sc.nextInt();
            switch (input) {
                case 1 -> showRoom(cinema);
                case 2 -> checkThePrice(cinema, soldTicketsAndCurrentIncome);
                case 3 -> showStatistics(soldTicketsAndCurrentIncome, cinema.length, cinema[0].length);
                case 0 -> menuOn = false;
                default -> System.out.println("Invalid input!\n");
            }
        }
    }

    public static void main(String[] args) {
        int rows = setRows();
        int seats = setSeats();
        char[][] cinema = fillRoom(rows, seats);
        showMenu(cinema);
    }
}
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.println("enter number of processors : ");
        int numberOfProcessors = sc.nextInt();

        System.out.println("enter number of cycles : ");
        int numberOfCycles = sc.nextInt();

        System.out.println("enter file path: ");
        String filePath = sc.next();

        Simulator simulator = new Simulator(numberOfProcessors, numberOfCycles, filePath);
        simulator.simulate();
    }
}
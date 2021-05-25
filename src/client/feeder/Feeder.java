package client.feeder;

import java.io.IOException;
import java.util.Scanner;

public class Feeder {
    private static void interact() {
        Scanner scanner = new Scanner(System.in);
        EchoFeeder feeder = new EchoFeeder("localhost");

        while (true) {
            String line = scanner.nextLine();
            if (line.equals("\\")) {
                feeder.close();
                break;
            }

            try {
                String res = feeder.sendToKitty(line);
                System.out.println(res);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        interact();
    }
}

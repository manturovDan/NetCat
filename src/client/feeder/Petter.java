package client.feeder;

import java.io.IOException;

public class Petter {
    private static void interact() throws  IOException {
        EchoPetter petter = new EchoPetter();
        petter.startConnection("localhost", 6001);
        petter.sendMessage("hello server");
        petter.stopConnection();
    }

    public static void main(String[] args) throws IOException {
        interact();
    }
}

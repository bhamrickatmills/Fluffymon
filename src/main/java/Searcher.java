import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import java.util.Scanner;

public class Searcher {
    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            ZMQ.Socket publisher = context.createSocket(SocketType.PUB);
            publisher.bind("tcp://*:5556");
            System.out.println("Enter sighting name, date(mmddyyyy), and cross streets (street/street): ");
            Scanner scanner = new Scanner(System.in);
            String petData = scanner.nextLine();
            System.out.println();
            Boolean sent = publisher.send("PET_DATA " + petData);
            scanner.close();
            publisher.close();
        }
    }
}

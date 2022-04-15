import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class PetReport {
    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);
            subscriber.connect("tcp://*:5556");
            subscriber.subscribe("PET_DATA");
            String reply = "";
            while (!Thread.currentThread().isInterrupted()) {
                reply = subscriber.recvStr();
                System.out.println(reply);
            }
            subscriber.close();
            System.out.println("done");
        }
    }
}

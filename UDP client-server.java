######Client Program:####
import java.io.*;
import java.net.*;

class UDPClient {
    public static void main(String[] a) throws IOException {
        int i = 2000;
        while (true) {
            byte buf[] = new byte[1024];
            DatagramSocket ds = new DatagramSocket(i);
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            ds.receive(dp);
            String str2 = new String(dp.getData(), 0, dp.getLength());
            System.out.println("Server:" + str2);
            i = i + 1;
        }
    }
}


########Server Program:####
import java.io.*;
import java.net.*;

class UDPServer {
    public static void main(String[] a) throws IOException {
        int i = 2000;
        String fooString1 = new String("exit");
        while (true) {
            InetAddress clientIP = InetAddress.getLocalHost();
            int clientPort = i;
            byte buf[] = new byte[1024];
            DatagramSocket ds = new DatagramSocket();
            BufferedReader dis = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Server is Running.");
            String str1 = new String(dis.readLine());
            if (str1.equals(fooString1)) {
                ds.close();
                break;
            } else {
                buf = str1.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, str1.length(), clientIP, clientPort);
                ds.send(packet);
                i = i + 1;
            }
        }
    }
}


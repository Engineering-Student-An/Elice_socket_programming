package udp;

import java.io.IOException;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();

            // localhost = 127.0.0.1
//            InetAddress IPAddress = InetAddress.getByName("localhost");
            InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
//            String hostName = IPAddress.getHostName();
            String hostAddress = IPAddress.getHostAddress();
            String canonicalHostName = IPAddress.getCanonicalHostName();
//            System.out.println("hostName = " + hostName);
            System.out.println("hostAddress = " + hostAddress);
            System.out.println("canonicalHostName = " + canonicalHostName);

            byte[] sendData = new byte[1024];
            String sentence = "Hello UDP Server";
            sendData = sentence.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            socket.receive(receivePacket);

            String modifiedSentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("서버 메시지: " + modifiedSentence);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}

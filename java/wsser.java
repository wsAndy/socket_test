import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class wsser {

    public static void main(String args[]) throws  Exception
    {
        ServerSocket ser = new ServerSocket(54321);

        Socket ss = ser.accept();
        PrintStream p_out = new PrintStream(ss.getOutputStream());
        p_out.println("connect");
        System.out.println("link one");
        while(true)
        {
            Scanner sc = new Scanner(ss.getInputStream());
            int number = sc.nextInt();

            PrintStream p = new PrintStream(ss.getOutputStream());
            p.println(number*2);

            System.out.println("send result for ["+
                    ss.getInetAddress().getHostName()+"]  at["+ss.getInetAddress().getHostAddress()+"]");

            if(number == 88)
            {
                break;
            }

        }
        ss.close();
        ser.close();
    }


}

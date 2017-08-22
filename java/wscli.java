import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class wscli {

    public static void main(String args[]) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        Socket soc = new Socket("127.0.0.1",54321);
        Scanner sc_get = new Scanner(soc.getInputStream());
        String str = sc_get.nextLine();
        System.out.println(str);

        while(true)
        {
            System.out.println("Enter number: ");
            int number = sc.nextInt();

            PrintStream p = new PrintStream(soc.getOutputStream());
            p.println(number);

            Scanner sc2 = new Scanner(soc.getInputStream());
            int result = sc2.nextInt();
            System.out.println("get return = "+result);

            if(number == 88)
            {
                break;
            }

        }

        soc.close();
    }
}

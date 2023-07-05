import java.util.*;
import java.net.*;
import java.io.*;
public class Client {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 3333);
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintStream ps = new PrintStream(s.getOutputStream());
        Scanner in  = new Scanner(System.in);
        System.out.println("Enter the key :");
        int key = in.nextInt();
        ps.println(key);
        while(true)
        {
            System.out.println("Enter 1 to encrypt 2 to decrypt 3 to bf attack 4 to fa attack");
            int choice = in.nextInt();
            ps.println(choice);
            switch(choice)
            {
                case  1:
                    System.out.println(br.readLine());
                    String plainText = in.next();
                    ps.println(plainText);
                    System.out.println(br.readLine());
                    break;
                case 2:
                    System.out.println(br.readLine());
                    String cipherText = in.next();
                    ps.println(cipherText);
                    System.out.println(br.readLine());
                    break;
                case 3:
                    System.out.println(br.readLine());
                    String bruteForce = in.next();
                    ps.println(bruteForce);
                    boolean flag = true;
                    while(flag){
                        System.out.println(br.readLine());
                        System.out.println(br.readLine());
                        String decision = in.next();
                        ps.println(decision);
                        if(decision.equals("y"))
                        {
                            flag = !flag;
                        }
                    }
                    System.out.println(br.readLine());
                    break;
                case 4:
                System.out.println(br.readLine());
                String freq = in.next();
                ps.println(freq);
                while(true){
                    System.out.println(br.readLine());
                    System.out.println(br.readLine());
                    String decision = in.next();
                    ps.println(decision);
                    if(decision.equals("y"))
                    {
                        break;
                    }
                }
                System.out.println(br.readLine());
            }
            if(choice>4)
            {
                break;
            }
        }
        s.close();
        ps.close();
        br.close();
    }
}

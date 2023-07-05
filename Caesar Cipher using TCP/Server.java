import java.util.*;
import java.net.*;
import java.io.*;
class caesarCipher 
{
    static String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    static String FREQUENCY = "etaoinsrhdlucmfywgpbvkxqjz";
    Scanner in = new Scanner(System.in);
    String encrypt(String plainText, int key)
    {
        String cipherText = "";
        for (int i = 0; i < plainText.length(); i++) {
            int index = ALPHABET.indexOf(plainText.charAt(i));
            int positionOfCipher = (index+key)%26;
            cipherText += ALPHABET.charAt(positionOfCipher);
        }
        return cipherText;
    }
    String decrypt(String cipherText, int key)
    {
        String plainText = "";
        for (int i = 0; i < cipherText.length(); i++) {
            int index = ALPHABET.indexOf(cipherText.charAt(i));
            int a = index - key;
            if(a < 0)
            {
                int modValue = (a%26)+26;
                plainText += ALPHABET.charAt(modValue);
            }
            else
            {
                plainText += ALPHABET.charAt(a%26);
            }
        }
        return plainText;
    }
    int bruteForce(String cipherText, PrintStream ps, BufferedReader br)
    {
        int key=1;
        try{
        for (int i = 1; i <= 25; i++) {
            String plainText = decrypt(cipherText, i);
            ps.println("press 'y' for meaningful and 'n' for not meaningful");
            ps.println(plainText+" : "+i);
            String isMeaningful =  br.readLine();
            if(isMeaningful.equals("y"))
            {
                key = i;
                break;
            }
        }
    }
    catch(Exception e)
    {
        System.out.println(e);
    }
        return key;
    }
    int frequencyAnalysis(String cipherText, PrintStream ps, BufferedReader br)
    {
        int key = 1;
        int arr[] = new int[26];
        for (int i = 0; i < cipherText.length(); i++) {
            int index = ALPHABET.indexOf(cipherText.charAt(i));
            arr[index]+=1;
        }
        for (int i = 0; i < arr.length; i++) {
            int maxIndex = findMaxIndex(arr);
            if(arr[maxIndex] == 0)
            {
                break;
            }
            for (int j = 0; j < FREQUENCY.length(); j++) {
                int modValue = ALPHABET.charAt(maxIndex)-FREQUENCY.charAt(j);
                if(modValue < 0)
                {
                    modValue = (modValue%26)+26;
                }
                else
                {
                    modValue = modValue%26;
                }
                String a = decrypt(cipherText, modValue);
                try{
                    ps.println("press 'y' for meaningful and 'n' for not meaningful");
                    ps.println(a +" : "+modValue);
                    String isMeaningful;
                    isMeaningful = br.readLine();
                    if(isMeaningful.equals("y"))
                    {
                        key = modValue;
                        return key;
                    }
            }
            catch(Exception e){
                System.out.println(e);
            }
            }
            arr[maxIndex]=0;
        }
        return key;
    }
    int findMaxIndex(int arr[])
    {
        int max = 0;
        for (int i = 1; i < arr.length; i++) {
            if(arr[i]>=arr[max])
            {
                max = i;
            }
        }
        return max;
    }
}
public class Server
{
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(3333);
        Socket s = ss.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintStream ps = new PrintStream(s.getOutputStream());
        Scanner in = new Scanner(System.in);
        caesarCipher cc= new caesarCipher();
        int key = Integer.parseInt(br.readLine());
        while(true)
        {
            int choice = Integer.parseInt(br.readLine());
            System.out.println("Given choice :"+choice);
            switch(choice)
            {
                case  1:
                    ps.println("Enter the plain text to encrypt:");
                    String plainText = br.readLine();
                    System.out.println("Plain text Received :"+plainText);
                    String decoded = cc.encrypt(plainText.toLowerCase(), key);
                    ps.println(decoded);
                    break;
                case 2:
                    ps.println("Enter the cipher text to decrypt:");
                    String cipherText = br.readLine();
                    System.out.println("Cipher text Received :"+cipherText);
                    String encoded = cc.decrypt(cipherText.toLowerCase(), key);
                    ps.println(encoded);
                    break;
                case 3:
                    ps.println("Enter the cipher text to find using brute force:");
                    String bruteForce = br.readLine();
                    System.out.println("Cipher text Received for brute Force :"+bruteForce);
                    int cipherKey = cc.bruteForce(bruteForce, ps, br);
                    ps.println("Key found by bruteforce :"+cipherKey);
                    break;
                case 4:
                    ps.println("Enter the cipher text to find using frequency analysis:");
                    String frequencyAnalysis = br.readLine();
                    System.out.println("Cipher text Received :"+frequencyAnalysis);
                    int frequencyKey = cc.frequencyAnalysis(frequencyAnalysis, ps, br);
                    ps.println("Key found by Frequency Analysis is :"+frequencyKey);
                    break;
                default:
                    break;
            }
            if(choice>4)
            {
                break;
            }
        }
        ss.close();
        s.close();
        br.close();
        ps.close();
    }
}
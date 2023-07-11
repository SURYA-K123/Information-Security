import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;

class Hashing {
    public static HashMap <String,String> users = new HashMap <String,String> ();
    public static HashMap <String,String> hashes = new HashMap <String,String> ();
    public static String a = "abcdefghijklmnopqrstuvwxyz";

    void perm(String st,int length)
    {
        if(st.length()== length)
        {
            String hash = encodePassword(st);
            hashes.put(hash,st);
            return;
        }
        for(int i=0;i<26;i++)
        {
            String str = st+a.charAt(i);
            perm(str,length);
        }
    }
    String hashPassword(String username)
    {
        String hash = users.get(username);
        String password = hashes.get(hash);
        return password;
    }
    String encodePassword(String password) {
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] passwordBytes = password.getBytes();
            byte[] hashBytes = digest.digest(passwordBytes);
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }
}

class Main{
    public static void main(String[] args) {
        Hashing hs = new Hashing();
        hs.perm("", 4);
        Scanner in = new Scanner(System.in); 
        while(true)
        {
            System.out.println("1:Generate password  2:Print Password  3:Password Cracker  4:exit");
            int choice = in.nextInt();
            if (choice == 1)
            {
                System.out.println("Enter the username :");
                String username = in.next().toLowerCase();
                System.out.println("Enter the password :");
                String password = in.next().toLowerCase();
                String hashPassword = hs.encodePassword(password);
                hs.users.put(username, hashPassword);
            }
            else if (choice == 2)
            {
                System.out.println(hs.users);
            }
            else if (choice == 3)
            {
                System.out.println("Enter the username :");
                String username = in.next().toLowerCase();
                System.out.println(hs.hashPassword(username));
            }
            else
            {
                break;
            }
        }
    }
}

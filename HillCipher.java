import java.util.Arrays;
import java.util.Scanner;

public class HillCipher {
    static String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    int[][] stringToMatrix(String string, int keyorder)
    {
        int length = string.length();
        int cols = Math.ceilDivExact(length,keyorder);
        int matrix[][] = new int[keyorder][cols];
        int requiredX = keyorder*cols-length;
        for (int i = 0; i < requiredX; i++) {
            string += "x";
        }
        int index = 0;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < keyorder; j++) {
                matrix[j][i] = ALPHABET.indexOf(string.charAt(index));
                index+=1;
            }
        }
        return matrix;
    }
    int[][] matrixMultiplication(int mat1[][],int mat2[][])
    {
        int multipliedMatrix[][] = new int[mat1.length][mat2[0].length];
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                int sum = 0;
                for (int k = 0; k < mat2.length; k++) {
                    sum += mat1[i][k]*mat2[k][j];
                }
                multipliedMatrix[i][j]=sum%26;
            }
        }
        return multipliedMatrix;
    }
}
int[][] scalarMultiply(int scalar, int mat[][]) {
        int mat1[][] = new int[mat.length][mat.length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                mat1[i][j] = getMod(scalar * mat[i][j]);
            }
        }
        return mat1;
    }
class Main
{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HillCipher hc = new HillCipher();
        System.out.println("Enter the order of key :");
        int keyorder = in.nextInt();
        int key[][] = new int[keyorder][keyorder];
        for (int i = 0; i < keyorder; i++) {
            for (int j = 0; j < keyorder; j++) {
                System.out.println("Key A"+(i+1)+""+(j+1)+":");
                key [i][j] = in.nextInt();
            }
        }
        int mat[][] = hc.stringToMatrix("abcdef",3);
        int a[][] = hc.matrixMultiplication(key, mat);
        for (int i = 0; i < a.length; i++) {
            System.out.println(Arrays.toString(a[i]));
        }
    }
}

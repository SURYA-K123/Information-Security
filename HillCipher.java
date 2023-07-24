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
    int multiplicativeInverse(int val) {
        int q, r, a, b, t;
        int t1 = 0;
        int t2 = 1;
        a = 26;
        b = val;
        while (a != 1 && b != 0) {
            q = a / b;
            r = a % b;
            t = t1 - (q * t2);
            a = b;
            b = r;
            t1 = t2;
            t2 = t;
        }
        return getMod(t1+t2);
    }
    int[][] adjointMatrix(int mat[][]) {
        int length = mat.length;
        boolean isNegative = false;
        int adj[][] = new int[length][length];
        if(mat.length==2)
        {
            adj[0][0] = getMod(mat[1][1]);
            adj[1][1] = getMod(mat[0][0]);
            adj[0][1] = getMod(mat[0][1]*(-1));
            adj[1][0] = getMod(mat[1][0]*(-1));
            return adj;
        }
        for (int i = 0; i < length; i++) {

            for (int j = 0; j < length; j++) {

                int subMat[][] = new int[length - 1][length - 1];
                int row = 0;

                for (int k = 0; k < length; k++) {
                    int column = 0;
                    if (k == i) {
                        continue;
                    } else {
                        for (int l = 0; l < length; l++) {
                            if (l == j) {
                                continue;
                            } else {
                                subMat[row][column] = mat[k][l];
                                column++;
                            }
                        }
                    }
                    row++;
                }
                if (!isNegative) {
                    adj[j][i] = getMod(determinant(subMat));
                    isNegative = !isNegative;
                } else {
                    adj[j][i] = getMod((-1) * determinant(subMat));
                    isNegative = !isNegative;
                }

            }
            isNegative = !isNegative;
        }
        return adj;
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

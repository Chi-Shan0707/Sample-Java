import java.util.Random;
public class MatricMatrixMulVerifier {
    public static boolean check(int[][] A, int[][] B, int[][] C) {

        int m = A.length;       // A 的行数（矩阵 A 的行数）
        int n = A[0].length;    // A 的列数（也是 B 的行数，矩阵乘法要求 A 的列数等于 B 的行数）
        int l = B[0].length;    // B 的列数（结果矩阵 C 的列数）

        java.util.Random random = new java.util.Random();
        int[] X = new int[l];      // 随机向量长度为 l（与 B 的列数一致）
        int[] BX = new int[n];     // B * X 的结果，长度为 n
        int[] ABX = new int[m];    // A * (B * X) 的结果，长度为 m
        int[] CX = new int[m];     // C * X 的结果，长度为 m

        // 将 X 填充为随机 0/1 向量
        for (int i = 0; i < l; ++i) {
            X[i] = random.nextInt(2); // 生成 0 或 1
        }

        // 计算 CX = C * X
        for (int i = 0; i < m; ++i) {
            CX[i] = 0;
            for (int k = 0; k < l; ++k) {
                CX[i] += C[i][k] * X[k];
            }
        }

        // 计算 BX = B * X
        for (int j = 0; j < n; ++j) {
            BX[j] = 0;
            for (int k = 0; k < l; ++k) {
                BX[j] += B[j][k] * X[k];
            }
        }

        // 计算 ABX = A * BX
        for (int i = 0; i < m; ++i) {
            ABX[i] = 0;
            for (int j = 0; j < n; ++j) {
                ABX[i] += A[i][j] * BX[j];
            }
        }

        // 比较 ABX 和 CX
        for (int i = 0; i < m; ++i) {
            if (ABX[i] != CX[i]) {
                return false;
            }
        }

        return true;
    }
    public static boolean verify(int trials, int[][] A, int[][] B, int[][] C)
    {
        for ( int i=0; i< trials; ++i)
        {
            if ( !check(A,B,C))
            {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        // Example usage
        int[][] A = { {1, 2}, {3, 4} };
        int[][] B = { {5, 6}, {7, 8} };
        int[][] C = { {19, 22}, {43, 50} }; // Correct product of A and B

        int trials = 10;
        boolean result = verify(trials, A, B, C);
        System.out.println("Verification result: " + result);
    }
}

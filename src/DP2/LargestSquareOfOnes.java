package DP2;

public class LargestSquareOfOnes {
    public int largest(int[][] matrix) {
        // Assumptions : The matrix is a binary matrix
        // (only contains 0 or 1 as the values.
        // it is not null, and has size N*N, N>= 0.
        int N = matrix.length;
        if (N == 0) {
            return 0;
        }
        int result = 0;
//        dp[i][j] means the largest square of 1's with right bottom corner as matrix[i][j]
        int[][] largest = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 || j == 0) {
                    largest[i][j] = matrix[i][j] == 1 ? 1 : 0;
                    System.out.println("first if, i = " + i + ", j = " + j + ", largest[i][j] : " + largest[i][j]
                            + ", matrix[i][j] : " + matrix[i][j]);
                } else if (matrix[i][j] == 1) {
                    largest[i][j] = Math.min(largest[i][j - 1] + 1, largest[i - 1][j] + 1);
                    largest[i][j] = Math.min(largest[i - 1][j - 1] + 1, largest[i][j]);
                }
                result = Math.max(result, largest[i][j]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LargestSquareOfOnes l = new LargestSquareOfOnes();
        int[][] matrix = {{1, 1, 1, 1},
                          {1, 1, 1, 1},
                          {0, 1, 1, 1},
                          {1, 1, 1, 1}};
/*
            int[][] largest = {{1, 1, 1, 1},
                               {1, 2, 2, 2},
                               {0, 1, 2, 3},
                               {1, 1, 2, 3}};

 */
        System.out.println(l.largest(matrix));
    }
}

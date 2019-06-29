package com.anand.coding.dsalgo.dp;

/**
 * Given a string A, find the longest palindromic sub-string.
 */
public class LongestPalindromicSubString {

    /**
     * DP Tabulation solution
     *
     * @param A
     * @return  lps
     */
    public static String lps(char[] A) {
        int n = A.length;

        int [][]DP = new int[n+1][n+1];

        if(n==0){
            return null;
        }
        //String of length=1 and 0 is palindromic
        for(int i=0; i<=n; i++) {
            DP[0][i] = 0;
            DP[i][i] = 1;
        }

        int maxI=1, maxJ=1;

        //String of length=2 is palindromic
        for(int i=1; i<=n-1; i++) {
            DP[i][i+1] = A[i-1]==A[i] ? 2 : 1;
        }

        //length 3 onward
        for(int numberOfElement=3; numberOfElement<=n; numberOfElement++){

            //To process DP one side only
            for(int i=1; i+numberOfElement-1<=n; i++){
                int j = i+numberOfElement-1;

                if(A[i-1]==A[j-1]) {
                    DP[i][j] = 2 + DP[i+1][j-1];

                    if (DP[i][j] > DP[maxI][maxJ]) {
                        maxI=i; maxJ=j;
                    }
                }
            }
        }

        printDPArray(DP,n,n);
        return (new String(A, maxI-1, maxJ-maxI+1));
    }


    /*
     * @param A
     * @return
     */
    public static String lpsRec(char[] A) {

        int n = A.length;

        if(n==0){
            return null;
        }

        int [][]DP = new int[n+1][n+1];

        int lpsLength = lpsRec(A,1, A.length, DP);
        printDPArray(DP,n,n);

        int maxI=0, maxJ=0;

        for(int i=0; i<=n; i++){
            for(int j=0; j<=n; j++){
                if (DP[i][j] > DP[maxI][maxJ]) {
                    maxI=i;
                    maxJ=j;
                }
            }
        }
        return (new String(A, maxI-1, maxJ-maxI+1));

    }


    /**
     *
     * @param A
     * @param l
     * @param r
     * @param DP
     */
    private static int lpsRec(char[] A, int l, int r, int [][]DP) {

        if(l>r){
            return 0;
        }

        if(l==r){
            return DP[l][r]= 1;
        }

        //Already calculated?
        if(DP[l][r] >0){
            return DP[l][r];
        }

        DP[l][r] = (A[l-1] == A[r-1]) ?
                       2 + lpsRec(A, l+1, r-1, DP) : 0;

        lpsRec(A, l+1, r, DP);
        lpsRec(A, l, r-1, DP);

        return DP[l][r];

    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        char[] A = "axanjnax".toCharArray();

        /**
         *
         *    1   0   0   0   0   0   0   0   0
         *    0   1   1   3   0   0   0   2   0
         *    0   0   1   1   0   0   0   0   7
         *    0   0   0   1   1   0   0   5   0
         *    0   0   0   0   1   1   3   0   0
         *    0   0   0   0   0   1   1   0   0
         *    0   0   0   0   0   0   1   1   0
         *    0   0   0   0   0   0   0   1   1
         *    0   0   0   0   0   0   0   0   1
         * xanjnax
         */
        System.out.println(lps(A));


        /**
         *
         *    0   0   0   0   0   0   0   0   0
         *    0   1   0   3   0   0   0   2   0
         *    0   0   1   0   0   0   0   0   7
         *    0   0   0   1   0   0   0   5   0
         *    0   0   0   0   1   0   3   0   0
         *    0   0   0   0   0   1   0   0   0
         *    0   0   0   0   0   0   1   0   0
         *    0   0   0   0   0   0   0   1   0
         *    0   0   0   0   0   0   0   0   1
         * xanjnax
         */
        System.out.println(lpsRec(A));
    }

    /**
     *
     * @param DP
     * @param n
     * @param m
     */
    public static void printDPArray(int [][] DP, int n, int m)
    {
        System.out.println();
        for(int i=0; i<=n; i++){

            for(int w =0; w<=m; w++){
                System.out.print(String.format("%4d", DP[i][w]));
            }
            System.out.println();
        }
    }
}
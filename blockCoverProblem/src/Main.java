import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Main {

    public static int TestCaseNum = 0;
    public static int TestSuccCnt = 0;

    public static int ansCount = 0;
    public static int[][][] shape = {
            {{0, 0}, {0, 1}, {1, 0}},
            {{0, 0}, {0, 1}, {1, 1}},
            {{0, 0}, {1, 0}, {1, 1}},
            {{0, 0}, {1, 0}, {1, -1}},
    };

    /* Test our function goes well */
    public static void main(String args[]) {
        String testRet = "";
        String testAns0 = "2";
        String testAns1 = "0";
        String testAns2 = "2";
        String testAns3 = "1514";
        /* Test cases */
        // Test case0
        int[][] testBoard0 = {
                {0, 0, 0},
                {0, 0, 0},
        };
        testRet += blockCoverInit(testBoard0, 2, 3);
        printTestResult(
                testAns0, testRet
        );

        // Test case1
        testRet = "";
        int[][] testBoard1 = {
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 0, 0, 0, 1, 1},
        };
        testRet += blockCoverInit(testBoard1, 3, 7);
        printTestResult(
                testAns1, testRet
        );

        // Test case2
        int[][] testBoard2 = {
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 0, 0, 1, 1, 1},
        };
        testRet = "";
        testRet += blockCoverInit(testBoard2, 3, 7);
        printTestResult(testAns2, testRet);


        // Test case3
        int[][] testBoard3 = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };
        testRet = "";
        testRet += blockCoverInit(testBoard3, 8, 10);
        printTestResult(testAns3, testRet);


        /* End of test, print test sumup */
        testResultSumup();
    }

    public static void printTestResult(String eVal, String fVal) {
        /* calling print function == test case added */
        TestCaseNum ++;
        System.out.print("Test case" + TestCaseNum + " Result : ");

        if(eVal.equals(fVal)) {
            System.out.println("* success! *");
            TestSuccCnt ++;
        }
        else {
            System.out.println("- failed; -");
        }

        System.out.println("    Expected value was (" + eVal + ")");
        System.out.println("    Function Output is (" + fVal + ")\n");
    }

    public static void testResultSumup() {
        System.out.println("==================================================");
        System.out.println("Test : " + TestSuccCnt + "/" + TestCaseNum + ".");
    }

    public static int blockCoverInit(int[][] board, int H, int W) {
        ansCount = 0;
        int remainBlock = 0;
        for (int i = 0; i < H; i ++ ) {
            for(int j = 0; j < W; j ++) {
                if (board[i][j] == 0) remainBlock ++;
            }
        }
        if(remainBlock % 3 != 0)
            return 0;

        blockCover(board, H, W, remainBlock, new int[] {0, 0});

        return ansCount;
    }

    public static void blockCover(int[][] board, int H, int W, int remainBlock, int[] curr) {
        int[] blank = findBlank(board, H, W, curr);
        for(int k = 0; k < 4; k ++) {
            if(isInBoard(blank[0], blank[1], k, H, W, board)) {
                if(remainBlock == 3) {
                    ansCount ++;
                } else {
                    blockCover(board, H, W, remainBlock - 3, new int[] {blank[0], blank[1]});
                }
                resetBoard(blank[0], blank[1], k, H, W, board);
            }
        }
        return; // we failed to fill earliest block
    }

    public static int[] findBlank(int[][] board, int H, int W, int[] curr) {
        for (int j = curr[1]; j < W; j++) {
            if (board[curr[0]][j] == 0) {
                return new int[] {curr[0], j};
            }
        }

        for (int i = curr[0] + 1; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (board[i][j] == 0) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {-1, -1};
    }

    public static boolean isInBoard(int i, int j, int stat, int H, int W, int[][] board) {
        int tmpI1 = i + shape[stat][1][0];
        int tmpJ1 = j + shape[stat][1][1];
        int tmpI2 = i + shape[stat][2][0];
        int tmpJ2 = j + shape[stat][2][1];

        if( (0 <= tmpI1 && tmpI1 < H) && (0 <= tmpI2 && tmpI2 < H)
                && (0 <=tmpJ1 && tmpJ1 < W) && (0 <=tmpJ2 && tmpJ2 < W) ) {
            if(board[tmpI1][tmpJ1] == 0 && board[tmpI2][tmpJ2] == 0) {
                board[tmpI1][tmpJ1] = 1;
                board[tmpI2][tmpJ2] = 1;
                board[i][j] = 1;
                return true;
            }
        }
        return false;
    }

    public static void resetBoard(int i, int j, int stat, int H, int W, int[][] board) {
        int tmpI1 = i + shape[stat][1][0];
        int tmpJ1 = j + shape[stat][1][1];
        int tmpI2 = i + shape[stat][2][0];
        int tmpJ2 = j + shape[stat][2][1];

        board[tmpI1][tmpJ1] = 0;
        board[tmpI2][tmpJ2] = 0;
        board[i][j] = 0;
    }

    public static void printBoard(int[][] board, int H, int W) {
        for(int i = 0; i < H; i ++) {
            for(int j = 0; j < W; j ++) {
                System.out.print("-" + board[i][j]);
            }
            System.out.println();
        }

        System.out.println("____________________________");
    }
}

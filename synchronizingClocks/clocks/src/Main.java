import java.util.*;

public class Main {

    public static int[][] buttons = {
            {0, 1, 2},
            {3, 7, 9, 11},
            {4, 10, 14, 15},
            {0, 4, 5, 6, 7},
            {6, 7, 8, 10, 12},
            {0, 2, 14, 15},
            {3, 14, 15},
            {4, 5, 7, 14, 15},
            {1, 2, 3, 4, 5},
            {3, 4, 5, 9, 13}
    };

    public static void main(String[] args) {
        TestCasePrinter tests = new TestCasePrinter();

        Integer[] clockStat1 = {12, 6, 6, 6, 6, 6, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};
        Integer[] clockStat2 = {12, 9, 3, 12, 6, 6, 9, 3, 12, 9, 12, 9, 12, 12, 6, 6};
        long startTime;
        long stopTime;

        startTime = System.currentTimeMillis();
        tests.printTestResult("2",
                "" + syncClocks_A(clockStat1));
        stopTime = System.currentTimeMillis();
        System.out.println("TestCase1 time : " + (stopTime - startTime) + " miliseconds.");

        startTime = System.currentTimeMillis();
        tests.printTestResult("9",
                "" + syncClocks_A(clockStat2));
        stopTime = System.currentTimeMillis();
        System.out.println("TestCase2 time : " + (stopTime - startTime) + " miliseconds.");

        tests.testResultSumup();
    }

    // planA. using BFS with using Queue
    // result >> get OutOfMemoryError in second test case(answer was 9)
    public static int syncClocks_A(Integer[] initStat) {
        Queue<Integer[]> clockBfs = new LinkedList<>();
        int clen = initStat.length;
        int blen = buttons.length;
        int[] countFour = new int[blen];
        Arrays.fill(countFour, 0); // if we clicked switch four time, the clock's position are reset.
        int[] buttonsLen = new int[blen];
        for(int i = 0; i < blen; i ++) {
            buttonsLen[i] = buttons[i].length;
        }

        // initializing first queue item with switch counting
        Integer[] currStat = new Integer[clen + 1];
        Integer[] nextStat = new Integer[clen + 1];
        System.arraycopy(initStat, 0, currStat, 0, clen);
        currStat[clen] = 0;
        boolean yetSorted = true;
        clockBfs.offer(currStat);
        do{
            currStat = clockBfs.poll();
            currStat[clen] ++;
            for(int i = 0; i < blen; i ++) {
                if(!checkFour(countFour, blen, i)) {
                    nextStat = currStat.clone();
                    pressButton(nextStat, buttons[i], buttonsLen[i]);
                    clockBfs.offer(nextStat);
                    if(!(yetSorted = !isSorted(nextStat, clen))) break;
                }
            }
        } while(!clockBfs.isEmpty() && yetSorted);
        return yetSorted ? -1 : currStat[clen];
    }

    // planB. complete search with using resursion.
    public static int syncClocks_B(Integer[] initStat) {
        int clen = initStat.length;
        int blen = buttons.length;
        int[] countFour = new int[blen];
        Arrays.fill(countFour, 0); // if we clicked switch four time, the clock's position are reset.
        int[] buttonsLen = new int[blen];
        for(int i = 0; i < blen; i ++) {
            buttonsLen[i] = buttons[i].length;
        }

        return -1;
    }

    public static int B_recursion(Integer[] stat) {
        return -1;
    }



    public static boolean isSorted(Integer[] currStat, int length) {
        for(int i = 0; i < length; i ++) {
            if(currStat[i] != 12 && currStat[i] != 0) return false;
        }
        return true;
    }

    public static boolean checkFour(int[] countFour, int length, int pos) {
        boolean flag = false;
        countFour[pos] ++;
        for(int i = 0; i < length; i ++) {
            if(countFour[i] == 4) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static void pressButton(Integer[] stat, int[] aButton, int length) {
        for(int i = 0; i < length; i ++)
            stat[aButton[i]] = (stat[aButton[i]] + 3) % 12;
    }

    public static void printArr(Integer[] stat) {
        for(int i = 0; i < stat.length; i ++) {
            System.out.print(stat[i] + "-");
        }
        System.out.println();
    }
}
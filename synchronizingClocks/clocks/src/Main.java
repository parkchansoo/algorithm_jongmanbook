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

        int[] clockStat1 = {12, 6, 6, 6, 6, 6, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};
        int[] clockStat2 = {12, 9, 3, 12, 6, 6, 9, 3, 12, 9, 12, 9, 12, 12, 6, 6};
        long startTime;
        long stopTime;

        startTime = System.currentTimeMillis();
        tests.printTestResult("", "");
        stopTime = System.currentTimeMillis();
        System.out.println("TestCase1 time : " + (stopTime - startTime) + " miliseconds.");

        startTime = System.currentTimeMillis();
        tests.printTestResult("", "");
        stopTime = System.currentTimeMillis();
        System.out.println("TestCase2 time : " + (stopTime - startTime) + " miliseconds.");

        startTime = System.currentTimeMillis();
        tests.printTestResult("", "");
        stopTime = System.currentTimeMillis();
        System.out.println("TestCase3 time : " + (stopTime - startTime) + " miliseconds.");

        tests.testResultSumup();
    }

    public static int syncClocks() {
        return 0;
    }
}
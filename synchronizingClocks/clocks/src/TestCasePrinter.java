public class TestCasePrinter {
    private int TestCaseNum = 0;
    private int TestSuccCnt = 0;

    TestCasePrinter() {
        this.TestCaseNum = 0;
        this.TestSuccCnt = 0;
    }

    TestCasePrinter(int TestCaseNum, int TestSuccCnt) {
        this.TestCaseNum = TestCaseNum;
        this.TestSuccCnt = TestSuccCnt;
    }

    public void printTestResult(String eVal, String fVal) {
        /* calling print function == test case added */
        TestCaseNum ++;
        System.out.println("--------------------------------------------");
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

    public void testResultSumup() {
        System.out.println("==================================================");
        System.out.println("Test : " + TestSuccCnt + "/" + TestCaseNum + ".");
    }
}

public class PalindromeNumber {
    public static class TestCase {
        int x;
        boolean expected;

        public TestCase(int x, boolean expected) {
            this.x = x;
            this.expected = expected;
        }
    }

    public static TestCase[] getTestCases() {
        return new TestCase[]{
            // Given Test Cases (Manually Added in Serial Order)
            new TestCase(121, true),
            new TestCase(-121, false),
            new TestCase(10, false),

            // Additional Edge Cases
            new TestCase(0, true),
            new TestCase(1, true),
            new TestCase(11, true),
            new TestCase(1001, true),
            new TestCase(1221, true),
            new TestCase(12321, true),
            new TestCase(123321, true),
            new TestCase(123421, false),
            new TestCase(1000021, false),
            new TestCase(2147447412, true),
        };
    }

    public static void main(String[] args) {
        TestCase[] testCases = getTestCases();
        boolean success = true;
        int idx = -1;
        
        for (int i = 0; i < testCases.length; i++) {
            Solution obj = new Solution();
            boolean result = obj.isPalindrome(testCases[i].x);
            if (result != testCases[i].expected) {
                success = false;
                idx = i;
                break;
            }
        }

        if (success) {
            System.out.println("Accepted");
        } else {
            System.out.println("Wrong Answer : " + idx + "/" + testCases.length);
        }
    }
}

public class ValidParenthesis {
    public class TestCase {
        String s;
        boolean expected;

        public TestCase(String s, boolean expected) {
            this.s = s;
            this.expected = expected;
        }
    }

    public TestCase[] getTestCases() {
        return new TestCase[] {
            new TestCase("()", true),
            new TestCase("()[]{}", true),
            new TestCase("(]", false),
            new TestCase("([)]", false),
            new TestCase("{[]}", true),
            new TestCase("]", false),
            new TestCase("(((())))", true),
            new TestCase("((((((((", false),
            new TestCase("(){}}{", false),
            new TestCase("({[)][]}", false)
        };
    }

    public static void main(String[] args) {
        ValidParenthesis mainObj = new ValidParenthesis();
        TestCase[] testCases = mainObj.getTestCases();
        boolean success = true;
        int idx = -1;
        for (int i = 0; i < testCases.length; i++) {
            Solution obj = new Solution();
            TestCase testCase = testCases[i];
            boolean output = obj.isValid(testCase.s);
            if (output != testCase.expected) {
                idx = i;
                success = false;
                break;
            }
        }
        System.out.println(success ? "Accepted" : "Wrong Answer : "+(idx)+"/"+testCases.length);
    }
}

public class LongestCommonPrefix {
    public static class TestCase {
        String[] input;
        String expected;

        public TestCase(String[] input, String expected) {
            this.input = input;
            this.expected = expected;
        }
    }

    public TestCase[] getTestCases() {
        return new TestCase[]{
            new TestCase(new String[]{"flower", "flow", "flight"}, "fl"),
            new TestCase(new String[]{"dog", "racecar", "car"}, ""),
            new TestCase(new String[]{"interspecies", "interstellar", "interstate"}, "inters"),
            new TestCase(new String[]{"apple", "ape", "april"}, "ap"),
            new TestCase(new String[]{""}, ""),
            new TestCase(new String[]{"a"}, "a"),
            new TestCase(new String[]{"abc", "abc", "abc"}, "abc"),
            new TestCase(new String[]{"prefix", "pre", "presentation"}, "pre"),
            new TestCase(new String[]{"alone"}, "alone"),
            new TestCase(new String[]{"same", "same", "same"}, "same")
        };
    }
    
    public static void main(String[] args) {
        LongestCommonPrefix tester = new LongestCommonPrefix();
        TestCase[] testCases = tester.getTestCases();
        boolean success = true;
        int idx = -1;
        
        for (int i = 0; i < testCases.length; i++) {
            Solution solution = new Solution();
            TestCase testCase = testCases[i];
            String output = solution.longestCommonPrefix(testCase.input);
            if (!output.equals(testCase.expected)) {
                idx = i;
                success = false;
                break;
            }
        }
        
        System.out.println(success ? "Accepted" : "Wrong Answer : " + (idx) + "/" + testCases.length);
    }
}
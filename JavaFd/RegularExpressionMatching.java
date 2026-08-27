public class RegularExpressionMatching {
    static class TestCase {
        String text;
        String pattern;
        boolean expected;

        public TestCase(String text, String pattern, boolean expected) {
            this.text = text;
            this.pattern = pattern;
            this.expected = expected;
        }
    }

    public TestCase[] getTestCases() {
        return new TestCase[]{
            // Given Example Test Cases
            new TestCase("aa", "a", false),
            new TestCase("aa", "a*", true),
            new TestCase("ab", ".*", true),
            new TestCase("aab", "c*a*b", true),
            new TestCase("mississippi", "mis*is*p*.", false),
            new TestCase("aab", "c*a*b", true),
            new TestCase("aaa", "ab*ac*a", true),
            new TestCase("bbab", "b*a*", false),
            new TestCase("aabcbcbcaccbcaabc", ".*a*aa*.*b*.c*.*a*", true),
            new TestCase("abbabaaaaaaacaa", "a*.*b.a.*c*b*a*c*", true),
            new TestCase("abcaaaaaaabaabcabac", ".*ab.a.*a*a*.*b*b*", true),
            new TestCase("cbaacacaaccbaabcb", "c*b*b*.*ac*.*bc*a*", true),
            new TestCase("cbccaababcbabac", "c*aab*.*b.b.*.*a*.", false),
            new TestCase("caccccaccbabbcb", "c*c*b*a*.*c*.a*a*a*", true),
            new TestCase("ccbbcabcbbaabaccc", "c*a*.*a*a*.*c*b*b*.", true),
            new TestCase("bacacaababbbcbc", ".*a*.*a*.aa*c*b*c", false),
            new TestCase("bcccccbaccccacaa", ".*bb*c*a*b*.*b*b*c*", true),

        };
    }

    public static void main(String[] args) {
        RegularExpressionMatching mainObj = new RegularExpressionMatching();
        TestCase[] testCases = mainObj.getTestCases();
        boolean success = true;
        int idx = -1;
        
        for (int i = 0; i < testCases.length; i++) {
            Solution obj = new Solution();
            TestCase testCase = testCases[i];
            boolean output = obj.isMatch(testCase.text, testCase.pattern);
            if (output != testCase.expected) {
                idx = i;
                success = false;
                break;
            }
        }
        System.out.println(success ? "Accepted" : "Wrong Answer : " + idx + "/" + testCases.length);
    }
}

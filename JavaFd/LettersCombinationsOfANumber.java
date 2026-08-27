import java.util.*;

public class LettersCombinationsOfANumber {
    public static class TestCase {
        String digits;
        List<String> expected;

        public TestCase(String digits, List<String> expected) {
            this.digits = digits;
            this.expected = expected;
        }
    }

    public static TestCase[] getTestCases() {
        return new TestCase[]{
            // Given Test Cases
            new TestCase("23", Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf")),
            new TestCase("", Collections.emptyList()),
            new TestCase("2", Arrays.asList("a", "b", "c")),
            // Additional 10 test cases
            new TestCase("7", Arrays.asList("p", "q", "r", "s")),
            new TestCase("9", Arrays.asList("w", "x", "y", "z")),
            new TestCase("79", Arrays.asList("pw", "px", "py", "pz", "qw", "qx", "qy", "qz", "rw", "rx", "ry", "rz", "sw", "sx", "sy", "sz")),
            new TestCase("234", Arrays.asList("adg", "adh", "adi", "aeg", "aeh", "aei", "afg", "afh", "afi", "bdg", "bdh", "bdi", "beg", "beh", "bei", "bfg", "bfh", "bfi", "cdg", "cdh", "cdi", "ceg", "ceh", "cei", "cfg", "cfh", "cfi")),
            new TestCase("567", Arrays.asList("jmp", "jmq", "jmr", "jms", "jnp", "jnq", "jnr", "jns", "jop", "joq", "jor", "jos", "kmp", "kmq", "kmr", "kms", "knp", "knq", "knr", "kns", "kop", "koq", "kor", "kos", "lmp", "lmq", "lmr", "lms", "lnp", "lnq", "lnr", "lns", "lop", "loq", "lor", "los")),
            new TestCase("88", Arrays.asList("tt", "tu", "tv", "ut", "uu", "uv", "vt", "vu", "vv")),
            new TestCase("92", Arrays.asList("wa", "wb", "wc", "xa", "xb", "xc", "ya", "yb", "yc", "za", "zb", "zc")),
            new TestCase("2345", Arrays.asList("adgj", "adgk", "adgl", "adhj", "adhk", "adhl", "adij", "adik", "adil", "aegj", "aegk", "aegl", "aehj", "aehk", "aehl", "aeij", "aeik", "aeil", "afgj", "afgk", "afgl", "afhj", "afhk", "afhl", "afij", "afik", "afil", "bdgj", "bdgk", "bdgl", "bdhj", "bdhk", "bdhl", "bdij", "bdik", "bdil", "begj", "begk", "begl", "behj", "behk", "behl", "beij", "beik", "beil", "bfgj", "bfgk", "bfgl", "bfhj", "bfhk", "bfhl", "bfij", "bfik", "bfil", "cdgj", "cdgk", "cdgl", "cdhj", "cdhk", "cdhl", "cdij", "cdik", "cdil", "cegj", "cegk", "cegl", "cehj", "cehk", "cehl", "ceij", "ceik", "ceil", "cfgj", "cfgk", "cfgl", "cfhj", "cfhk", "cfhl", "cfij", "cfik", "cfil"))
        };
    }

    public static void main(String[] args) {
        TestCase[] testCases = getTestCases();
        boolean success = true;
        int idx = -1;
        for (int i = 0; i < testCases.length; i++) {
            Solution obj = new Solution();
            List<String> result = obj.letterCombinations(testCases[i].digits);
            if (!result.equals(testCases[i].expected)) {
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

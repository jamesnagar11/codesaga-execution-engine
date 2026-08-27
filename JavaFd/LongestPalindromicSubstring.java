import java.util.*;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        
        String[][] testCases = {
            {"babad", "bab", "aba"},
            {"cbbd", "bb"},
            {"a", "a"},
            {"ac", "a", "c"},
            {"racecar", "racecar"},
            {"abcdef", "a", "b", "c", "d", "e", "f"},
            {"bananas", "anana"},
            {"forgeeksskeegfor", "geeksskeeg"},
            {"abba", "abba"},
            {"xyzzyx", "xyzzyx"},
            {"aabbaa", "aabbaa"},
            {"abcda", "a", "b", "c", "d", "a"},
            {"abccba", "abccba"}
        };
        
        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            Solution solution = new Solution();
            String input = testCases[i][0];
            String actualOutput = solution.longestPalindrome(input);
            
            boolean isValid = false;
            for (int j = 1; j < testCases[i].length; j++) {
                if (actualOutput.equals(testCases[i][j])) {
                    isValid = true;
                    break;
                }
            }
            
            if (isValid) {
                passed++;
            } else {
                System.out.println("Wrong Answer : " + (i + 1) + "/" + testCases.length);
                return;
            }
        }
        
        System.out.println("Accepted");
    }
}

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        
        String[][] testCases = {
            {"abcabcbb", "3"},
            {"bbbbb", "1"},
            {"pwwkew", "3"},
            {"", "0"},
            {"abcdef", "6"},
            {"aab", "2"},
            {"dvdf", "3"},
            {"abba", "2"},
            {"tmmzuxt", "5"},
            {"ohomm", "3"},
            {"anviaj", "5"},
            {"asjrgapa", "6"},
            {"aabbccddeeffgghh", "2"},
            {"abcdefgabcdefg", "7"},
            {"abrkaabcdefghijjxxx", "10"}
        };
        
        boolean allPassed = true;
        int idx = -1;
        for (int i = 0; i < testCases.length; i++) {
            Solution solution = new Solution();
            String input = testCases[i][0];
            int expectedOutput = Integer.parseInt(testCases[i][1]);
            int actualOutput = solution.lengthOfLongestSubstring(input);
            
            if (actualOutput != expectedOutput) {
                allPassed = false;
                break;
            }
        }
        
        if (allPassed) {
            System.out.println("Accepted");
        }
        else {
            System.out.println("Wrong Answer : "+(idx+1)+ "/" + testCases.length);
        }
    }
}
import java.util.*;

public class ContainerWithMostWater {
    public static class TestCase {
        int[] height;
        int expected;

        public TestCase(int[] height, int expected) {
            this.height = height;
            this.expected = expected;
        }
    }

    public static TestCase[] getTestCases() {
        return new TestCase[]{
            // Given Test Cases (Manually Added in Serial Order)
            new TestCase(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}, 49),
            new TestCase(new int[]{1, 1}, 1),
            
            // Additional Edge Cases
            new TestCase(new int[]{4, 3, 2, 1, 4}, 16),
            new TestCase(new int[]{1, 2, 1}, 2),
            new TestCase(new int[]{2, 3, 10, 5, 7, 8, 9}, 36),
            new TestCase(new int[]{1, 2, 4, 3}, 4),
            new TestCase(new int[]{6, 9, 3, 4, 5, 8}, 32),
            new TestCase(new int[]{1, 1, 1, 1, 1, 1}, 5),
            new TestCase(new int[]{1, 3, 2, 5, 25, 24, 5}, 24),
            new TestCase(new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5}, 40),
            new TestCase(new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}, 25),
        };
    }

    public static void main(String[] args) {
        TestCase[] testCases = getTestCases();
        
        boolean success = true;
        int idx = -1;
        for (int i = 0; i < testCases.length; i++) {
            Solution obj = new Solution();
            int expectedValue = obj.maxArea(testCases[i].height);
            if (expectedValue != testCases[i].expected) {
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

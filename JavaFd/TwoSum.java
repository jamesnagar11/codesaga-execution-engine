public class TwoSum {
    public static class TestCase {
        int[] nums;
        int target;
        int[] expected;

        public TestCase(int[] nums, int target, int[] expected) {
            this.nums = nums;
            this.target = target;
            this.expected = expected;
        }
    }

    public static TestCase[] getTestCases() {
        return new TestCase[]{
            // Given Test Cases (Manually Added in Serial Order)
            new TestCase(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}),
            new TestCase(new int[]{3, 2, 4}, 6, new int[]{1, 2}),
            new TestCase(new int[]{3, 3}, 6, new int[]{0, 1}),

            // Additional Edge Cases
            new TestCase(new int[]{1, 2, 3, 4}, 6, new int[]{1, 3}),
            new TestCase(new int[]{-3, 4, 3, 90}, 0, new int[]{0, 2}),
            new TestCase(new int[]{3, 3}, 6, new int[]{0, 1}),
            new TestCase(new int[]{1,2,3}, 5, new int[]{1,2}),
            new TestCase(new int[]{-1,-2,7}, 5, new int[]{1,2}),
            new TestCase(new int[]{0, 4, 3, 0}, 0, new int[]{0, 3}),
            new TestCase(new int[]{1, 5, -10, 554}, 6, new int[]{0, 1}),
        };
    }

    private static int[] generateLargeArray(int size) {
        int[] arr = new int[size + 1];
        for (int i = 0; i <= size; i++) {
            arr[i] = i;
        }
        return arr;
    }

    public static void main(String[] args) {
        TestCase[] testCases = getTestCases();
        boolean success = true;
        int idx = -1;
        for (int i = 0; i < testCases.length; i++) {
            Solution obj = new Solution();
            int[] expectedArray = obj.twoSum(testCases[i].nums, testCases[i].target);
            if (expectedArray.length != testCases[i].expected.length) {
                success = false;
                idx = i;
                break;
            }
            for (int j = 0; j < expectedArray.length; j++) {
                if (expectedArray[j] != testCases[i].expected[j]) {
                    idx = i;
                    success = false;
                    break;
                }
            }
        }
        if (success) {
            System.out.println("Accepted");
        } else {
            System.out.println("Wrong Answer : "+idx+"/"+testCases.length);
        }
    }
}
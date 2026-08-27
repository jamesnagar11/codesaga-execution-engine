public class MedianOfTwoSortedArrays {
    public static class TestCase {
        int[] nums1;
        int[] nums2;
        double expected;

        public TestCase(int[] nums1, int[] nums2, double expected) {
            this.nums1 = nums1;
            this.nums2 = nums2;
            this.expected = expected;
        }
    }

    public TestCase[] getTestCases() {
        return new TestCase[]{
            new TestCase(new int[]{1, 3}, new int[]{2}, 2.0),
            new TestCase(new int[]{1, 2}, new int[]{3, 4}, 2.5),
            new TestCase(new int[]{0, 0}, new int[]{0, 0}, 0.0),
            new TestCase(new int[]{}, new int[]{1}, 1.0),
            new TestCase(new int[]{2}, new int[]{}, 2.0),
            new TestCase(new int[]{1, 3, 5}, new int[]{2, 4, 6}, 3.5),
            new TestCase(new int[]{1, 1, 1}, new int[]{1, 1, 1, 1}, 1.0),
            new TestCase(new int[]{-5, -3, -1}, new int[]{-4, -2, 0}, -2.5),
            new TestCase(new int[]{1000000}, new int[]{1000001}, 1000000.5),
            new TestCase(new int[]{-10, -5, 0, 5, 10}, new int[]{-8, -3, 2, 6, 12}, 1.0)
        };
    }

    public static boolean isCloseEnough(double a, double b) {
        return Math.abs(a - b) <= 1e-4;
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays tester = new MedianOfTwoSortedArrays();
        TestCase[] testCases = tester.getTestCases();
        boolean success = true;
        int idx = -1;
        
        for (int i = 0; i < testCases.length; i++) {
            Solution solution = new Solution();
            TestCase testCase = testCases[i];
            double output = solution.findMedianSortedArrays(testCase.nums1, testCase.nums2);
            if (!isCloseEnough(output, testCase.expected)) {
                idx = i;
                success = false;
                break;
            }
        }
        
        System.out.println(success ? "Accepted" : "Wrong Answer : " + (idx) + "/" + testCases.length);
    }
}

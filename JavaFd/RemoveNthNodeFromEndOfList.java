import java.util.*;

public class RemoveNthNodeFromEndOfList {
    public static class TestCase {
        ListNode head;
        int n;
        ListNode expected;

        public TestCase(ListNode head, int n, ListNode expected) {
            this.head = head;
            this.n = n;
            this.expected = expected;
        }
    }

    private static ListNode arrayToList(int[] arr) {
        if (arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    private static int[] listToArray(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public static TestCase[] getTestCases() {
        return new TestCase[]{
            new TestCase(arrayToList(new int[]{1,2,3,4,5}), 2, arrayToList(new int[]{1,2,3,5})),
            new TestCase(arrayToList(new int[]{1}), 1, arrayToList(new int[]{})),
            new TestCase(arrayToList(new int[]{1,2}), 1, arrayToList(new int[]{1})),
            new TestCase(arrayToList(new int[]{1,2,3,4,5,6}), 3, arrayToList(new int[]{1,2,3,5,6})),
            new TestCase(arrayToList(new int[]{10,20,30,40,50}), 5, arrayToList(new int[]{20,30,40,50})),
            new TestCase(arrayToList(new int[]{7,8,9,10,11,12,13}), 1, arrayToList(new int[]{7,8,9,10,11,12})),
            new TestCase(arrayToList(new int[]{5,10,15,20}), 2, arrayToList(new int[]{5,10,20})),
            new TestCase(arrayToList(new int[]{100,200,300}), 3, arrayToList(new int[]{200,300})),
            new TestCase(arrayToList(new int[]{9,8,7,6,5,4,3,2,1}), 4, arrayToList(new int[]{9,8,7,6,5,3,2,1})),
            new TestCase(arrayToList(new int[]{1,2,3,4,5}), 5, arrayToList(new int[]{2,3,4,5}))
        };
    }

    public static void main(String[] args) {
        TestCase[] testCases = getTestCases();
        boolean success = true;
        int idx = -1;
        for (int i = 0; i < testCases.length; i++) {
            Solution obj = new Solution();
            int[] actual = listToArray(obj.removeNthFromEnd(testCases[i].head, testCases[i].n));
            int[] expected = listToArray(testCases[i].expected);
            if (!Arrays.equals(actual, expected)) {
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
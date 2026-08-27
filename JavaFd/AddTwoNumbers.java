public class AddTwoNumbers {
    public class TestCase {
        ListNode l1;
        ListNode l2;
        ListNode expected;

        public TestCase(ListNode l1, ListNode l2, ListNode expected) {
            this.l1 = l1;
            this.l2 = l2;
            this.expected = expected;
        }
    }

    public TestCase[] getTestCases() {
        return new TestCase[] {
            // Given Example Test Cases
            new TestCase(
                createLinkedList(new int[] {2, 4, 3}),
                createLinkedList(new int[] {5, 6, 4}),
                createLinkedList(new int[] {7, 0, 8})
            ),
            new TestCase(
                createLinkedList(new int[] {0}),
                createLinkedList(new int[] {0}),
                createLinkedList(new int[] {0})
            ),
            new TestCase(
                createLinkedList(new int[] {9, 9, 9, 9, 9, 9, 9}),
                createLinkedList(new int[] {9, 9, 9, 9}),
                createLinkedList(new int[] {8, 9, 9, 9, 0, 0, 0, 1})
            ),

            // Additional Edge Cases
            new TestCase(
                createLinkedList(new int[] {1}),
                createLinkedList(new int[] {9}),
                createLinkedList(new int[] {0, 1})
            ),
            new TestCase(
                createLinkedList(new int[] {5, 6}),
                createLinkedList(new int[] {5, 4}),
                createLinkedList(new int[] {0, 1, 1})
            ),
            new TestCase(
                createLinkedList(new int[] {9, 9, 9}),
                createLinkedList(new int[] {1}),
                createLinkedList(new int[] {0, 0, 0, 1})
            ),
            new TestCase(
                createLinkedList(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 9}),
                createLinkedList(new int[] {9, 9, 9, 9, 9, 9, 9, 9, 9, 9}),
                createLinkedList(new int[] {0, 2, 3, 4, 5, 6, 7, 8, 9, 9, 1})
            )
        };
    }

    private ListNode createLinkedList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        AddTwoNumbers mainObj = new AddTwoNumbers();
        TestCase[] testCases = mainObj.getTestCases();
        boolean success = true;
        int idx = -1;
        for (int i = 0; i < testCases.length; i++) {
            Solution obj = new Solution();
            TestCase testCase = testCases[i];
            ListNode l1 = testCase.l1;
            ListNode l2 = testCase.l2;
            ListNode outputList = obj.addTwoNumbers(l1, l2);
            ListNode temp = testCase.expected;
            while (temp != null) {
                if (outputList == null || outputList.val != temp.val) {
                    idx = i;
                    success = false;
                    break;
                }
                outputList = outputList.next;
                temp = temp.next;
            }
            if (!success) break;
        }
        System.out.println(success ? "Accepted" : "Wrong Answer : "+(idx)+"/"+testCases.length);
    }
}

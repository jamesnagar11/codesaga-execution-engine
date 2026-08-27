#include <iostream>
#include "Solution.cpp"
#include "ListNode.h"
using namespace std;

class TestCase {
public:
    ListNode* l1;
    ListNode* l2;
    ListNode* expected;

    TestCase(ListNode* l1, ListNode* l2, ListNode* expected)
        : l1(l1), l2(l2), expected(expected) {}
};

ListNode* createLinkedList(vector<int> values) {
    ListNode* dummy = new ListNode(0);
    ListNode* current = dummy;
    for (int val : values) {
        current->next = new ListNode(val);
        current = current->next;
    }
    return dummy->next;
}

vector<TestCase> getTestCases() {
    return {
        // Given Example Test Cases
        TestCase(createLinkedList({2, 4, 3}), createLinkedList({5, 6, 4}), createLinkedList({7, 0, 8})),
        TestCase(createLinkedList({0}), createLinkedList({0}), createLinkedList({0})),
        TestCase(createLinkedList({9, 9, 9, 9, 9, 9, 9}), createLinkedList({9, 9, 9, 9}), createLinkedList({8, 9, 9, 9, 0, 0, 0, 1})),
        
        // Additional Edge Cases
        TestCase(createLinkedList({1}), createLinkedList({9}), createLinkedList({0, 1})),
        TestCase(createLinkedList({5, 6}), createLinkedList({5, 4}), createLinkedList({0, 1, 1})),
        TestCase(createLinkedList({9, 9, 9}), createLinkedList({1}), createLinkedList({0, 0, 0, 1})),
        TestCase(createLinkedList({1, 2, 3, 4, 5, 6, 7, 8, 9, 9}), createLinkedList({9, 9, 9, 9, 9, 9, 9, 9, 9, 9}), createLinkedList({0, 2, 3, 4, 5, 6, 7, 8, 9, 9, 1}))
    };
}

int main() {
    vector<TestCase> testCases = getTestCases();
    bool success = true;
    int idx = -1;

    for (size_t i = 0; i < testCases.size(); i++) {
        Solution obj;
        TestCase testCase = testCases[i];
        ListNode* outputList = obj.addTwoNumbers(testCase.l1, testCase.l2);
        ListNode* temp = testCase.expected;
        
        while (temp != nullptr) {
            if (outputList == nullptr || outputList->val != temp->val) {
                idx = i;
                success = false;
                break;
            }
            outputList = outputList->next;
            temp = temp->next;
        }
        if (!success) break;
    }
    
    cout << (success ? "Accepted" : "Wrong Answer : " + to_string(idx) + "/" + to_string(testCases.size())) << endl;
    
    return 0;
}

#include <iostream>
#include <vector>
#include "Solution.cpp"
#include "ListNode.h"

using namespace std;

class RemoveNthNodeFromEndOfList {
public:
    struct TestCase {
        ListNode* head;
        int n;
        ListNode* expected;
        TestCase(ListNode* h, int num, ListNode* exp) : head(h), n(num), expected(exp) {}
    };

    static ListNode* arrayToList(const vector<int>& arr) {
        if (arr.empty()) return nullptr;
        ListNode* head = new ListNode(arr[0]);
        ListNode* current = head;
        for (size_t i = 1; i < arr.size(); i++) {
            current->next = new ListNode(arr[i]);
            current = current->next;
        }
        return head;
    }

    static vector<int> listToArray(ListNode* head) {
        vector<int> arr;
        while (head) {
            arr.push_back(head->val);
            head = head->next;
        }
        return arr;
    }

    vector<TestCase> getTestCases() {
        return {
            {arrayToList({1,2,3,4,5}), 2, arrayToList({1,2,3,5})},
            {arrayToList({1}), 1, arrayToList({})},
            {arrayToList({1,2}), 1, arrayToList({1})},
            {arrayToList({1,2,3,4,5,6}), 3, arrayToList({1,2,3,5,6})},
            {arrayToList({10,20,30,40,50}), 5, arrayToList({20,30,40,50})},
            {arrayToList({7,8,9,10,11,12,13}), 1, arrayToList({7,8,9,10,11,12})},
            {arrayToList({5,10,15,20}), 2, arrayToList({5,10,20})},
            {arrayToList({100,200,300}), 3, arrayToList({200,300})},
            {arrayToList({9,8,7,6,5,4,3,2,1}), 4, arrayToList({9,8,7,6,5,3,2,1})},
            {arrayToList({1,2,3,4,5}), 5, arrayToList({2,3,4,5})}
        };
    }

    void runTests() {
        vector<TestCase> testCases = getTestCases();
        for (size_t i = 0; i < testCases.size(); i++) {
            Solution solution;
            vector<int> actual = listToArray(solution.removeNthFromEnd(testCases[i].head, testCases[i].n));
            vector<int> expected = listToArray(testCases[i].expected);

            if (actual != expected) {
                cout << "Wrong Answer : " << i << "/" << testCases.size() << endl;
                return;
            }
        }

        cout << "Accepted" << endl;
    }
};

int main() {
    RemoveNthNodeFromEndOfList tester;
    tester.runTests();
    return 0;
}

#include <iostream>
#include <vector>
#include "Solution.cpp"

using namespace std;

class PalindromeNumber {
public:
    struct TestCase {
        int x;
        bool expected;
        TestCase(int x, bool expected) : x(x), expected(expected) {}
    };

    vector<TestCase> getTestCases() {
        return {
            // Given Test Cases (Manually Added in Serial Order)
            {121, true},
            {-121, false},
            {10, false},

            // Additional Edge Cases
            {0, true},
            {1, true},
            {11, true},
            {1001, true},
            {1221, true},
            {12321, true},
            {123321, true},
            {123421, false},
            {1000021, false},
            {2147447412, true},
        };
    }

    void runTests() {
        vector<TestCase> testCases = getTestCases();
        for (size_t i = 0; i < testCases.size(); i++) {
            Solution solution;
            bool actualOutput = solution.isPalindrome(testCases[i].x);

            if (actualOutput != testCases[i].expected) {
                cout << "Wrong Answer : " << i << "/" << testCases.size() << endl;
                return;
            }
        }

        cout << "Accepted" << endl;
    }
};

int main() {
    PalindromeNumber tester;
    tester.runTests();
    return 0;
}

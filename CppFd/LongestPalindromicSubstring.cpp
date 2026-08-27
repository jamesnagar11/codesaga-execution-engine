#include <iostream>
#include <vector>
#include <string>
#include "Solution.cpp"

using namespace std;

class LongestPalindromicSubstring {
public:
    struct TestCase {
        string input;
        vector<string> expected;

        TestCase(string input, vector<string> expected) : input(move(input)), expected(move(expected)) {}
    };

    vector<TestCase> getTestCases() {
        return {
            {"babad", {"bab", "aba"}},
            {"cbbd", {"bb"}},
            {"a", {"a"}},
            {"ac", {"a", "c"}},
            {"racecar", {"racecar"}},
            {"abcdef", {"a", "b", "c", "d", "e", "f"}},
            {"bananas", {"anana"}},
            {"forgeeksskeegfor", {"geeksskeeg"}},
            {"abba", {"abba"}},
            {"xyzzyx", {"xyzzyx"}},
            {"aabbaa", {"aabbaa"}},
            {"abcda", {"a", "b", "c", "d", "a"}},
            {"abccba", {"abccba"}}
        };
    }

    void runTests() {
        vector<TestCase> testCases = getTestCases();
        for (int i = 0; i < testCases.size(); i++) {
            Solution solution;
            string actualOutput = solution.longestPalindrome(testCases[i].input);

            bool isValid = false;
            for (const string &expected : testCases[i].expected) {
                if (actualOutput == expected) {
                    isValid = true;
                    break;
                }
            }

            if (!isValid) {
                cout << "Wrong Answer : " << (i + 1) << "/" << testCases.size() << endl;
                return;
            }
        }

        cout << "Accepted" << endl;
    }
};

int main() {
    LongestPalindromicSubstring tester;
    tester.runTests();
    return 0;
}

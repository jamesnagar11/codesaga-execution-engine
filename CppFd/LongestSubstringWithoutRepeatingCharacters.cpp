#include <iostream>
#include <vector>
#include <string>
#include "Solution.cpp"

using namespace std;

class LongestSubstringWithoutRepeatingCharacters {
public:
    struct TestCase {
        string input;
        int expected;

        TestCase(string input, int expected) : input(move(input)), expected(expected) {}
    };

    vector<TestCase> getTestCases() {
        return {
            {"abcabcbb", 3},
            {"bbbbb", 1},
            {"pwwkew", 3},
            {"", 0},
            {"abcdef", 6},
            {"aab", 2},
            {"dvdf", 3},
            {"abba", 2},
            {"tmmzuxt", 5},
            {"ohomm", 3},
            {"anviaj", 5},
            {"asjrgapa", 6},
            {"aabbccddeeffgghh", 2},
            {"abcdefgabcdefg", 7},
            {"abrkaabcdefghijjxxx", 10}
        };
    }

    void runTests() {
        vector<TestCase> testCases = getTestCases();
        for (int i = 0; i < testCases.size(); i++) {
            Solution solution;
            int actualOutput = solution.lengthOfLongestSubstring(testCases[i].input);

            if (actualOutput != testCases[i].expected) {
                cout << "Wrong Answer : " << (i + 1) << "/" << testCases.size() << endl;
                return;
            }
        }

        cout << "Accepted" << endl;
    }
};

int main() {
    LongestSubstringWithoutRepeatingCharacters tester;
    tester.runTests();
    return 0;
}

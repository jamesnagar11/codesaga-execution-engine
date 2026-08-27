#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include "Solution.cpp"

using namespace std;

class LongestCommonPrefix {
public:
    struct TestCase {
        vector<string> input;
        string expected;

        TestCase(vector<string> input, string expected) : input(move(input)), expected(move(expected)) {}
    };

    vector<TestCase> getTestCases() {
        return {
            {{"flower", "flow", "flight"}, "fl"},
            {{"dog", "racecar", "car"}, ""},
            {{"interspecies", "interstellar", "interstate"}, "inters"},
            {{"apple", "ape", "april"}, "ap"},
            {{""}, ""},
            {{"a"}, "a"},
            {{"abc", "abc", "abc"}, "abc"},
            {{"prefix", "pre", "presentation"}, "pre"},
            {{"alone"}, "alone"},
            {{"same", "same", "same"}, "same"}
        };
    }

    void runTests() {
        vector<TestCase> testCases = getTestCases();
        bool success = true;
        int idx = -1;

        for (int i = 0; i < testCases.size(); i++) {
            Solution solution;
            string output = solution.longestCommonPrefix(testCases[i].input);
            if (output != testCases[i].expected) {
                success = false;
                idx = i;
                break;
            }
        }

        cout << (success ? "Accepted" : "Wrong Answer : " + to_string(idx) + "/" + to_string(testCases.size())) << endl;
    }
};

int main() {
    LongestCommonPrefix tester;
    tester.runTests();
    return 0;
}

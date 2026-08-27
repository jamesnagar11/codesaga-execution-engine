#include <iostream>
#include <vector>
#include "Solution.cpp"

using namespace std;

class RegularExpressionMatching {
public:
    struct TestCase {
        string text;
        string pattern;
        bool expected;
        TestCase(string t, string p, bool e) : text(t), pattern(p), expected(e) {}
    };

    vector<TestCase> getTestCases() {
        return {
            // Given Example Test Cases
            {"aa", "a", false},
            {"aa", "a*", true},
            {"ab", ".*", true},
            {"aab", "c*a*b", true},
            {"mississippi", "mis*is*p*.", false},
            {"aab", "c*a*b", true},
            {"aaa", "ab*ac*a", true},
            {"bbab", "b*a*", false},
            {"aabcbcbcaccbcaabc", ".*a*aa*.*b*.c*.*a*", true},
            {"abbabaaaaaaacaa", "a*.*b.a.*c*b*a*c*", true},
            {"abcaaaaaaabaabcabac", ".*ab.a.*a*a*.*b*b*", true},
            {"cbaacacaaccbaabcb", "c*b*b*.*ac*.*bc*a*", true},
            {"cbccaababcbabac", "c*aab*.*b.b.*.*a*.", false},
            {"caccccaccbabbcb", "c*c*b*a*.*c*.a*a*a*", true},
            {"ccbbcabcbbaabaccc", "c*a*.*a*a*.*c*b*b*.", true},
            {"bacacaababbbcbc", ".*a*.*a*.aa*c*b*c", false},
            {"bcccccbaccccacaa", ".*bb*c*a*b*.*b*b*c*", true},
        };
    }

    void runTests() {
        vector<TestCase> testCases = getTestCases();
        for (size_t i = 0; i < testCases.size(); i++) {
            Solution solution;
            bool actualOutput = solution.isMatch(testCases[i].text, testCases[i].pattern);

            if (actualOutput != testCases[i].expected) {
                cout << "Wrong Answer : " << i << "/" << testCases.size() << endl;
                return;
            }
        }

        cout << "Accepted" << endl;
    }
};

int main() {
    RegularExpressionMatching tester;
    tester.runTests();
    return 0;
}

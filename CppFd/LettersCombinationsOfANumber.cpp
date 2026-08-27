#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include "Solution.cpp"

using namespace std;

struct TestCase {
    string digits;
    vector<string> expected;
};

vector<TestCase> getTestCases() {
    return {
        {"23", {"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"}},
        {"", {}},
        {"2", {"a", "b", "c"}},
        {"7", {"p", "q", "r", "s"}},
        {"9", {"w", "x", "y", "z"}},
        {"79", {"pw", "px", "py", "pz", "qw", "qx", "qy", "qz", "rw", "rx", "ry", "rz", "sw", "sx", "sy", "sz"}},
        {"234", {"adg", "adh", "adi", "aeg", "aeh", "aei", "afg", "afh", "afi", "bdg", "bdh", "bdi", "beg", "beh", "bei", "bfg", "bfh", "bfi", "cdg", "cdh", "cdi", "ceg", "ceh", "cei", "cfg", "cfh", "cfi"}},
        {"567", {"jmp", "jmq", "jmr", "jms", "jnp", "jnq", "jnr", "jns", "jop", "joq", "jor", "jos", "kmp", "kmq", "kmr", "kms", "knp", "knq", "knr", "kns", "kop", "koq", "kor", "kos", "lmp", "lmq", "lmr", "lms", "lnp", "lnq", "lnr", "lns", "lop", "loq", "lor", "los"}},
        {"88", {"tt", "tu", "tv", "ut", "uu", "uv", "vt", "vu", "vv"}},
        {"92", {"wa", "wb", "wc", "xa", "xb", "xc", "ya", "yb", "yc", "za", "zb", "zc"}},
        {"2345", {"adgj", "adgk", "adgl", "adhj", "adhk", "adhl", "adij", "adik", "adil", "aegj", "aegk", "aegl", "aehj", "aehk", "aehl", "aeij", "aeik", "aeil", "afgj", "afgk", "afgl", "afhj", "afhk", "afhl", "afij", "afik", "afil", "bdgj", "bdgk", "bdgl", "bdhj", "bdhk", "bdhl", "bdij", "bdik", "bdil", "begj", "begk", "begl", "behj", "behk", "behl", "beij", "beik", "beil", "bfgj", "bfgk", "bfgl", "bfhj", "bfhk", "bfhl", "bfij", "bfik", "bfil", "cdgj", "cdgk", "cdgl", "cdhj", "cdhk", "cdhl", "cdij", "cdik", "cdil", "cegj", "cegk", "cegl", "cehj", "cehk", "cehl", "ceij", "ceik", "ceil", "cfgj", "cfgk", "cfgl", "cfhj", "cfhk", "cfhl", "cfij", "cfik", "cfil"}}
    };
}

int main() {
    vector<TestCase> testCases = getTestCases();
    bool success = true;
    int idx = -1;

    for (size_t i = 0; i < testCases.size(); i++) {
        Solution obj;
        vector<string> result = obj.letterCombinations(testCases[i].digits);
        sort(result.begin(), result.end());
        sort(testCases[i].expected.begin(), testCases[i].expected.end());
        if (result != testCases[i].expected) {
            success = false;
            idx = i;
            break;
        }
    }
    
    cout << (success ? "Accepted" : "Wrong Answer : " + to_string(idx) + "/" + to_string(testCases.size())) << endl;
    return 0;
}

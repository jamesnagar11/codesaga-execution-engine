#include <iostream>
#include <vector>
#include "Solution.cpp"

using namespace std;

class ContainerWithMostWater {
public:
    struct TestCase {
        vector<int> height;
        int expected;
    };

    static vector<TestCase> getTestCases() {
        return {
            // Given Test Cases (Manually Added in Serial Order)
            {{1, 8, 6, 2, 5, 4, 8, 3, 7}, 49},
            {{1, 1}, 1},
            
            // Additional Edge Cases
            {{4, 3, 2, 1, 4}, 16},
            {{1, 2, 1}, 2},
            {{2, 3, 10, 5, 7, 8, 9}, 36},
            {{1, 2, 4, 3}, 4},
            {{6, 9, 3, 4, 5, 8}, 32},
            {{1, 1, 1, 1, 1, 1}, 5},
            {{1, 3, 2, 5, 25, 24, 5}, 24},
            {{5, 5, 5, 5, 5, 5, 5, 5, 5}, 40},
            {{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}, 25},
        };
    }

    static int main() {
        vector<TestCase> testCases = getTestCases();
        bool success = true;
        int idx = -1;
        for (size_t i = 0; i < testCases.size(); i++) {
            Solution obj;
            int expectedValue = obj.maxArea(testCases[i].height);
            if (expectedValue != testCases[i].expected) {
                success = false;
                idx = i;
                break;
            }
        }
        if (success) {
            cout << "Accepted" << endl;
        } else {
            cout << "Wrong Answer : " << idx << "/" << testCases.size() << endl;
        }
        return 0;
    }
};

int main() {
    return ContainerWithMostWater::main();
}
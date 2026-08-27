#include <iostream>
#include <vector>
#include "Solution.cpp"
using namespace std;

class TestCase {
public:
    vector<int> nums;
    int target;
    vector<int> expected;

    TestCase(vector<int> nums, int target, vector<int> expected)
        : nums(nums), target(target), expected(expected) {}
};

vector<TestCase> getTestCases() {
    return {
        // Given Test Cases (Manually Added in Serial Order)
        TestCase({2, 7, 11, 15}, 9, {0, 1}),
        TestCase({3, 2, 4}, 6, {1, 2}),
        TestCase({3, 3}, 6, {0, 1}),

        // Additional Edge Cases
        TestCase({1, 2, 3, 4}, 6, {1, 3}),
        TestCase({-3, 4, 3, 90}, 0, {0, 2}),
        TestCase({3, 3}, 6, {0, 1}),
        TestCase({1,2,3}, 5, {1,2}),
        TestCase({-1,-2,7}, 5, {1,2}),
        TestCase({0, 4, 3, 0}, 0, {0, 3}),
        TestCase({1, 5, -10, 554}, 6, {0, 1})
    };
}

int main() {
    vector<TestCase> testCases = getTestCases();
    bool success = true;
    int idx = -1;
    
    for (size_t i = 0; i < testCases.size(); i++) {
        Solution obj;
        vector<int> result = obj.twoSum(testCases[i].nums, testCases[i].target);
        
        if (result.size() != testCases[i].expected.size()) {
            success = false;
            idx = i;
            break;
        }
        for (size_t j = 0; j < result.size(); j++) {
            if (result[j] != testCases[i].expected[j]) {
                success = false;
                idx = i;
                break;
            }
        }
    }
    
    if (success) {
        cout << "Accepted" << endl;
    } else {
        cout << "Wrong Answer : " << idx << "/" << testCases.size() << endl;
    }
    
    return 0;
}
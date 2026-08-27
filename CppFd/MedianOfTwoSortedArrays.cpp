#include <iostream>
#include <vector>
#include <cmath>
#include "Solution.cpp"

using namespace std;

class MedianOfTwoSortedArrays {
public:
    struct TestCase {
        vector<int> nums1;
        vector<int> nums2;
        double expected;

        TestCase(vector<int> nums1, vector<int> nums2, double expected) 
            : nums1(move(nums1)), nums2(move(nums2)), expected(expected) {}
    };

    vector<TestCase> getTestCases() {
        return {
            {{1, 3}, {2}, 2.0},
            {{1, 2}, {3, 4}, 2.5},
            {{0, 0}, {0, 0}, 0.0},
            {{}, {1}, 1.0},
            {{2}, {}, 2.0},
            {{1, 3, 5}, {2, 4, 6}, 3.5},
            {{1, 1, 1}, {1, 1, 1, 1}, 1.0},
            {{-5, -3, -1}, {-4, -2, 0}, -2.5},
            {{1000000}, {1000001}, 1000000.5},
            {{-10, -5, 0, 5, 10}, {-8, -3, 2, 6, 12}, 1.0}
        };
    }

    static bool isCloseEnough(double a, double b) {
        return fabs(a - b) <= 1e-4;
    }

    void runTests() {
        vector<TestCase> testCases = getTestCases();
        for (int i = 0; i < testCases.size(); i++) {
            Solution solution;
            double actualOutput = solution.findMedianSortedArrays(testCases[i].nums1, testCases[i].nums2);

            if (!isCloseEnough(actualOutput, testCases[i].expected)) {
                cout << "Wrong Answer : " << (i + 1) << "/" << testCases.size() << endl;
                return;
            }
        }

        cout << "Accepted" << endl;
    }
};

int main() {
    MedianOfTwoSortedArrays tester;
    tester.runTests();
    return 0;
}

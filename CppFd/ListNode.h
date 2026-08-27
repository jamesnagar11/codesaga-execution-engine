#ifndef LISTNODE_H
#define LISTNODE_H

class ListNode {
public:
    int val;
    ListNode* next;

    ListNode() : val(0), next(nullptr) {}

    ListNode(int val) : val(val), next(nullptr) {}
};

#endif
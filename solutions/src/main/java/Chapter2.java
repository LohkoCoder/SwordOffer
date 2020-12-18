import com.sun.xml.internal.bind.AnyTypeAdapter;

import java.util.HashMap;
import java.util.Stack;

public class Chapter2 {

    /**
     * To 实现一个函数，把字符串中的每个空格替换成"%20"，
     * 例如"We are happy."，则输出"We%20are%20happy."
     *
     */
    public void replaceBlank(StringBuffer str) {

        int p1 = str.length() -1;
        int spaceNum = 0;
        for (int i=0; i < p1+1; i++) {
            if (str.charAt(i) == ' ') {
                spaceNum++;
                str.append("  ");
            }
        }
        int p2 = str.length()-1;
        for (int i=p1; i>0; i--) {
            if (str.charAt(i) != ' ') {
                str.setCharAt(p2, str.charAt(i));
                p2--;
            } else {
                str.setCharAt(p2, '0');
                p2--;
                str.setCharAt(p2, '2');
                p2--;
                str.setCharAt(p2, '%');
                p2--;
            }
        }
        System.out.println(str);
    }

    /**
     * To 从尾到头打印一个链表
     * 方法 1
     */
    public void printReversedList(ListNode head) {

        Stack<ListNode> listStack = new Stack();

        while (head != null) {
            listStack.push(head);
            head = head.next;
        }

        while (!listStack.isEmpty()) {
            System.out.println(listStack.pop().value);
        }
    }

    public ListNode constructList() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        return listNode1;
    }

    /**
     * To 从尾到头打印一个链表
     * 方法 2
     */
    public void printReversedList2(ListNode head) {
        if (head.next != null) {
            printReversedList(head.next);
        }
        System.out.println(head.value);
    }

    /**
     * 重建二叉树
     */
    int[] preorder;
    HashMap<Integer, Integer> dic = new HashMap();
    public TreeNode constructBTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for(int i = 0; i < inorder.length; i++)
            dic.put(inorder[i], i);
        return recur(0, 0, inorder.length - 1);
    }

    TreeNode recur(int root, int left, int right) {
        if(left > right) return null;                          // 递归终止
        TreeNode node = new TreeNode(preorder[root]);          // 建立根节点
        int i = dic.get(preorder[root]);                       // 划分根节点、左子树、右子树
        // left指的是左子树的最左，right指的是左子树的最右
        node.left = recur(root + 1, left, i - 1);              // 开启左子树递归
        // left指的是右子树的最左，right指的是右子树的最右
        node.right = recur(root + i - left + 1, i + 1, right); // 开启右子树递归
        return node;                                           // 回溯返回根节点
    }


    public static void main(String args[]) {
        Chapter2 chapter2 = new Chapter2();

//        chapter2.replaceBlank(new StringBuffer("We are happy."));
//        chapter2.printReversedList(chapter2.constructList());
//        chapter2.printReversedList2(chapter2.constructList());
        System.out.println(chapter2.constructBTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7}).val);
    }


    static class ListNode {
        int value;
        ListNode next = null;
        ListNode(int value) {
            this.value=value;
        }
    }

// Definition for a binary tree node.
   public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }

}


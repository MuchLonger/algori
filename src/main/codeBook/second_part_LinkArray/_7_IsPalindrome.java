package codeBook.second_part_LinkArray;

import java.util.Stack;

/**
 * @description: 判断一个链表是否是回文结构
 * 1）解法1，使用栈
 * 2）解法2：使用半个栈
 * 3）解法3：不使用多余空间，翻转后边部分链表
 * @Time: 2019/12/5 17:12
 */
public class _7_IsPalindrome {
    /**
     * 使用一个栈
     */
    public static boolean isPalindrome1(Node head) {
        if (head == null)
            throw new RuntimeException("head不能为空");
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (stack.pop().value != head.value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 使用半个栈，使用快慢节点找到中点，然后从中点开始加入栈中
     */
    public static boolean isPalindrome2(Node head) {
        if (head == null)
            throw new RuntimeException("head不能为空");
        Node fast = head;
        Node slow = head.next;  // 慢节点先走一步，这样得到的就是中点后一个值
        while (fast.next != null && fast.next.next != null) {  // 快节点判空
            slow = slow.next;
            fast = fast.next.next;
        }
        Stack<Node> stack = new Stack<>();
        while (slow != null) {  // 慢节点（中点+1）之后的所有值都加到栈内
            stack.push(slow);
            slow = slow.next;
        }
        while (!stack.isEmpty()) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 反转中点之后的链表，判断完之后再复原
     */
    public static boolean isPalindrome3(Node head){
        if (head == null)
            throw new RuntimeException("head不能为空");
        Node fast = head;
        Node slow = head;  // 这次不需要先走一步了，因为当前位置的正巧是中点，还需要处理中点和后面的关系
        while (fast.next != null && fast.next.next != null) {  // 快节点判空
            slow = slow.next;
            fast = fast.next.next;
        }

        fast=slow.next;  //废物利用 fast节点 来记录next之后的值 反转就靠它
        slow.next=null;  // 将中间节点的next置为null
        Node next=null;  // 用来转移节点
        while (fast != null) {
            next=fast.next;
            fast.next=slow;   // 使用slow来表示pre，因为依旧需要slow这条链，这样就能保证一直都是一条链（如果使用pre就会分割成两条链）省了两条链的拼接过程
            slow=fast;
            fast=next;
        }   //反转完成，终点是pre

        Node end=slow;  // 需要保存最后一个节点，用来后面反转回来（反转的时候这个就类似于头结点了）
        fast=head;  //反转完之后fast节点又没用了， 又可以用来保存head节点

        boolean res=true;
        while (fast != null && slow != null) {  //前后比较回文
            if (fast.value != slow.value) {
                res=false;
                break;
            }
            fast=fast.next;
            slow=slow.next;
        }

        // 将链表再反转回去
        fast=end.next;  // 取得反转链表的头结点
        end.next=null;  // 尾节点需要置空（因为它本来next指针就是空的）
        while (fast != null) {
            slow=fast.next; //取上面废弃掉的节点来用
            fast.next=end;  //注意，一定要使用end，因为end才是“上一个节点”，不然会漏掉end这个值
            end=fast;
            fast=slow;
        }

        return res;
    }

    public static void main(String[] args){
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        n1.next.next.next = new Node(2);
        n1.next.next.next.next = new Node(1);
        Node n2 = new Node(1);
        n2.next = new Node(2);
        n2.next.next = new Node(3);

        System.out.println(isPalindrome3(n1));
        System.out.println(isPalindrome3(n2));
        System.out.println("-----------------------------");
        System.out.println(isPalindrome1(n1));
        System.out.println(isPalindrome1(n2));
        System.out.println("-----------------------------");
        System.out.println(isPalindrome2(n1));
        System.out.println(isPalindrome2(n2));

    }
}

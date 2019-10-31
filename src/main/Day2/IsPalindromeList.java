package Day2;

import Util.ArrUtil;
import Util.Node;
import teacherCode.class_03.Code_11_IsPalindromeList;

/**
 * @description: 判断是否指定链表是否是回文，用三种方法解决：
 *
 * 讲解之所以存入栈，因为存入栈后就是逆序输出，两者一比较是否相等就是回文
 * 方法一：时间复杂度O（N），空间复杂度O（N）：一边next一边把node放入栈中，全部存入栈后，再从head开始与栈一一比对全都相等就是回文
 *
 * 方法二：时间复杂度O（N），空间复杂度O（N/2）：
 *   引入一个快指针（每次走两步，即.next.next），引入一个慢指针（每次走一步，即.next），当快指针走完（即走到底了）慢指针正好走到一半（注意偶数时，慢指针需要走在两个值的前面（偶数时平均值有两个））
 *   于是将慢指针后的值存入栈中，一直到底。之后再从head开始遍历，并与栈中元素一一比对，栈中输出完了且都相等就输出true。（回文前后相等）
 *
 * 方法三：时间复杂度O（N），空间复杂度O（1）：
 *   沿用上面快慢指针的概念，（还是要注意，当N为偶数时慢指针要在中间两个值的前面）。当慢指针到达一半的时候，将慢指针指向null；慢指针后面的Node全部翻转
 *   因为后面全都翻转，所以只要后面的最后一个节点和head节点同时开始遍历，不为null的时候终止，都相等就为回文。最后需要把结果反转回去
 *  1 -> 2 -> 2 -> 1 翻转后 1 -> 2 <- 2 <- 1 从head和结尾分别开始比较。 再翻转回去 1 -> 2 -> 2 -> 1
 *
 * @Time: 2019/10/28 22:08
 */
public class IsPalindromeList {

    //第一种方法
    public static boolean isPalindrome1(Node head){
        java.util.Stack<Node> putPalCode=new java.util.Stack<>();
        Node cur=head;  //注意需要新建一个Node上，不然head在执行下面while的时候就移动到最后了。
        while(cur!=null){     //注意是cur!=null 而不是cur.next不等于null，因为while内最后一步是.next
            putPalCode.push(cur);  //存入cur
            cur=cur.next;
        }
        while(!putPalCode.empty()){
            if(putPalCode.pop().value!=head.value){
                return false;
            }
            head=head.next;
        }
        return true;
    }

    public static boolean isPalindrome2(Node head){
        if(head==null||head.next==null)
            return true;
        java.util.Stack<Node> putPalCode=new java.util.Stack<>();  //存入慢节点开始的节点

        Node SlowerPoint=head; //慢节点以head开始。
        Node FasterPoint=head; //只有当快节点是以 head 开始时 慢节点才会在遇到偶数的时候在前一个
        while(FasterPoint.next!=null&&FasterPoint.next.next!=null){
            SlowerPoint=SlowerPoint.next;
            FasterPoint=FasterPoint.next.next;
        }
        //结束上面while后 慢节点到达中点
        while(FasterPoint!=null){
            putPalCode.push(FasterPoint);
            FasterPoint=FasterPoint.next;
        }
        while(!putPalCode.empty()){  //因为存入栈的指针只有一半，所以只需要遍历栈即可实现尾部到头部的比较
            if(putPalCode.pop().value!=head.value){
                return false;
            }
            head=head.next;
        }
        return true;
    }

    //第三种方法：主要难理解的是链表的反转
    public static boolean isPalindrome3(Node head){
        if(head==null||head.next==null){
            return true;
        }
        Node SlowerPoint=head; //只有当快节点是 head 开始时 慢节点才会在遇到偶数的时候在前一个
        Node FasterPoint=head; //慢节点以head开始。
        while(FasterPoint.next!=null&&FasterPoint.next.next!=null){  //一样的移动到中间位置
            SlowerPoint=SlowerPoint.next;
            FasterPoint=FasterPoint.next.next;
        }

        //使用最后一个节点来保存慢节点的下一个节点（因为慢节点要置null所以需要保存）
        FasterPoint=SlowerPoint.next;  //下一个节点，因为慢节点没动过
        SlowerPoint.next=null;
        Node temp=null;
//        从慢节点的下一个节点（中间）开始往前反转，详情参考链表反转那个Class
        while(FasterPoint!=null){
            temp=FasterPoint.next;  //保存下一个，用以向后延伸
            FasterPoint.next=SlowerPoint;   //将下一个节点指向上一个节点
            SlowerPoint=FasterPoint; //前一个更前 （向前延伸）
            FasterPoint=temp;  //后一个更后 （向后延伸）
        }


        //此时的慢节点Slower指向的是最后一个节点
        temp=SlowerPoint; //是使用temp将倒数第一个节点保存用以节点复原
        FasterPoint=head;  //此时将快节点指向头结点 ，这样一个从最后一个节点遍历，一个从开头遍历。当其中一个为null的时候终止
        boolean isPal=true;
        while(SlowerPoint!=null&&FasterPoint!=null){
            if(SlowerPoint.value!=FasterPoint.value){
                isPal=false;
                break;
            }
            SlowerPoint=SlowerPoint.next;
            FasterPoint=FasterPoint.next;  //同时向后移动
        }

//        下面是将半部分的链表再反转回来
        SlowerPoint=temp.next; //获得最后一个节点的前一个节点，之所以是前一个，因为上面将后半部分链表反转了
        temp.next=null;  //这个拿来当头结点
        while(SlowerPoint!=null){
            FasterPoint=SlowerPoint.next; //Faster已经没用，所以直接用来记录，并且记录的是最后一个节点的前一个节点的前一个节点，因为最后一个节点根本没动过！
            SlowerPoint.next=temp;
            temp=SlowerPoint;
            SlowerPoint=FasterPoint;
        }
        return isPal;
    }


    public static void main(String[] args){
        Node head = new Node(1);
        head.next=new Node(2);
        head.next.next=new Node(3);
        head.next.next.next=new Node(2);
        head.next.next.next.next=new Node(1);

        ArrUtil.printLinkedList(head);
        System.out.println(isPalindrome3(head));
        ArrUtil.printLinkedList(head);
    }
}

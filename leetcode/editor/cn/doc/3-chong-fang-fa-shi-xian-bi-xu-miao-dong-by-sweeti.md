🙋打卡打卡～


❤️ 大佬们随手关注波公众号【[甜姨的奇妙冒险](https://pic.leetcode-cn.com/304599b006dd41bcf2042715f31a2dc4fbdc4cf9748a11a81d8978ea1e839956-wxgzh.jpeg)】和知乎专栏【[甜姨的力扣题解](https://zhuanlan.zhihu.com/c_1224355183452614656)】呀～
❤️ 关注公众号扫码加入 「甜姨的技术交流群」喔！

---

## 题目要求：
设计一个栈，使其支持 `push`、`pop`、`top` 操作，并能在常数时间复杂度内完成 `getMin` 检索栈内最小元素。

---

## 题目分析：

对于前三种操作 `push`、`pop`、`top` 现有的栈就可以支持常数时间复杂度内完成；
对于 `getMin` 操作，现有的栈必须从栈顶到栈底线性扫描一遍，无法在常数时间复杂度内完成。
因此我们首先的思路就是能否在现有栈的基础上进行拓展（见方法一、方法二）。
其次我们可以考虑从头来简单地自定义一个栈（见方法三）。

---

## 方法一：使用辅助栈
- 首先定义一个 「数据栈」来支持常规的 `push`、`pop`、`top` 操作；
- 再定义一个「辅助栈」，其栈顶一直保持为当前的最小值，以支持常数时间复杂度的 `getMin` 操作。

``` Java
class MinStack {
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;
    
    public MinStack() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int x) {
        // 先将 x 压入数据栈
        dataStack.push(x);
        // 如果 x 是当前的最小值，则也需要将 x 压入辅助栈
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }
    
    public void pop() {
        // 先删除数据栈的栈顶元素 x
        int x = dataStack.pop();
        // 若 x 是当前的最小值，则也需要删除辅助栈的栈顶元素
        if (x == minStack.peek()) {
            minStack.pop();
        }
    }
    
    public int top() {
        return dataStack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}
```

---

## 方法二：使用 Stack<Node>
方法一是使用了辅助栈，那我们在不使用辅助栈的情况下如何在常数时间复杂度内获取到栈内的最小值呢？
那么我们可以「在栈元素中除了保存当前的元素值之外，额外保存当前的最小值」。

这里我使用的是自定义的 Node，其他语言中如果有现成的 Pair 的话直接用 Pair 也行， 或者这里用数组也行。

``` Java
class MinStack {

    private Stack<Node> stack;

    public MinStack() {
        stack = new Stack<>();
    }
    
    public void push(int x) {
        // 将元素值 x 和 当前最小值 同时入栈。
        if (stack.isEmpty()) {
            stack.push(new Node(x, x));
        } else {
            stack.push(new Node(x, Math.min(x, stack.peek().min)));
        }
    }
    
    public void pop() {
        stack.pop();
    }
    
    public int top() {
        // 返回栈顶元素中的元素值。
        return stack.peek().val;
    }
    
    public int getMin() {
        // 返回栈顶元素的最小值
        return stack.peek().min;
    }

    private static class Node {
        int val;
        int min;

        public Node(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }
}
```

---

## 方法三：自定义 Stack
方法一和方法二都借助了现有的栈，方法三我们尝试从头自定义一个栈。
这里我们简单地用单链表的形式来定义栈，链表节点的定义如下：
``` Java
private static class Node {
    // 元素值
    int val;
    // 最小值
    int min;
    // 后继节点
    Node next;
        
    public Node(int val, int min) {
        this(val, min, null);
    }
        
    public Node(int val, int min, Node next) {
        this.val = val;
        this.min = min;
        this.next = next;
    }
}
```

代码实现：
``` Java
class MinStack {

    private Node head;

    public MinStack() {}
    
    public void push(int x) {
        // 新建节点插入链表头部，作为新的头节点，存储当前的元素值和最小值，并且指向之前的头节点。
        if (head == null) {
            head = new Node(x, x);
        } else {
            head = new Node(x, Math.min(x, head.min), head);
        }
    }
    
    public void pop() {
        // 删除链表头节点
        head = head.next;
    }
    
    public int top() {
        // 返回头节点中存储的元素值
        return head.val;
    }
    
    public int getMin() {
        // 返回头节点中存储的最小值
        return head.min;
    }
}
```

---


❤️ 大佬们随手关注波公众号【[甜姨的奇妙冒险](https://pic.leetcode-cn.com/304599b006dd41bcf2042715f31a2dc4fbdc4cf9748a11a81d8978ea1e839956-wxgzh.jpeg)】和知乎专栏【[甜姨的力扣题解](https://zhuanlan.zhihu.com/c_1224355183452614656)】呀～
❤️ 关注公众号扫码加入 「甜姨的技术交流群」喔！

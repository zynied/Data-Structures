思路：**画图帮助思考，关键还是思考清楚细节。**

 [0232.gif](https://pic.leetcode-cn.com/f4452eaf376161b1421d07a410301e020ff5673b75b6f0ea95c69b1ab7c211c6-0232.gif)


1、使用两个栈，一个栈（`stackPush`）用于元素进栈，一个栈（`stackPop`）用于元素出栈；

2、`pop()` 或者 `peek()` 的时候：

（1）如果 `stackPop` 里面有元素，直接从 `stackPop` 里弹出或者 `peek` 元素；

（2）如果 `stackPop` 里面没有元素，一次性将 `stackPush` 里面的所有元素倒入 `stackPop`。


为此，可以写一个 `shift` 辅助方法，一次性将 `stackPush` 里的元素倒入 `stackPop`。

**注意**：

> **一定要保证 `stackPop` 为空的时候，才能把元素从 `stackPush` 里拿到 `stackPop` 中。**

**参考代码 1**：

说明：参考代码 1 是自己在复习的时候写的，和参考代码 2 的逻辑一样，看其中任意一个都是可以的。

```Java []
import java.util.ArrayDeque;
import java.util.Deque;

public class MyQueue {

    private Deque<Integer> pushStack;
    private Deque<Integer> popStack;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        pushStack = new ArrayDeque<>();
        popStack = new ArrayDeque<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        // 在任何时候都可以向 pushStack 推入元素
        pushStack.addLast(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        // 从 popStack 取出元素
        if (!popStack.isEmpty()) {
            return popStack.removeLast();
        }
        // 走到这里是因为 popStack 为空，此时需要将 pushStack 里的所有元素依次放入 popStack
        while (!pushStack.isEmpty()) {
            popStack.addLast(pushStack.removeLast());
        }
        return popStack.removeLast();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        // 从 popStack 取出元素
        if (!popStack.isEmpty()) {
            return popStack.peekLast();
        }
        // 走到这里是因为 popStack 为空，此时需要将 pushStack 里的所有元素依次放入 popStack
        while (!pushStack.isEmpty()) {
            popStack.addLast(pushStack.removeLast());
        }
        return popStack.peekLast();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        // 两个栈都为空，才说明队列为空
        return pushStack.isEmpty() && popStack.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
```

**参考代码 2**：

```Java []
import java.util.Stack;

public class MyQueue {

    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        stackPush.push(x);
    }

    /**
     * 辅助方法：一次性将 stackPush 里的所有元素倒入 stackPop
     * 注意：1、该操作只在 stackPop 里为空的时候才操作，否则会破坏出队入队的顺序
     * 2、在 peek 和 pop 操作之前调用该方法
     */
    private void shift() {
        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        shift();
        if (!stackPop.isEmpty()) {
            return stackPop.pop();
        }
        throw new RuntimeException("队列里没有元素");
    }

    /**
     * Get the front element.
     */
    public int peek() {
        shift();
        if (!stackPop.isEmpty()) {
            return stackPop.peek();
        }
        throw new RuntimeException("队列里没有元素");
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return stackPush.isEmpty() && stackPop.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
```


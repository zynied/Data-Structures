之前理解循环队列总是绕来绕去，还需要取模，现在多用一个`size`变量就可以实现 头指针`front`和尾指针`rear`的解耦。
- `rear`变量指的位置是新来元素待插入的位置，即队列尾元素后面那个位置；`front`变量指向的是队列头部那个元素。
- 定义一个`size`变量，记录当前队列中元素个数，只要`size != arr.length`，就可以添加元素，否则，就是队列满了。

这样定义完之后，能不能添加或者删除元素只需要看`size`就可以了，不再通过`front`和`rear`的关系来判断。
具体来说：如果判断完可以添加元素，那么就要移动`rear`，如果`rear`到底了，直接让`rear`指向`0`位置；否则直接加一。删除元素时，`front`同理。最主要的是要维护好`size`这个变量。
 [CircularQueue.gif](https://pic.leetcode-cn.com/c439d282d60c40642f7fed325597969acfac091ff95e483131596ddfa90c664d-CircularQueue.gif)

更详细点的解释，我写了篇博客，可以参考下：[用 size 变量解耦 front 和 last 实现循环队列](https://sningning.gitee.io/posts/15ce7a39/#more)

上代码。

```java
class Solution {

    private int[] arr;
    private int size;
    private int front;
    private int rear;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        if (k < 0) {
            throw new IllegalArgumentException("Queue size is less than 0!");
        }
        this.arr = new int[k];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (size == arr.length) {
            return false;
        }
        arr[rear] = value;
        size ++;
        rear = rear == arr.length - 1 ? 0 : rear + 1;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (size == 0) {
            return false;
        }
        front = front == arr.length - 1 ? 0 : front + 1;
        size--;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (size == 0) {
            return -1;
        }
        return arr[front];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (size == 0) {
            return -1;
        }
        return rear == 0 ? arr[arr.length - 1] : arr[rear - 1];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return size == arr.length;
    }

}
```





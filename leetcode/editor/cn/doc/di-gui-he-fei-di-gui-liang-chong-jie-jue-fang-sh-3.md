
### 1，非递归方式解决
这题算是比较简单的了，一种比较简单的实现方式就是使用两个指针，一个在最左边一个在最右边，然后两两交换

```
    public void reverseString(char[] s) {
        if (s == null || s.length == 0)
            return;
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            swap(s, left, right);
            left++;
            right--;
        }
    }

    private void swap(char[] array, int i, int j) {
        //第1种交换方式
       char temp = array[i];
       array[i] = array[j];
       array[j] = temp;

        //第2种交换方式
    //    array[i] = (char) (array[i] + array[j]);
    //    array[j] = (char) (array[i] - array[j]);
    //    array[i] = (char) (array[i] - array[j]);

        //第3种交换方式
    //    array[i] = (char) (array[i] - array[j]);
    //    array[j] = (char) (array[i] + array[j]);
    //    array[i] = (char) (array[j] - array[i]);

        //第4种交换方式
        // array[i] ^= array[j];
        // array[j] ^= array[i];
        // array[i] ^= array[j];
    }
```
这里的swap上面的4种实现方式都是可以的，尤其是第3种实现方式，大家可能会担心越界的问题，其实这个是没有影响的，具体也可以看下[357，交换两个数字的值](https://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247486352&idx=1&sn=2c2a196b94342e98c8339c5074e9ea57&chksm=fb4198b0cc3611a6edfdd46da64f2353f160810ed319cd2fc76f0e78f0d78e89e93612c5bb89&token=2090267209&lang=zh_CN#rd)

看一下运行结果
![image.png](https://pic.leetcode-cn.com/1602158570-uIwFYR-image.png)


<br>

### 2，递归方式解决
```
    public void reverseString(char[] s) {
        if (s == null || s.length == 0)
            return;
        reverseStringHelper(s, 0, s.length - 1);
    }

    public void reverseStringHelper(char[] s, int left, int right) {
        if (left >= right)
            return;
        swap(s, left, right);
        reverseStringHelper(s, ++left, --right);
    }

    private void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
```

<br>

### 3，从中间往两边交换
前面两种方式都是从两边往中间开始两两交换，这里也可以从中间往两边两两交换

```
    public void reverseString(char[] s) {
        if (s == null || s.length == 0)
            return;
        int left = s.length / 2 - 1;
        int right = 0;
        if (s.length % 2 == 1) {
            right = left + 2;
        } else {
            right = left + 1;
        }

        while (left >= 0) {
            swap(s, left, right);
            left--;
            right++;
        }
    }

    private void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
```


<br>

**如果觉得有用就给个赞吧，你的赞是给我最大的鼓励，也是我写作的最大动力**

![image.png](https://pic.leetcode-cn.com/d56a80459005b444404d2ad6fbaabdabecd2b9ed3cb2cf432e570c315ae2fcf7-image.png)
##### 查看更多答案，可扫码关注我微信公众号“**[数据结构和算法](https://img-blog.csdnimg.cn/20200807155236311.png)**”
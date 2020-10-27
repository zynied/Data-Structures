
> 本文已收录至我的 Github《算法图解》系列：[https://github.com/vipstone/algorithm](https://github.com/vipstone/algorithm)



这道题是 bilibili 今年的笔试真题，也是一道关于栈的经典面试题。


经过前面文章的学习，我想很多朋友已经看出来了，**我接下来要写的是一个关于「算法图解」的系列文章**，中间可能会穿插少量的其他类型的文章，但「算法和数据结构」会是我今年文章输出的重点内容。


我在写这个算法系列的时候会注意两个问题：

- **保证算法的解题思路大家都能看懂，因此我会以图片的形式进行思路讲解，这样更直观、更易于理解**；
- **在介绍完一个知识点之后，会进行大量的练习，以巩固所学的内容**，比如当我讲完「栈」结构之后，我会围绕着「栈」做一系列的经典面试题练习。



> 学习算法最关键的是掌握解题的思路，只要思路对了，编写代码只是时间的问题。



我们先来回顾一下往期关于「栈」的内容：

- [《动图演示：手撸堆栈的两种实现方法！》](https://mp.weixin.qq.com/s/HkDnPxuOAT3GmbMgMmIAgg)
- [《JDK 竟然是这样实现栈的？》](https://mp.weixin.qq.com/s/ztEiJGa9MCeGBMpYzSfkUg)
- [《链表反转的两种实现方法，后一种击败了100%的用户！》](https://mp.weixin.qq.com/s/-t3zGkByxvNUiEIVxPnydA)
- [《算法图解：如何找出栈中的最小值？》](https://mp.weixin.qq.com/s/afz5sOMM0UITrkgakN7jGA)



那么接下来，我们就进入今天的正式内容...


### 题目
给定一个只包括 `'('`， `')'`， `'{'`， `'}'`， `'['`， `']'` 的字符串，判断字符串是否有效。


有效字符串需满足：

- 左括号必须用相同类型的右括号闭合。
- 左括号必须以正确的顺序闭合。
- 注意空字符串可被认为是有效字符串。



**示例 1:**
> 输入: "()"
> 输出: true



**示例 2:**
> 输入: "()[]{}"
> 输出: true



**示例 3:**
> 输入: "(]"
> 输出: false



**示例 4:**
> 输入: "([)]"
> 输出: false



**示例 5:**
> 输入: "{[]}"
> 输出: true



> LeetCode 地址：[https://leetcode-cn.com/problems/valid-parentheses](https://leetcode-cn.com/problems/valid-parentheses)



### 解题思路
这道题考察的是就是验证括号的对称性，比如“([{}])”这种字符串就是正确的，应该返回 true，而“([{})]”这种字符串就是错误的，应该返回 false。


从上面的题目可以看出，括号总共分为三类：小括号、中括号和大括号，那么**我们可以利用栈先进后出的特性，将所有左边的括号（“(”、“[”、“{”）先入栈，然后再碰到右括号时，让它与栈顶的元素进行匹配，比如当遇到“)”时，如果栈顶是“(”，则说明匹配成功，栈顶元素出栈再继续字符串循环的流程，如果匹配错误就直接返回 false。**

假设我们要匹配字符串“(([]))”是否合法？那么执行流程就是这样的。


首先遇到左边括号，先入栈：


![image.png](https://pic.leetcode-cn.com/1603281479-ZkKMRA-file_1603281479659)
接下来又是左边括号，继续入栈：
![image.png](https://pic.leetcode-cn.com/1603281479-ALFHaP-file_1603281479671)
然后又是左边括号，继续入栈：
![image.png](https://pic.leetcode-cn.com/1603281479-OrLFSi-file_1603281479672)
接下来是右边括号，与栈顶元素匹配，“[]”为一对合法的括号，匹配成功栈顶元素出栈：
![image.png](https://pic.leetcode-cn.com/1603281479-PmhpRP-file_1603281479670)
接下来又是右边括号，与栈顶元素匹配，“()”为一对合法的括号，匹配成功栈顶元素出栈：
![image.png](https://pic.leetcode-cn.com/1603281479-iMpuKE-file_1603281479674)
接下来又是右边括号，与栈顶元素匹配，“()”为一对合法的括号，匹配成功栈顶元素出栈：
![image.png](https://pic.leetcode-cn.com/1603281479-aODPgv-file_1603281479676)
当字符串循环结束并且栈为空栈时，则证明此字符串的括号匹配合法，最终的效果如下图所示：
![image.png](https://pic.leetcode-cn.com/1603281479-OklTRP-file_1603281479677)


那么接下来我们就用代码来实现一下整个过程...


### 实现代码一
```java
public boolean isValid(String s) {
    int slen = s.length(); // 括号的长度
    if (slen % 2 == 1) { // 括号不是成对出现直接返回 false
        return false;
    }
    // 把所有对比的括号存入 map，对比时用
    Map<Character, Character> map = new HashMap<>();
    map.put(')', '(');
    map.put('}', '{');
    map.put(']', '[');
    // 定义栈，用于存取括号（辅助比较）
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < slen; i++) { // 循环所有字符
        char c = s.charAt(i);
        if (map.containsKey(c)) { // 为右边的括号，如 ')'、'}' 等
            if (stack.isEmpty() || stack.peek() != map.get(c)) { // 栈为空或括号不匹配
                return false;
            }
            stack.pop(); // 是一对括号，执行出栈（消除左右括号）
        } else { // 左边括号，直接入栈
            stack.push(c);
        }
    }
    return stack.isEmpty();
}
```
我们在 LeetCode 中提交一下代码，执行结果如下：
![image.png](https://pic.leetcode-cn.com/1603281479-FOwPLO-file_1603281479679)


#### 代码解析
以上代码的 `map` 集合是用于定义括号的匹配规则，比如“)”对应的匹配值是“(”，“]”的匹配值是“[”等，然后我们再去循环待验证的字符串，遇到左括号直接入栈，遇到右括号让它与栈顶元素匹配，等到整个字符串循环结束，如果栈为空则说明字符串的括号合法。


#### 复杂度分析
时间复杂度：O(n)，遍历了一遍整个字符串。
空间复杂度：O(n)。


### 实现代码二
除了使用栈之外，我们还可以使用借助 Java 中的 `replace` 方法来实现，我们可以循环的消除字符串中的括号，比如将“()”或“[]”或“{}”循环得替换为空，最后在执行完成之后如果字符串为空，则说明字符串中的括号是合法的，具体实现代码如下：
```java
public boolean isValid(String s) {
        int len;
        do {
            len = s.length();
            // 消除成双成对的符号
            s = s.replace("()", "").replace("[]", "").
                    replace("{}", "");
        } while (len != s.length()); // 不能再进行替换了，replace 方法没有替换任何字符
        return s.length() == 0;
    }
```


我们在 LeetCode 中提交一下代码，执行结果如下：
![image.png](https://pic.leetcode-cn.com/1603281479-EYMKPZ-file_1603281479680)


从运行结果来看，二者的执行效率相差还是很明显的：
![image.png](https://pic.leetcode-cn.com/1603281479-QQklxA-file_1603281479682)


### 总结
本文我们讲了一道 bilibili 的笔试真题，同时它也是栈的经典面试题，我们可以借助栈的特性（先进后出）将所有的左括号入栈，当遇到右括号时让它与栈顶元素进行匹配，当字符串循环结束栈为空时，则说明此字符串的括号是合法的。当然我们在实际面试中，也可以使用 Java 的 `replace` 方法作为一个保底的实现方案，因为 `replace` 方法的实现相对更简单一些，只是性能不那么好。

>

> 本文已收录至我的 Github《算法图解》系列：[https://github.com/vipstone/algorithm](https://github.com/vipstone/algorithm)
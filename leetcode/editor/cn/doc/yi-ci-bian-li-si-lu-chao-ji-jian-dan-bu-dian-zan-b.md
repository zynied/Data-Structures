**解题思路：**
1. 特判：过滤空字符串
2. 创建一个`辅助栈`：
3. 遍历，**对每一个字符进行如下操作**：
- 若为`左括号`，则往栈中存放`右括号`
- 若为`右括号`，如果 `栈为空`  或者  `该右括号与取出的栈顶元素不一样`，则返回`false`;
4. 返回栈`是否为空`的状态；

```java []
class Solution {
    public boolean isValid(String s) {
        //1.特判
        if(s.isEmpty()) return true;
        //2.创建辅助栈
        Stack<Character> stack = new Stack<>();
        //3.遍历
        for(char c : s.toCharArray()){
            if(c == '('){
                stack.push(')');
            }else if(c == '['){
                stack.push(']');
            }else if(c == '{'){
                stack.push('}');
            }else if(stack.isEmpty() || c != stack.pop()){
                return false;
            }
        }
        //4.返回
        return stack.isEmpty();
    }
}
```

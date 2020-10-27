### 解题思路
使用栈（java 的util包里的）来按照题意模拟出
### 代码

```java
import java.util.Stack;
//使用java内置的栈-stack来完成
class Solution {
    public boolean isValid(String s) {
        //定义一个用来装字符类型的栈
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i<s.length();i++){
            //循环拿出s中的元素c
            char c = s.charAt(i);
            //如果拿到的s中的元素是左边的括号类型（'(','[','{'），就压栈
            if(c == '('|| c == '[' || c == '{'){
                stack.push(c);
            //如果是右边的括号类型（')',']','}'）就要掂量一下
            }else{
                //在栈中有元素的情况下：将从s中拿到的右边类型的元素和这个栈顶元素做个比较
                if(!stack.isEmpty()){
                    //看看栈顶的元素
                    char top = stack.peek();
                    //如果栈顶元素可以和‘）’配对，那就让栈顶元素出栈
                    if(c == ')'&& top == '('){
                        stack.pop();
                    //如果栈顶元素可以和‘】’配对，那就让栈顶元素出栈
                    }else if(c == ']'&& top == '['){
                        stack.pop();
                    //如果栈顶元素可以和‘}’配对，那就让栈顶元素出栈
                    }else if(c == '}'&& top == '{'){
                        stack.pop();
                    //所有不匹配的类型都不对
                    }else{
                        return false;
                    }
                //在栈中没元素的情况下：从s中拿出的右边类型的元素没有了配对类型，就黄了。如：栈为空，来了个“]”那肯定不对。
                }else{
                    return false;
                }
            }
        }
        //最后，如果栈空了那就说明都匹配上了，如果栈里还有元素。如：栈里还有’【‘，那是还有没匹配上的，就不对了。
        return stack.isEmpty();
    }
}
```
一刷：艾玛，我这逻辑就像老太太的裹脚布，我。。。。。，（虽然还算思路清晰☺️）

栈顶元素反映了在嵌套层次关系中，最近的需要匹配的元素~
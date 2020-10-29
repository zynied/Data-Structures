//编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
//
// 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
//
// 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
//
//
//
// 示例 1：
//
// 输入：["h","e","l","l","o"]
//输出：["o","l","l","e","h"]
//
//
// 示例 2：
//
// 输入：["H","a","n","n","a","h"]
//输出：["h","a","n","n","a","H"]
// Related Topics 双指针 字符串
// 👍 319 👎 0

package com.nzy.leetcode;
public class ReverseString{
    public static void main(String[] args) {
        com.nzy.leetcode.string.ReverseString.Solution solution = new com.nzy.leetcode.string.ReverseString().new Solution();
        String str="hello";
        solution.reverseString(str.toCharArray());
        System.out.println(str);
    }
//leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void reverseString(char[] s) {
            recursive(s,0,s.length-1);
            /*双指针法 (执行耗时:1 ms,击败了99.99% 的Java用户)*/
            /*int left=0;
            int right=s.length-1;
            while (left<right){
                char temp=s[left];
                s[left]=s[right];
                s[right]=temp;
                left++;
                right--;
            }*/
        }
        /*递归法 (执行耗时:2 ms,击败了9.56% 的Java用户)*/
        private void recursive(char [] s,int left,int right){
            if (left>=right)return;
            char temp=s[left];
            s[left]=s[right];
            s[right]=temp;
            left++;
            right--;
            recursive(s,left,right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

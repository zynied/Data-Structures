//给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
//
//
//
// 进阶：
//
//
// 你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
//
//
//
//
// 示例 1：
//
//
//输入：nums = [3,0,1]
//输出：2
//解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
//
// 示例 2：
//
//
//输入：nums = [0,1]
//输出：2
//解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
//
// 示例 3：
//
//
//输入：nums = [9,6,4,2,3,5,7,0,1]
//输出：8
//解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
//
// 示例 4：
//
//
//输入：nums = [0]
//输出：1
//解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
//
//
//
// 提示：
//
//
// n == nums.length
// 1 <= n <= 104
// 0 <= nums[i] <= n
// nums 中的所有数字都 独一无二
//
// Related Topics 位运算 数组 数学
// 👍 334 👎 0

package com.nzy.leetcode;
public class MissingNumber{
    public static void main(String[] args) {
        com.nzy.leetcode.array.MissingNumber.Solution solution = new com.nzy.leetcode.array.MissingNumber().new Solution();
        System.out.println(solution.missingNumber(new int[]{0, 1, 2}));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int missingNumber(int[] nums) {
            //1.排序
            /*时间复杂度:O(n*logn),空间复杂度:O()*/
            /*执行耗时:6 ms,内存消耗:39 MB*/
            Arrays.sort(nums);
            for (int i=0;i<nums.length;i++){
                if (nums[i]!=i)return i;
            }
            return nums.length;
            //2.Set
            /*时间复杂度:O(n),空间复杂度:O()*/
            /*LinkedHashSet 执行耗时:13 ms,内存消耗:39.3 MB*/
            /*HashSet 执行耗时:7 ms,内存消耗:38.9 MB*/
/*            Set<Integer> in=new HashSet<>();
            for (int i=0;i<nums.length;i++){
                in.add(nums[i]);
            }
            for (int i=0;i<nums.length;i++){
                if (!in.contains(i))return i;
            }
            return nums.length;*/
            //3.直接计算
            /*时间复杂度:O(n),空间复杂度:O()*/
            /*执行耗时:0 ms,内存消耗:38.8 MB*/
/*            int l=nums.length;
            int sum=0;
            for (int i : nums) {
                sum+=i;
            }
            return l*(l+1)/2-sum;*/
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

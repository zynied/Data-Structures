//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
//
//
//
// 示例:
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
//
// Related Topics 数组 哈希表
// 👍 9505 👎 0

package com.nzy.leetcode;
public class TwoSum{
    public static void main(String[] args) {
        com.nzy.leetcode.array.TwoSum.Solution solution = new com.nzy.leetcode.array.TwoSum().new Solution();
        int nums[]=new int[]{1,-2,3,5};
        int[] indexs = solution.twoSum(nums, 6);
        for (int index : indexs) {
            System.out.println(index);
        }

    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            //1.暴力破解法(劣)
            /*时间复杂度:O(n²),空间复杂度:O(1)*/
            /*执行耗时:66 ms,内存消耗:38.7 MB*/
            /*for (int i=0;i<nums.length;i++){
                for (int j=i+1;j<nums.length;j++){
                    if (nums[i]+nums[j]==target){
                        return new int[]{i,j};
                    }
                }
            }
            return new int[]{-1,-1};*/
            //2.哈希表法(优)
            /*时间复杂度:O(n),空间复杂度:O(1)*/
            /*执行耗时:2 ms,内存消耗:38.6 MB*/
            /*HashMap<Integer, Integer> map = new HashMap<>();
            for (int i=0;i<nums.length;i++){
                int diff=target-nums[i];
                if (map.containsKey(diff)) return new int[]{map.get(diff),i};
                map.put(nums[i],i);
            }
            return new int[]{-1,-1};*/
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}

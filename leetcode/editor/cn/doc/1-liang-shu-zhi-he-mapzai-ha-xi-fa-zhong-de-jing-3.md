# 思路 

很明显暴力的解法是两层for循环查找，时间复杂度是O(n^2)。

建议大家做这道题目之前，先做一下这两道
* [242. 有效的字母异位词](https://mp.weixin.qq.com/s/vM6OszkM6L1Mx2Ralm9Dig)
* [349. 两个数组的交集](https://mp.weixin.qq.com/s/N9iqAchXreSVW7zXUS4BVA)

[242. 有效的字母异位词](https://mp.weixin.qq.com/s/vM6OszkM6L1Mx2Ralm9Dig) 这道题目是用数组作为哈希表来解决哈希问题，[349. 两个数组的交集](https://mp.weixin.qq.com/s/N9iqAchXreSVW7zXUS4BVA)这道题目是通过set作为哈希表来解决哈希问题。

本题呢，则要使用map，那么来看一下使用数组和set来做哈希法的局限。

* 数组的大小是受限制的，而且如果元素很少，而哈希值太大会造成内存空间的浪费。
* set是一个集合，里面放的元素只能是一个key，而两数之和这道题目，不仅要判断y是否存在而且还要记录y的下表位置，因为要返回x 和 y的下表。所以set 也不能用。

此时就要选择另一种数据结构：map ，map是一种key value的存储结构，可以用key保存数值，用value在保存数值所在的下表。

C++中map，有三种类型：

|映射 |底层实现 | 是否有序 |数值是否可以重复 | 能否更改数值|查询效率 |增删效率|
|---|---| --- |---| --- | --- | ---|
|std::map |红黑树 |key有序 |key不可重复 |key不可修改 | O(logn)|O(logn) |
|std::multimap | 红黑树|key有序 | key可重复 | key不可修改|O(logn) |O(logn) |
|std::unordered_map |哈希表 | key无序 |key不可重复 |key不可修改 |O(1) | O(1)|

std::unordered_map 底层实现为哈希表，std::map 和std::multimap 的底层实现是红黑树。

同理，std::map 和std::multimap 的key也是有序的（这个问题也经常作为面试题，考察对语言容器底层的理解）。 更多哈希表的理论知识请看[关于哈希表，你该了解这些！](https://mp.weixin.qq.com/s/g8N6WmoQmsCUw3_BaWxHZA)。

**这道题目中并不需要key有序，选择std::unordered_map 效率更高！**

解题思路动画如下：

![1.两数之和.mp4](f6e6bd0b-dde4-4228-885f-448907752a80)



# C++代码 

```
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        std::unordered_map <int,int> map;
        for(int i = 0; i < nums.size(); i++) {
            auto iter = map.find(target - nums[i]);
            if(iter != map.end()) {
                return {iter->second, i};
            }
            map.insert(pair<int, int>(nums[i], i));
        }
        return {};
    }
};
```


**本文  [https://github.com/youngyangyang04/leetcode-master](https://github.com/youngyangyang04/leetcode-master ) 已经收录，里面还有leetcode刷题攻略、各个类型经典题目刷题顺序、思维导图，可以fork到自己仓库，有空看一看一定会有所收获，如果对你有帮助也给一个star支持一下吧！**

> **我是[程序员Carl](https://github.com/youngyangyang04)，利用工作之余重刷leetcode，更多[精彩算法文章](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzUxNjY5NTYxNA==&action=getalbum&album_id=1485825793120387074&scene=173#wechat_redirect)尽在：[代码随想录](https://img-blog.csdnimg.cn/20200815195519696.png)，关注后，回复「Java」「C++」「python」「简历模板」等等，有我整理多年的学习资料。**

**[代码随想录](https://img-blog.csdnimg.cn/20200815195519696.png)里目前已经循序渐及的讲解了数组、链表、哈希表、字符串、栈与队列和二叉树总共80余道经典题目，现在正在讲解回溯法，已经有很多小伙伴在这里打卡学习，如果一路坚持下来，算法功力稳稳的上升一个台阶，赶快上车吧。可以加我[微信](https://img-blog.csdnimg.cn/20200814140330894.png)，备注「简单自我介绍」+「组队刷题」，拉你进入刷题群（无任何广告，纯个人分享）。**


**以下资料希望对你有帮助：**

* [Carl的开源项目以及学习资料](https://github.com/youngyangyang04)
* [Carl的B站视频：算法和编程语言的讲解](https://space.bilibili.com/525438321)
* [Carl的知乎：回答编程相关问题](https://www.zhihu.com/people/sun-xiu-yang-64)
* [Carl的简历模板](https://github.com/youngyangyang04/Markdown-Resume-Template)
* [C++面试&C++学习指南知识点整理](https://github.com/youngyangyang04/TechCPP)
* [BAT技术面试的流程以及注意事项](https://mp.weixin.qq.com/s/815qCyFGVIxwut9I_7PNFw)


**leetcode双指针法经典题目汇总：**

* [双指针法：移除元素](https://mp.weixin.qq.com/s/wj0T-Xs88_FHJFwayElQlA)
* [双指针法：反转字符串](https://mp.weixin.qq.com/s/X02S61WCYiCEhaik6VUpFA)
* [双指针法：替换空格](https://mp.weixin.qq.com/s/t0A9C44zgM-RysAQV3GZpg)
* [双指针法：翻转字符串里的单词](https://mp.weixin.qq.com/s/X3qpi2v5RSp08mO-W5Vicw)
* [双指针法：反转链表](https://mp.weixin.qq.com/s/pnvVP-0ZM7epB8y3w_Njwg)
* [双指针法：环形链表II](https://mp.weixin.qq.com/s/_QVP3IkRZWx9zIpQRgajzA)
* [双指针法：三数之和](https://mp.weixin.qq.com/s/r5cgZFu0tv4grBAexdcd8A)
* [双指针法：四数之和](https://mp.weixin.qq.com/s/nQrcco8AZJV1pAOVjeIU_g)
* [双指针法：总结篇！](https://mp.weixin.qq.com/s/_p7grwjISfMh0U65uOyCjA)


**如果感觉题解对你有帮助，不要吝啬给一个👍吧！**

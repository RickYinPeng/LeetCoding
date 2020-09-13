[toc]

### 一、题目描述

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
- 示例 1:
```
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
```
- 示例 2:
```
Input: "cbbd"
Output: "bb"
```

### 二、题目分析

#### 1. 题目基础分析 

1. 首先要指定这道题是找字符串中最大的回文子串
2. 首先得明确几个边界条件
   1. 如果字符串只有一个字符,那么就返回这个字符，因为一个字符本身也是回文串
   2. 如果字符串有多个字符，但是其中没有出现回文子串，那么结果就是字符串中任意一个字符

#### 2. 暴力算法分析

最简单的一种思路就是暴力解法，之前分析边界条件可知，如果字符串不为空，其<font size = 3 color = red>最小的回文子串的长度</font>就是`1`，所以我们遍历字符串所有`长度>1`的子串，判断它是否为回文子串，如果是再判断其长度是否大于目前找到的最大回文子串的长度(这种暴力解法也有几个细微的优化的地方，代码中会说明)

#### 3. 动态规划分析

动态规划解法: 这道题，需要遍历原字符串所有子串，并且判断其是否是回文串，所以需要一种能够快速判断原字符串所有子串是否是回文串的方法，于是就可以想到动态规划。（后面会结合这里的步骤来分析本题）

动态规划标准的解题步骤：(这里的分析来自:[大佬](https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/))
1. 思考状态（重点）
2. 思考状态转移方程（核心、难点），「状态转移方程」是原始问题的不同规模的子问题的联系。即大问题的最优解如何由小问题的最优解得到。「动态规划」方法依然是「空间换时间」思想的体现，常见的解决问题的过程很像在「填表」。
3. 思考输出，<font color = red>有些时候是最后一个状态，有些时候可能会综合之前所有计算过的状态。</font>
4. 思考优化空间（也可以叫做表格复用,本题不涉及），非常经典的「优化空间」的典型问题是「0-1 背包」问题和「完全背包」问题。

### 三、题目解法

#### 1. 暴力解法
```java
public class coding_5_longest_Palindromic_Substring {
    /**
     * 上面的暴力解法超时了，cao，所以优化下
     * 优化点：
     * 1. s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组
     * 2. 如果目前遍历的子串的长度没有maxLength长度大的话，那么就没必要判断它是否是回文串了，因为判断了也是白判断，我们是要找最大的回文串（这里是最主要的优化点，因为避免了很多次重复比较）
     * 3. 判断是否是回文子串
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || "".equals(s)) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        /**
         * 注意：最小的回文子串的长度就是1，所以我们可以直接从长度等于2的子串开始比较
         */
        int maxLength = 1;
        int startIndex = 0;
        int len = s.length();
        /**
         * todo s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组
         */
        char[] chars = s.toCharArray();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                /**
                 * todo 注意：这里也做了优化，如果目前遍历的子串的长度没有maxLength长度大的化，那么就没必要比较它了，因为比较了也是白比较
                 */
                if (j - i + 1 > maxLength && validatePalindrome2(chars, i, j)) {
                    maxLength = j - i + 1;
                    startIndex = i;
                }
            }
        }
        return s.substring(startIndex, startIndex + maxLength);
    }

    /**
     * 判断是否是回文子串
     *
     * @param chars
     * @param left
     * @param right
     * @return
     */
    public boolean validatePalindrome(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

```
#### 2. 动态规划

1. 思考本题的重心所在，本题是需要我遍历所有的子串，并且判断他们是否是回文串，此时可以思考下，在判断当前子串是否是回文串的时候，能不能根据前面遍历的子串是否是回文串的结果，来得出目前正在判断的子串是否是回文串，有点绕，不过可以细细思考一些。
2. 从回文串的定义我们来分析
    1. 如果一个字符串的头尾两个字符都不相等，那么这个字符串一定不是回文串；
    2. 如果一个字符串的头尾两个字符相等，才有必要继续判断下去。
        1. 如果里面的子串是回文，整体就是回文串；
        2. 如果里面的子串不是回文串，整体就不是回文串。
> 即：在头尾字符相等的情况下，里面子串的回文性质据定了整个子串的回文性质，这就是状态转移。因此可以把「状态」定义为原字符串的一个子串是否为回文子串。
3. 定义状态<br/>`dp[i][j]` 表示子串 `s[i..j]` 是否为回文子串，这里子串 `s[i..j]` 定义为左闭右闭区间，可以取到 `s[i]` 和 `s[j]`。
4. 思考状态转移方程([均来自大佬的分析](https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/))<br/>在这一步分类讨论（根据头尾字符是否相等），根据上面的分析得到：
    ```
    dp[i][j] = (s[i] == s[j]) and dp[i + 1][j - 1]
    ```
    - 「动态规划」事实上是在填一张二维表格，由于构成子串，因此 `i` 和 `j` 的关系是 `i <= j` ，因此，只需要填这张表格对角线以上的部分。
    - 看到 `dp[i + 1][j - 1]` 就得考虑边界情况。
    - 边界条件是：表达式 `[i + 1, j - 1]` 不构成区间，即长度严格小于 `2`，即 `j - 1 - (i + 1) + 1 < 2` ，整理得 `j - i < 3`。
    - 这个结论很显然：`j - i < 3` 等价于 `j - i + 1 < 4`，即当子串 `s[i..j]` 的长度等于`2` 或者等于 `3` 的时候，其实只需要判断一下头尾两个字符是否相等就可以直接下结论了。
    - 如果子串 `s[i + 1..j - 1]` 只有 `1` 个字符，即去掉两头，剩下中间部分只有 `1` 个字符，显然是回文；
    - 如果子串 `s[i + 1..j - 1]` 为空串，那么子串 `s[i, j]` 一定是回文子串。
    - 因此，在 `s[i] == s[j]` 成立和 `j - i < 3` 的前提下，直接可以下结论，`dp[i][j] = true`，否则才执行状态转移。
5. 考虑初始化：初始化的时候，单个字符一定是回文串，因此把对角线先初始化为 `true`，即 `dp[i][i] = true`。
6. 考虑输出：只要一得到 `dp[i][j] = true`，就记录子串的长度和起始位置，没有必要截取，这是因为截取字符串也要消耗性能，记录此时的回文子串的「起始位置」和「回文长度」即可。

```
public class coding_5_longest_Palindromic_Substring {
    /**
     * 动态规划
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        //特别条件判断
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLength = 1;
        int startIndex = 0;

        //定义状态转移数组
        int[][] dp = new int[len][len];

        //将字符串转换位CharArray，这样利于操作
        char[] chars = s.toCharArray();

        //对状态转移数组做初始化
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }

        /**
         * 这里要特别注意填表的顺序
         */
        //开始填表
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (chars[i] != chars[j]) {
                    dp[i][j] = 0;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                //如果 dp[i][j]=1,就表示子串s[i...j]是回文串，此时记录回文串的长度和起始地址
                if (dp[i][j] == 1 && j - i + 1 > maxLength) {
                    maxLength = j - i + 1;
                    startIndex = i;
                }
            }
        }
        return s.substring(startIndex, startIndex + maxLength);
    }
}

```
> 注意：上面的填表顺序很有技巧

### 四、最后

这道题还有很多解法，中心扩散、Manacher，后续如果遇到一起完善，先把这两种算法吃透


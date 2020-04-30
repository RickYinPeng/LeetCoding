package com.rickyin.coding.search;

import java.util.Arrays;
import java.util.Calendar;

/**
 * 分析:
 * 1.题目的意思是，给你一个有效字符串，将其分成两个有效字符串 A 和 B(注意：A和B都必须是有效字符串) 
 * 2.使得A和B连接起来的有效字符串的嵌套深度最小(A和B连接的嵌套深度: depth(A+b) = Math.max(depth(A),depth(B)) )
 * 3.有效字符串的特点是，有对应的括号与之匹配，且左括号必须以正确的顺序闭合(如："([)]" 虽然字符串中都有对应的括号与之匹配，但是顺序不正确，所以不是有效字符串 )。
 * 4.嵌套深度："(())"的depth=2, "((()))"的depth=3, "()()()"的depth=1,由此可见括号的嵌套深度等于栈中最多连续出现的左括号"("的个数
 * 5."(()())" 此字符串的深度是2,最多连续出现了 2 个左括号,todo 现在要拆分以后再重组，其实思路就有了，把这两个连续出现的左括号分到不同组即可。
 * 6.输出 [0,1,1,1,1,0] 0分到A组,1分到B组  A: () B: ()() depth = 1
 * 4."(((())))"此字符串的嵌套深度是4，这个4就是栈中连续左括号出现的最大次数，如果我们拆分这个字符串为A和B，该如何拆分呢?
 * 5.输出 [0,1,0,1,1,0,1,0] A:(()) B:(()) dept = 2
 * 6.从这个示例中我们知道，重组以后的嵌套深度是原始嵌套深度的一半（不能整除的时候上取整）
 *     如果原始嵌套深度是偶数，例如 6，重组的嵌套深度 = 3；
 *     如果原始嵌套深度是奇数，例如 7，重组的嵌套深度 = 4。
 * 7.那如何分组呢?
 * 8.因为题目给定的就是一个有效字符串,所以我们碰到连续的左括号的时候分别分组就ok了,0代表分入A,1代表分入B,我们可以使用奇偶来分
 * 9.随着嵌套深度增大,最开始深度为0 dept=0,i代表0或1
 *    例: ((())) --> [0,1,0,0,1,0] A:(()) B: ()
 *    a.碰到一个 '(' , dept++ ,i = dept%2 = 1%2 = 1 分到A组
 *    b.紧接着又碰到一个'(',dept++,i = dept%2 = 2%2 = 0 分到B组
 *    c.又碰到一个'(',dept++,i = dept%2 = 3%2 = 1 分到A组
 *    d.碰到一个')',这个右括号应该和它紧挨着的左括号分到同一组,所以直接 i = dept%2 = 3%2 = 1 分到A组,紧接着dept--
 *    e.在上一步为什么先 i = dept%2 = 3%2 = 1后 dept--呢？为了让右括号与之紧接着对应的左括号分到一组,所以不能先dept++,如果先dept++，
 *      那么这个右括号就会被分到下一组去,为什么dept--呢,因为这个分完了。
 *    todo:左括号奇偶分配，右括号根据depth%2得出栈顶左括号的位置是0还是1 depth--是因为当前栈顶的左括号匹配完之后再遇见右括号该匹配栈顶的第二个左括号了
 */
public class coding_1111_有效括号的嵌套深度 {
    public static void main(String[] args) {
        int[] ints = maxDepthAfterSplit("(((())))");
        System.out.println(Arrays.toString(ints));
    }
    public static int[] maxDepthAfterSplit(String seq) {
        /**
         * ( ( ( ) ) )
         * 1 2 3 4 5 6
         * 1 0 1 0 1 0
         */
        int[] res = new int[seq.length()];
        char[] chars = seq.toCharArray();
        int dept = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                dept++;
                res[i] = dept % 2;
            } else {
                res[i] = dept % 2;
                dept--;
            }
        }
        return res;
    }
}

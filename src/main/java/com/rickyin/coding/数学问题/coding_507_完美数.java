package com.rickyin.coding.数学问题;

public class coding_507_完美数 {
    public static void main(String[] args) {

    }
    /**
     * 首先由于完美数的定义，需要排除自身，所以数字 11 一定不是完美数
     * 其次我们需要计算 num 除了它自身以外的所有正因子之和 sum，正因子必然是成对出现的，故而我们只需要遍历到 num 的平方根 sqrt 即可
     *  1.以 3636 为例，它的非自身外正因子有，1、2、3、4、6、9、12、18，其中 11 和 66 单独计算，[2, 18]、[3, 12]、[4, 9]都是对应关系、
     *  2.所以只需要遍历到 3636 的平方根 66 就可以获取全部正因子
     *  3. 1 单独计算的原因是要排除自身，66 单独计算的原因是 6 * 6 = 36，两个值相同，故而只能计算一遍
     */
    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int sum = 1;
        int i = 2;
        double sqrt = Math.sqrt(num);
        for (; i < sqrt; i++) {
            if (num % i == 0) {
                sum += i;
                sum += num / i;
            }
        }
        if (i * i == num) {
            sum += i;
        }
        return sum == num;
    }
}

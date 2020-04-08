package com.rickyin.coding.数学问题;

import java.util.*;

/**
 * TODO 这个题真nm恶心！！！(水浒问题)
 */
public class coding_365_水壶问题 {
    public static void main(String[] args) {
        coding_365_水壶问题 coding_365 = new coding_365_水壶问题();
        System.out.println(coding_365.canMeasureWater2(3, 5, 4));
    }

    /**
     * todo 最大公约数(反正我是想不到):最快的解答
     */
    public boolean canMeasureWater(int x, int y, int z) {
        if (x == z || y == z || x + y == z || z == 0) {
            return true;
        }
        if (x + y < z) {
            return false;
        }
        return z == 0 || (x + y >= z && z % gcd(x, y) == 0);
    }

    int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }


    /**
     * todo 为什么使用搜索算法？
     *  首先对于这种题来说，我们要分清楚有哪些情况
     *  这里定义水浒为A和B，定义有序整数对(a,b)来表示当前水浒A和水浒B中水量
     *  题目中说你允许:
     *      1.装满任意一个水壶
     *      2.清空任意一个水壶
     *      3.从一个水壶向另外一个水壶倒水，直到装满或者倒空
     *  首先我们来分析题目中实例一的结果怎么得到
     *  实例一分析:
     *        A = 3,B=5,z=4
     *      1.首先A和B都为0(代表两个水浒为空的状态) --> (0,0)
     *      2.装满B水浒 --> (0,5)
     *      3.将B水浒中的水倒入A水浒(从一个水壶向另外一个水壶倒水，直到装满或者倒空) --> (3,2)
     *      4.清空A水浒 --> (0,2)
     *      5.将B水浒中的睡倒入A水浒 --> (2,0)
     *      6.装满B水浒 --> (2,5)
     *      7.将B水浒中的睡导入A水浒(因为A水浒容量为3,之前倒入的水占了2,所以这次B水浒只能向A水浒倒入1的水) --> (3,4)
     *      8.此时清空A水浒的水 --> (0,4)
     *      9.正好满足题目要求
     *  情况分析:
     *       对于任意的状态，一定是至少有一个水浒为空,或者至少有一个水浒为满,不会出现两个水浒都不满的情况,因为不满这种情况只有在到的时候出现,而到
     *      的时候直到装满或者倒空,所以不可能出现A向B倒水，A有剩余B也有剩余,要么B被倒满A有剩余,要么B有剩余A为空
     *      1.倒满A水浒(包括A为空和非空的情况)
     *      2.倒满B水浒(包括B为空和非空的情况)
     *      3.清空A水浒
     *      4.清空B水浒
     *      5.将A水浒中的水倒入B水浒,A水浒为空,B水浒有剩余(B水浒有剩余可以剩余为0)
     *      6.将A水浒中的水倒入B水浒,A水浒有剩余,B水浒满
     *      7.将B水浒中的水倒入A水浒,B水浒为空,A水浒有剩余(A水浒有剩余可以剩余为0)
     *      8.将B水浒中的水倒入A水浒,B水浒有剩余,A水浒满
     *      得到这八种状态
     *      所以转换为编程的思想就是构建一张图,图的起点是(0,0),终点是(z,任意整数),(任意整数,z),(a,b)且a+b=z
     *  利用这八种状态分析示例一:
     *      (0,0) -->[装满B(是上面的第2种操作)] (0,5)  -->[将B的水倒入A,A满B有剩余(上面第8种操作)] (3,2)
     *            -->[清空A水浒(上面第3种操作)] (0,2)  -->[将B的水倒入A,A有剩余,B为空(上面第7种操作)] (2,0)
     *            -->[倒满B水浒(上面第2种操作)] (2,5)  -->[将B的水倒入A,A满B有剩余(上面第8种操作)] (3,4) --> 得解
     *   注意: 从一种状态可以扩展出8种状态,因为状态有重复 比如: (0,0) --> 8种状态种有一种是(0,5) --> 8种状态种有一种是(0,0) 所以是一个有向
     *        有环得图,所以遍历得时候需要判断是否访问过
     */

    public boolean canMeasureWater2(int x, int y, int z) {
        if(z==0){
            return true;
        }
        if(x+y<z){
            return false;
        }
        State initState = new State(0,0);
        //使用广度优先搜索
        Queue<State> queue = new LinkedList<State>(); //队列
        Set<State> visited = new HashSet<State>();      //用来判断是否访问过

        queue.add(initState);
        visited.add(initState);

        while (!queue.isEmpty()){
            State head = queue.poll();

            int curX = head.getX();
            int curY = head.getY();

            if(curX == z || curY ==z || curX+curY ==z){
                return true;
            }

            //从当前状态扩展所有可能的下一个状态
            List<State> nextState = getNextState(curX, curY, x, y);
            for (State state : nextState) {
                //todo 注意: 因为Set集合种装的是对象,如果你不重写对象的 equals方法和 hashcode方法,那么这里将不能准确判断
                if(!visited.contains(state)){
                    queue.add(state);
                    visited.add(state);
                }
            }
        }
        return false;
    }

    private List<State> getNextState(int curX, int curY, int x, int y) {

        List<State> nextStateList = new ArrayList<State>(8);

        //todo 8种状态
        //1.倒满A水浒(包括A为空和非空的情况)
        State state1 = new State(x, curY);
        //2.倒满B水浒(包括B为空和非空的情况)
        State state2 = new State(curX, y);
        //3.清空A水浒
        State state3 = new State(0, curY);
        //4.清空B水浒
        State state4 = new State(curX, 0);
        //5.将A水浒中的水倒入B水浒,A水浒为空,B水浒有剩余(B水浒有剩余可以剩余为0)
        State state5 = new State(0, curY+curX);
        //6.将A水浒中的水倒入B水浒,A水浒有剩余,B水浒满
        State state6 = new State(curX-(y-curY), y);
        //7.将B水浒中的水倒入A水浒,B水浒为空,A水浒有剩余(A水浒有剩余可以剩余为0)
        State state7 = new State(curX+curY, 0);
        //8.将B水浒中的水倒入A水浒,B水浒有剩余,A水浒满
        State state8 = new State(x, curY-(x-curX));

        if(curX<x){
            nextStateList.add(state1);
        }
        if(curY<y){
            nextStateList.add(state2);
        }
        if(curX>0){
            nextStateList.add(state3);
        }
        if(curY>0){
            nextStateList.add(state4);
        }
        if(curY+curY<=y){
            nextStateList.add(state5);
        }
        if(curX+curY<=x){
            nextStateList.add(state7);
        }
        if(curX-(y-curY) >= 0){
            nextStateList.add(state6);
        }
        if(curY-(x-curX) >= 0){
            nextStateList.add(state8);
        }
        return nextStateList;
    }

    /**
     * 状态节点类
     */
    private class State{
        private int x;
        private int y;

        public State(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return x == state.x &&
                    y == state.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}

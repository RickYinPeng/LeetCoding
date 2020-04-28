[toc]
### 题目描述

给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

你可以假设数组中无重复元素。

- 示例 1:
```
输入: [1,3,5,6], 5
输出: 2
```
- 示例 2:
```
输入: [1,3,5,6], 2
输出: 1
```
- 示例 3:
```
输入: [1,3,5,6], 7
输出: 4
```
- 示例 4:
```
输入: [1,3,5,6], 0
输出: 0
```

### 题目分析

1. 数组中找寻某个位置的题目解法一般就是二分搜索算法,这个不用多说哈(如果对二分法不熟悉,那么可以先不看本题题解,看后面对于二分法的详解)
2. 题目中,当目标值大于数组最后一个元素的时候,直接将目标值插入数组末尾,所以可以直接返回数组的长度
3. 当目标值大于数组最后一个元素的条件不成立时,目标值插入的位置一定在数组区间中
4. 倘若目标值插入的位置在数组区间中,且数组中有一个元素的值和目标值相等,那么返回该元素的下标
5. 倘若目标值插入的位置在数组区间中,数组中没有元素与目标值相等,那么返回第一个大于目标值的元素的下标
6. <font size = 3 color = red>所以此题转换成了,寻找第一个大于等于目标值的元素的下标(重要)</font>


### 题目解法

#### 解法一: 普通的二分搜索算法解决本题

```java
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        if(target > nums[len-1]){
            return len;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
```
#### 解法二: 用“排除法”写的二分查找算法解决本题(可看完后面分析再来看这里的题解)

```java
    /**
     * 使用排除法(减治法)写的二分查找算法解决本题
     */
    public int searchInsert2(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        
        //特别的判断条件
        //当目标值大于数组最后一个元素的时候,直接将目标值插入数组末尾,所以可以直接返回数组的长度
        if (target > nums[len - 1]) {
            return len;
        }
        int left = 0;
        int right = nums.length - 1;
        
        //先把循环可以继续的条件写成 while (left < right)。
        while (left < right) {
            int mid = left + (right - left) >>> 1;
            
            //思考当 nums[mid] 满足什么性质的时候，mid 不是解
            // 当nums[mid] 严格小于目标元素时,不是解
            //说明:此题是找 ≥ 目标值的第一个元素,如果 num[mid]<target,那么num[mid]肯定不是解
            if (nums[mid] < target) {
                // 下一轮搜索区间 [mid+1,right]
                //mid被分到了左边区间,所以不需要向上取整
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
```

### 用“排除法”写的二分查找算法

#### 常规的二分查找算法(教科书上的二分查找算法)

通常教课书上给出的二分查找代码，循环部分给出的条件是 `while (left <= right)` ，表示当 `left == right` 成立的时候，还有一个元素，即索引`left（right）`位置的元素还没有看到，需要继续查看这个元素的值，看看是不是我们想要的。

这个思路把待查找数组分为了 3 个部分：`mid` 所在位置，`mid` 的左边，`mid` 的右边，根据 `mid` 元素与目标元素的值的大小关系，如果 `nums[mid]` 恰好等于 `target` 直接返回就好了，否则根据不等关系，确定下一轮搜索的区间在哪里。

#### 常规的二分查找算法解某些题时出现的问题

当使用常规的二分查找算法解决如下题目时,会出现傻傻分不清返回 `left` 还是返回 `right`,一不小心会把代码写成线性查找。

- 查找第一个大于等于给定值的元素
- 查找最后一个小于等于给定值的元素
- 查找第一个值等于给定值的元素(数组中有重复元素)
- 查找最后一个值等于给定值的元素(数组中有重复元素)

这一类问题的共同特点是：目标值往往在待查找数组中存在多个，但是题目要求我们返回的是一个边界值。

#### 排除法写二分搜索分析

##### 不妨从哪些元素一定不是目标元素考虑

做对这一类问题的思路是“排除法”，在本题解最开始其实已经介绍了，我们的思路是做排除法：具体是根据看到的 mid 位置的元素，排除掉不可能存在目标元素的区间，而下一轮在可能存在目标的子区间里查找。

具体做法:

**1、先把循环可以继续的条件写成 `while (left < right)`。**

在循环的过程中 `left` 不断右移，`right` 不断左移。从形式上看，退出循环的时候一定有 `left == right` 成立。此时要注意：`left （right）` 这个位置的值可能程序还没有读取到，因此“有可能”需要再对 `left（right）` 这个位置的值是否是目标元素的值做一次判断。

**2、<font size = 3 color = red>写 `if` 和 `else` 语句的时候，思考当 `nums[mid]` 满足什么性质的时候，`mid` 不是解</font>，进而接着判断 `mid` 的左边有没有可能是解，`mid` 的右边有没有可能是解。**

<font size = 3 color = red>根据 `mid` 被分到左边区间还是右边区间，代码写出来只有以下 2 种（重难点）：</font>

- 边界收缩行为 1：`mid` 被分到左边。即区间被分成 `[left, mid]` 与 `[mid + 1, right]`，这里用“闭区间”表示区间端点可以取到，下同；<br/>代码写出来是这样的：

```java
if (check(mid)) {
    // 下一轮搜索区间是 [mid + 1, right]，因此把左边界设置到 mid + 1 位置
    left = mid + 1;
} else {
    // 上面对了以后，不加思考，剩下的区间一定是 [left, mid]，因此左边界向右收缩到 mid 位置
    right = mid;
}
```
- 边界收缩行为 2： `mid` 被分到右边。即区间被分成 `[left, mid - 1]` 与 `[mid, right]`；

```java
if (check(mid)) {
    right = mid - 1;
} else {
    left = mid;
}
```

**3、根据“边界收缩行为”修改取中间数的行为（重难点）。**

先说一下中间数的取法。一般是这样的：
```java
int mid = (left + right) / 2;
```
这种写法在绝大多数情况下没问题，但是在 `left` 和 `right` 特别大的场景中，`left + right` 会发生整形溢出，得到一个负数，`mid` 的值随之也是负数。改进的写法是：
```java
int mid = left + (right - left) / 2;
```
这两种写法事实上没有本质的区别，在 `left` 和 `right` 都表示数组索引的时候，几乎不会越界，因为绝大多数情况下不会开那么长的数组。

<font size = 3 color = red>这里有一个细节，/ 是整除，它的行为是“向下取整”，造成了 `int mid = (left + right) / 2` 这种写法 `mid` 永远取不到带搜索区间里最右边的位置（读者可以举一个只有 2 个元素的子数组，理解这句话）。</font>

面对上面的“边界收缩行为 2”（`mid` 被分到右边），在待搜索区间收缩到只剩下 2 个元素的时候，就有可能（请读者在练习的过程中体会这里我的描述为什么是“有可能”而不是“一定”）造成死循环。如下图：
![image](http://tie.027cgb.com/606599/LeetCoding/1.png)

有了上面的分析，我们把上面“边界收缩行为”对应的中间数取法补上：

- 边界收缩行为 1： `mid` 被分到左边。即区间被分成 `[left, mid]` 与 `[mid + 1, right]`，此时取中间数的时候下取整(此时不会出现死循环问题,因为mid被分到了左边)。
```java
int mid = left + (right - left) / 2;
if (check(mid)) {
    // 下一轮搜索区间是 [mid + 1, right]
    left = mid + 1;
} else {
    right = mid;
}
```
- 边界收缩行为 2： `mid` 被分到右边。即区间被分成 `[left, mid - 1]` 与 `[mid, right]`，此时取中间数的时候上取整。
```java
int mid = left + (right - left + 1) / 2;
if (check(mid)) {
    // 下一轮搜索区间是 [left, mid - 1]
    right = mid - 1;
} else {
    left = mid;
}
```
> 在 `if else` 语句里面只要出现 `left = mid` 的时候，把去中间数行为改成上取整即可。

#### 排除法写二分搜索代码分析

```java
    /**
     * 排除法(减治法)写的二分查找算法
     */
    public int binarySearch2(int[] num, int target) {
        if (num.length == 0) {
            return -1;
        }
        int left = 0;
        int right = num.length - 1;
        
        //先把循环可以继续的条件写成 while (left < right)
        //有可能”需要再对 left（right） 这个位置的值是否是目标元素的值做一次判断。
        while (left < right) {
            //不需要向上取整,因为这里mid并没有被分到右边区间
            int mid = left + (right - left) >>> 1;
            if (num[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        //这里就是前面说的: 有可能”需要再对 left（right） 这个位置的值是否是目标元素的值做一次判断。
        if (num[left] != target) {
            return -1;
        }
        return left;
    }
```

### 扩展

#### 查找最后一个小于等于给定值的元素

```java
    /**
     * 查找最后一个小于等于给定值的元素
     * 例: [1,3,5,7,9,11] target=8,最后一个小于等于给定值的元素就是 7(下标=3)
     */
    public static int binarySearch3(int[] num, int target) {
        if (num.length == 0) {
            return -1;
        }
        int left = 0;
        int right = num.length - 1;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;

            //思考 num[mid] 什么时候不是解
            //当 num[mid]<=target 时是解,所以当 num[mid]>target 时不是解
            if (num[mid] > target) {
                right = mid - 1;
            } else {
                //因为mid被分到了左边区间,所以需要向上取整 int mid = (left + right + 1) >>> 1;
                left = mid;
            }

            /**
             * 下面这种写法,是我自己尝试做的 查找最后一个小于给定值的元素(没有等于)
             */
            /*
            if(num[mid] >= target){
                right = mid-1;
            }else {
                left = mid;
            }
            */
        }
        return left;
    }
```

#### 查找第一个值等于给定值的元素(数组中有重复元素)

```java
    /**
     * 查找第一个值等于给定值的元素(数组中有重复元素)
     * 例: [1,3,5,7,7,11] target=7,第一个值等于给定值的元素就是 7(下标=3)
     */
    public static int binarySearch4(int[] num, int target) {
        if (num.length == 0) {
            return -1;
        }
        int left = 0;
        int right = num.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;

            //思考 num[mid] 什么时候不是解
            //当 num[mid]=target && num[mid-1]=target时不是解
            if (num[mid] == target && num[mid - 1] == target) {
                right = mid - 1;
            } else if (num[mid] < target) {
                left = mid + 1;
            }else{
                right = mid;
            }

        }
        return left;
    }
```




package bitmap;

import java.util.Arrays;

/**
 * 使用位图法判断整形int数组中是否存在重复值
 * <p/>
 * <p>这里使用到的技巧: 数组中的值作为位图中的索引(有整数前提)</p>
 *
 * @author Zero
 *         Created on 2017/5/10.
 */
public class HasDuplicate {


    /**
     * 判断<em>整形数组</em>中是否有重复数据，时间复杂度为O（n）
     *
     * @param arr
     * @return
     */
    public static boolean hasDuplicatedItem(int[] arr) {
        // 扫描数组找最大值,因为要使用数组中的值作为位图中的索引,所以需要确定最大值
        // Arrays.stream(arr).max().getAsInt();
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }


        //如果数组不重复,那么数组arr.length必须大于最大值
        if (arr.length > max) {
            return true;
        }

        // 按最大值创建一个新bit数组(位图)
        int[] bitArray = new int[max + 1];//加1是因为值可以为0
        // 按值向新数组中添值，如value为3则bitArray[3]=1
        for (int value : arr) {
            if (bitArray[value] != 0) {
                // 如果value指向的位置已经不是零，说明之前已经给这一块置1了，立即返回true表示数组有重复
                return true;
            } else {
                // value指向的位置是零,则置为1表示这一位已经有数存在了
                bitArray[value] = 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 5},
                {1, 2, 3, 5, 3, 5},
                {0, 1, 2, 3, 5, 3, 5},
                {1, 2, 3, 5, 3, 5, 56, 534, 3, 32},
                {0, 0, 1, 2, 3, 5, 56, 534, 78, 32},
        };
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("数组:%s %s 重复元素\n", Arrays.toString(arr[i]), hasDuplicatedItem(arr[i]) ? " 存在 " : " 不存在 ");
        }
    }
}
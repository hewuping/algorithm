package bitmap;

import java.util.Arrays;

public class BitmapSorter {
    public static void main(String[] args) {
        int[] arr = {1, 7, -3, 0, 0, 6, 6, 9};
        System.out.println("排序前: " + Arrays.toString(arr));
        bitmapSort(arr);
        System.out.println("排序后: " + Arrays.toString(arr));
    }

    /**
     * 使用位图法进行排序
     *
     * @param arr
     */
    public static void bitmapSort(int[] arr) {
// 找出数组中最值
        int max = arr[0];
        int min = max;
        for (int i : arr) {
            if (max < i) {
                max = i;
            }
            if (min > i) {
                min = i;
            }
        }
        //arr映射到位图数组上
        int[] map = new int[max - min + 1];
        System.out.print("arr value =>map index : ");
        for (int value : arr) {
            int i = value - min;//减去最小值确保可以获得整数index
            System.out.printf("%d => %d , ", value, i);
            map[i]++;//i对应一个数组元素(i+min), newArr[i]的值对应了这个数组元素出现的次数
        }
        //out: arr value =>map index : 1 => 4 , 7 => 10 , -3 => 0 , 0 => 3 , 0 => 3 , 6 => 9 , 6 => 9 , 9 => 12 ,
        //这时可以发现, 大的值都在位图的后面(index比较大)

        System.out.println("\n位图: " + Arrays.toString(map));
        //位图: [1, 0, 0, 2, 1, 0, 0, 0, 0, 2, 1, 0, 1]

        //现在我们要算错位图中的真正值,并赋值到arr中
        int index = 0;
        for (int i = 0; i < map.length; i++) {
            while (map[i] > 0) {//如果值为0,说明i不对应任何原始数组元素, 因而忽略掉
                map[i]--;//每减去一次次数, 这个值就要加上一个min
                arr[index] = i + min;//修改原来的数组
                index++;
            }
        }
//        System.out.println(index);
    }
}
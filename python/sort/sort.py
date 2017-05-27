def insert_sort(lists):
    # 插入排序

    # 插入排序的基本操作就是将一个数据插入到已经排好序的有序数据中，从而得到一个新的、个数加一的有序数据，
    # 算法适用于少量数据的排序，时间复杂度为O(n^2)。是稳定的排序方法。
    # 插入算法把要排序的数组分成两部分：
    # 第一部分包含了这个数组的所有元素，但将最后一个元素除外（让数组多一个空间才有插入的位置），
    # 而第二部分就只包含这一个元素（即待插入元素）。在第一部分排序完成后，再将这个最后元素插入到已排好序的第一部分中。
    count = len(lists)
    for i in range(1, count):
        me = lists[i]
        j = i - 1  # 上一个元素index
        while j >= 0:  # 边界判断,防止数组越界
            if lists[j] > me:
                lists[j + 1], lists[j] = lists[j], me  # 继续往上爬
            j -= 1
        print('insert: ', lists)
    return lists


def bubble_sort(lists):
    # 冒泡排序

    # 它重复地走访过要排序的数列，一次比较两个元素，如果他们的顺序错误就把他们交换过来。
    # 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
    count = len(lists)
    for i in range(0, count):
        for j in range(i + 1, count):
            if lists[i] > lists[j]:
                lists[i], lists[j] = lists[j], lists[i]
                print('bubble: ', lists)

    return lists


def shell_sort(lists):
    # 希尔排序

    # 希尔排序(Shell Sort)是插入排序的一种。
    # 也称缩小增量排序，是直接插入排序算法的一种更高效的改进版本。
    # 希尔排序是非稳定排序算法。该方法因DL．Shell于1959年提出而得名。
    # 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
    # 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
    count = len(lists)
    step = 2
    group = count / step
    while group > 0:
        for i in range(0, group):
            j = i + group
            while j < count:
                k = j - group
                key = lists[j]
                while k >= 0:
                    if lists[k] > key:
                        lists[k + group] = lists[k]
                        lists[k] = key
                    k -= group
                j += group
        group /= step
    return lists


def quick_sort(lists, left, right):
    # 快速排序
    # 通过一趟排序将要排序的数据分割成独立的两部分，
    # 其中一部分的所有数据都比另外一部分的所有数据都要小，
    # 然后再按此方法对这两部分数据分别进行快速排序，
    # 整个排序过程可以递归进行，以此达到整个数据变成有序序列。
    if left >= right:
        return lists
    key = lists[left]
    low = left
    high = right
    while left < right:
        while left < right and lists[right] >= key:
            right -= 1
        lists[left] = lists[right]
        while left < right and lists[left] <= key:
            left += 1
        lists[right] = lists[left]
    lists[right] = key
    quick_sort(lists, low, left - 1)
    quick_sort(lists, left + 1, high)
    return lists


def select_sort(lists):
    # 选择排序(直接排序)

    # 第1趟，在待排序记录r1 ~ r[n]中选出最小的记录，将它与r1交换；
    # 第2趟，在待排序记录r2 ~ r[n]中选出最小的记录，将它与r2交换；
    # 以此类推，第i趟在待排序记录r[i] ~ r[n]中选出最小的记录，将它与r[i]交换，使有序序列不断增长直到全部排序完毕。
    count = len(lists)
    for i in range(0, count):
        min = i
        for j in range(i + 1, count):
            if lists[min] > lists[j]:
                min = j
        lists[min], lists[i] = lists[i], lists[min]
    return lists


###############################################
def adjust_heap(lists, i, size):
    lchild = 2 * i + 1
    rchild = 2 * i + 2
    max = i
    if i < size / 2:
        if lchild < size and lists[lchild] > lists[max]:
            max = lchild
        if rchild < size and lists[rchild] > lists[max]:
            max = rchild
        if max != i:
            lists[max], lists[i] = lists[i], lists[max]
            adjust_heap(lists, max, size)


def build_heap(lists, size):
    for i in range(0, (size / 2))[::-1]:
        adjust_heap(lists, i, size)


def heap_sort(lists):
    size = len(lists)
    build_heap(lists, size)
    for i in range(0, size)[::-1]:
        lists[0], lists[i] = lists[i], lists[0]
        adjust_heap(lists, 0, i)


###############################################
def merge(left, right):
    i, j = 0, 0
    result = []
    while i < len(left) and j < len(right):
        if left[i] <= right[j]:
            result.append(left[i])
            i += 1
        else:
            result.append(right[j])
            j += 1
    result += left[i:]
    result += right[j:]
    return result


def merge_sort(lists):
    # 归并排序
    if len(lists) <= 1:
        return lists
    num = len(lists) / 2
    left = merge_sort(lists[:num])
    right = merge_sort(lists[num:])
    return merge(left, right)


###############################################

import math


def radix_sort(lists, radix=10):
    # 基数排序
    k = int(math.ceil(math.log(max(lists), radix)))
    bucket = [[] for i in range(radix)]
    for i in range(1, k + 1):
        for j in lists:
            bucket[j / (radix ** (i - 1)) % (radix ** i)].append(j)
        del lists[:]
        for z in bucket:
            lists += z
            del z[:]
    return lists


if __name__ == '__main__':
    lists = [3, 5, 8, 4, 9, 1]
    print(1)
    print(insert_sort(lists))
    print(bubble_sort(lists))


# http://python.jobbole.com/82270/

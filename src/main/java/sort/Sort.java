package sort;

import javafx.scene.control.Pagination;

import java.util.Arrays;
import java.util.Random;

public class Sort {

    /**
     * 冒泡
     * 它重复地走访过要排序的元素，依次比较相邻两个元素，如果他们的顺序错误就把他们调换过来，直到没有元素再需要交换，排序完成
     * o(n*n)
     * @param num
     */
    public static int[] BubbleSort(int[] num){
        int len = num.length;
        for(int i=0;i<len;i++){
            for(int j = 0;j<len-i-1;j++){
                if(num[j] > num[j+1]){
                    int temp = num[j];
                    num[j] = num[j+1];
                    num[j+1] = temp;
                }
            }
        }
        return num;
    }

    /**
     * 鸡尾酒排序
     * 冒泡的改进
     * 此算法与冒泡排序的不同处在于从低到高然后从高到低，而冒泡排序则仅从低到高去比较序列里的每个元素
     * @param num
     */
    public static void CocktailSort(int[] num){
        int len = num.length;
        for(int i = 0;i<len;i++){
            for(int j = 0;j<len-i-1;j++){
                if(num[j] >num[j+1]){
                    int temp = num[j];
                    num[j] = num[j+1];
                    num[j+1] = temp;
                }
            }
            for(int j = len-i-2;j>0;j--){
                if(num[j-1]>num[j]){
                    int temp = num[j-1];
                    num[j] = num[j-1];
                    num[j-1] = temp;
                }
            }
        }
    }

    /**
     * 选择排序
     * 初始时在序列中找到最小（大）元素，放到序列的起始位置作为已排序序列；然后，再从剩余未排序元素中继续寻找最小（大）元素，放到已排序序列的末尾。
     * 不稳定 o(n*n)
     * @param num
     */
    public static void selectionSort(int[] num){
        int len = num.length;
        for(int i = 0;i<len;i++){
            int min = i;
            for(int j = i;j<len;j++){
                if(num[min] > num[j]){
                    min = j;
                }
            }
            if(min != i){
                int temp = num[i];
                num[i] = num[min];
                num[min] = temp;
            }
        }
    }

    /**
     * 插入排序 稳定 O(n^2)
     * 插入排序不适合对于数据量比较大的排序应用。但是，如果需要排序的数据量很小，比如量级小于千，那么插入排序还是一个不错的选择
     * @param num
     */
    public static void insertionSort(int[] num){
        int len = num.length;
        for(int i = 0;i<len;i++){
            int index = num[i];
            for(int j = i-1;j>=0;j--){
                if(index <num[j]){
                    num[j+1] = num[j];
                    if(j == 0)
                        num[j] = index;
                }
                if(index>=num[j]){
                    num[j+1] = index;
                    break;
                }
            }
        }
    }

    /**
     * 二分插入（插入排序的改进）
     * @param num
     */
    public static void InsertionSortDichotomy(int[] num){
        int len = num.length;
        for(int i = 0;i<len;i++){
            int start = 0;
            int end = i-1;
            while (start<=end){
                int middle = (start+end)/2;
                if(num[middle] > num[i]){
                    end = middle-1;
                }else {
                    start = middle +1;
                }
            }
            int temp = num[i];
            for(int j = i;j>start;j--){
                num[j] = num[j-1];
            }
            num[start] = temp;
        }
    }

    /**
     * 希尔排序 也叫递减增量排序，是插入排序的一种更高效的改进版本。希尔排序是不稳定的排序算法
     * @param num
     */
    public static void shellSort(int[] num){
        int h = num.length;
        while (true){
            h = h/2;
            for(int i = h;i<num.length;i++){
                int temp = num[i];
                for(int j = i-h;j>=0;j=j-h){
                    if(num[j] > temp){
                        num[j+h] = num[j];
                        if(j-h<0){
                            num[j] = temp;
                        }
                    }else {
                        num[j+h] = temp;
                        break;
                    }
                }
            }
            if(h == 1){
                break;
            }
        }
    }

    /**
     * 归并排序
     * @param num
     */
    public static void mergeSort(int[] num,int left,int right){
        if(left == right)
            return;
        int middle = (left+right)/2;
        mergeSort(num,left,middle);
        mergeSort(num,middle+1,right);
        merge(num,left,middle,right);
    }

    public static  void merge(int[] num,int left,int middle,int right){
        int[] temp = new int[right-left+1];
        int i = left;
        int j = middle+1;
        int index = 0;
        while (true){
            if(i > middle && j > right){
                break;
            }
            if(i>middle){
                temp[index++] = num[j++];
                continue;
            }
            if(j > right){
                temp[index++] = num[i++];
                continue;
            }
            if(num[i]>num[j]){
                temp[index++] = num[j];
                j++;
            }else {
                temp[index++] = num[i];
                i++;
            }
        }
        for(int k = 0;k<temp.length;k++){
            num[left++] = temp[k];
        }
    }

    /**
     * 归并排序 非递归（迭代）自底向上
     * O(nlogn) 稳定
     * @param num
     * @param len
     */
    public static void mergeSort(int[] num, int len){
        int left ,mid,right;
        for(int i = 1;i<len;i *=2){
            left = 0;
            while (left+i <len){
                mid = left+i-1;
                right = mid+i>len?len-1:mid+i;
                merge(num,left,mid,right);
                left = right+1;
            }
        }
    }

    /**
     * 堆排序
     * O(nlogn) 不稳定
     * @param num
     */
    public static void heapSort(int[] num){
        int len = num.length;
        // 初始化堆
        for(int i =len/2-1;i>=0;i--){
            heapSortHelper(num,i,num.length);
        }
        for(int i = len-1;i>0;i--){
            // 大值放到数组末尾
            int temp = num[i];
            num[i] = num[0];
            num[0] = temp;
            // 调整堆
            heapSortHelper(num,0,i);
        }
    }

    public static void heapSortHelper(int[] num,int i,int len){
        int temp = num[i];
        for(int j = 2*i+1;j<len;j = 2*j+1){
            if(j+1<len && num[j+1]>num[j]){
                j++;
            }
            if(num[j]>temp){
                num[i] = num[j];
                i = j;
            }else {
                break;
            }
        }
        num[i] = temp;
    }

    /**
     * 快速排序
     * O(nlogn)
     * @param num
     */
    public static void quickSort(int[] num){
        quickSortHelper(num,0,num.length-1);
    }

    public static void quickSortHelper(int[] num,int left,int right){
        if(left >= right)
            return;
        int index = Partition2(num,left,right);
        quickSortHelper(num,left,index-1); // 下标index的值已经排好，不需要再动
        quickSortHelper(num,index+1,right);
    }

    /**
     * 前后指针法
     * 在没找到大于key值前，pre永远紧跟cur，遇到大的两者之间机会拉开差距，中间差的肯定是连续的大于key的值，当再次遇到小于key的值时，交换两个下标对应的值就好了。
     * @param num
     * @param left
     * @param right
     * @return
     */
    public static int Partition(int[] num,int left,int right){
        int index = num[right];
        int cur = left;
        int pre = cur-1;
        while (cur < right){
            while (num[cur] < index && ++pre!=cur){
                int temp = num[pre];
                num[pre] = num[cur];
                num[cur] = temp;
            }
            cur++;
        }
        int temp = num[++pre];
        num[pre] = num[right];
        num[right] = temp;
        return pre;
    }

    /**
     * 左右指针法
     * @param num
     * @param left
     * @param right
     * @return
     */
    public static int Partition1(int[] num,int left,int right){
        int i = left;
        int j = right-1;
        while (i<j){
            while (i<j && num[i]<= num[right]){
                i++;
            }
            while (i<j && num[j]>=num[right]){
                j--;
            }
            int temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
        if(num[i] < num[right]){
            i++;
        }
        int temp = num[i];
        num[i] = num[right];
        num[right] = temp;
        return i;
    }

    /**
     * 挖坑法
     * @param num
     * @param left
     * @param right
     * @return
     */
    public static int Partition2(int[] num,int left, int right){
        int key = num[right];
        int i = left;
        int j = right;
        while (i<j){
            while (i<j && num[i]<=key){
                i++;
            }
            num[j] = num[i];
            while (i<j && num[j]>=key){
                j--;
            }
            num[i] = num[j];
        }
        num[j] = key;
        return j;
    }

    // 非比较算法

    /**
     * 计数排序
     * 计数排序算法没有用到元素间的比较，它利用元素的实际值来确定它们在输出数组中的位置。因此，计数排序算法不是一个基于比较的排序算法
     * 计数排序算法是一个稳定的排序算法。
     * @param num
     * @return
     */
    public static int[] countSort(int[] num){
        int[] b = new int[num.length];
        int min = num[0];
        int max = num[0];
        for (int i : num){
            if(i>max)
                max = i;
            if(i<min)
                min = i;
        }
        int k = max-min+1;
        int[] c = new int[k];
        for(int i = 0;i<num.length;i++){
            c[num[i]-min] += 1;
        }
        for (int i = 1;i<c.length;i++){
            c[i] += c[i-1];
        }
        for(int i = num.length-1;i>=0;i--){
            b[--c[num[i]-min]] = num[i];
        }
        return b;
    }

    public static void main(String[] args){
        int[] temp = new int[20];
        for(int i = 0;i<20;i++){
            temp[i] = (int)(Math.random()*100);
        }
        System.out.println(Arrays.toString(temp));
//        Sort.BubbleSort(temp);
//        Sort.CocktailSort(temp);
//        Sort.selectionSort(temp);
//        Sort.insertionSort(temp);
//        Sort.InsertionSortDichotomy(temp);
//        Sort.shellSort(temp);
//        Sort.mergeSort(temp,0,temp.length-1);
//        Sort.mergeSort(temp,temp.length);
//        Sort.heapSort(temp);
//        Sort.quickSort(temp);
        temp = countSort(temp);
        System.out.println(Arrays.toString(temp));
    }

}

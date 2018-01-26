package com.cutie.java_data_stru_algori.charpter2;

/**
 * Created by cutie on 2018/1/24.
 */
public class ArrayTest {
    public static void main(String[] args) {
        int[] array = {0,3,6,9,15,25,55,63,77,81,85,96};
        System.out.println(binarySearch(array, 0, array.length, 1));
        System.out.println(binarySearch1(array, 0));
        System.out.println(binarySearch2(array, 0));
    }

    private static int binarySearch(int[] array,int startIndex, int endIndex, int searchValue){
        int index = (startIndex + endIndex)/2;
        if (endIndex-startIndex <= 1 && array[startIndex]!=searchValue && array[endIndex]!=searchValue){
            return -1;
        }
        if (array[index] > searchValue){
            return binarySearch(array, startIndex, index, searchValue);
        }else if (array[index] < searchValue){
            return binarySearch(array, index, endIndex, searchValue);
        }else{
            return index;
        }
    }

    private static int binarySearch1(int[] array, int searchValue){
        int startIndex = 0,endIndex = array.length-1;
        int index;
        while(true){
            index = (startIndex+endIndex)/2;
            if(array[index] == searchValue){
                return index;
            }else if(startIndex>endIndex){
                return -1;
            }else{
                if(array[index]<searchValue){
                    startIndex++;
                }else if(array[index]>searchValue){
                    endIndex--;
                }
            }
        }
    }

    private static int binarySearch2(int[] array, int searchValue){
        for (int startIndex = 0,endIndex = array.length-1,index=0;true;index = (startIndex+endIndex)/2){
            if(array[index] == searchValue){
                return index;
            }else if(startIndex>endIndex){
                return -1;
            }else{
                if(array[index]<searchValue){
                    startIndex++;
                }else if(array[index]>searchValue){
                    endIndex--;
                }
            }
        }
    }


    private void init(){
        int[] array1 = new int[100];
        //只能连在一起写
        int array2[] = {0,3,6,9,5,2,4,8,5,25,63,24,81,75,96};
        int length = array1.length;
        array1[7] = 66;

    }
}

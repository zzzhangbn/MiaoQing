package com.ricemonitoring.util;

import com.ricemonitoring.pojo.crop_01_01_table_01_02;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 19:43
 * 快速排序算法
 */
@Component
public class quickSort {
    public void sort(List<crop_01_01_table_01_02> list, int low, int high){
        int i,j;
        Double index;
        //左坐标大于右坐标
        if(low>high){
            return;
        }
        i =low;
        j=high;
        crop_01_01_table_01_02 temp = list.get(i);
        index = Double.valueOf(temp.getArea_data());
        while (i<j){
            //右侧值大于基准值，j左移
            while (i<j&&Double.valueOf(list.get(j).getArea_data())>=index){
                j--;
            }
            if(i<j)
                list.set(i++,list.get(j));//用比基准小的记录替换低位记录
            while(i<j&&Double.valueOf(list.get(i).getArea_data())<index)
                i++;
            if(i<j)
                list.set(j--, list.get(i));//用比基准值大的值替换高位记录
        }
        list.set(i,temp);
        sort(list,low,i-1);
        sort(list,i+1,high);
    }
    public  void quickSort(List<crop_01_01_table_01_02> list){
        sort(list,0,list.size()-1);
    }
}

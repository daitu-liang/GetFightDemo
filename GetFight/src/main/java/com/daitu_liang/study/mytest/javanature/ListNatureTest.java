package com.daitu_liang.study.mytest.javanature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by leixiaoliang on 2017/1/17.
 */
public class ListNatureTest {


    /**ArrayList顺序存储和LinkedList链式的结构
     * 现foreach的一大好处，简单一行实现了四行的功能，
     * 使得代码简洁美观，另一大好处是相对于下标循环而言的，
     * foreach不必关心下标初始值和终止值及越界等，
     * 所以不易出错。Effective-Java中推荐使用此种写法遍历
     */
    //for each循环
    private void testNature1() {
        List<Integer> list = new ArrayList<Integer>();
        for (Integer j : list) {
            // use j
        }
    }

    // 下标递增循环，终止条件为和等于size()的临时变量比较判断
    private void testNature2() {
        List<Integer> list = new ArrayList<Integer>();
        int size = list.size();
        for (int j = 0; j < size; j++) {
            list.get(j);
        }
    }

    //显示调用集合迭代器
    private void testNature3() {
        List<Integer> list = new ArrayList<Integer>();
        for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) {
            iterator.next();
        }
    }

    //下标递增循环，终止条件为每次调用size()函数比较判断
    private void testNature4() {
        List<Integer> list = new ArrayList<Integer>();
        for (int j = 0; j < list.size(); j++) {
            list.get(j);
        }
    }

    /**
     * (1) 无论ArrayList还是LinkedList，遍历建议使用foreach，
     * 尤其是数据量较大时LinkedList避免使用get遍历。
     (2) List使用首选ArrayList。对于个别插入删除非常多的可以使用LinkedList。
     (3) 可能在遍历List循环内部需要使用到下标，
     这时综合考虑下是使用foreach和自增count还是get方式。
     http://www.trinea.cn/android/arraylist-linkedlist-loop-performance/
     */


    Map<String, String> map = new HashMap<String, String>();
    //for each map.entrySet()
    private void testMap1() {
        Map<String, String> map = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            entry.getKey();
            entry.getValue();
        }
    }

    //显示调用map.entrySet()的集合迭代器
    private void testMa2() {
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            entry.getKey();
            entry.getValue();
        }
    }
    // for each map.keySet()，再调用get获取
    private void testMap3() {
        Map<String, String> map = new HashMap<String, String>();
        for (String key : map.keySet()) {
            map.get(key);
        }
    }
    //for each map.entrySet()，用临时变量保存map.entrySet()
    private void testMap4() {
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            entry.getKey();
            entry.getValue();
        }
    }

    /**
     * a. HashMap的循环，如果既需要key也需要value，直接用;即可，foreach简洁易懂。

    Map<String, String> map = new HashMap<String, String>();
    for (Entry<String, String> entry : map.entrySet()) {
        entry.getKey();
        entry.getValue();
    }
     */


    /**
     * b. 如果只是遍历key而无需value的话，可以直接用

     Map<String, String> map = new HashMap<String, String>();
     for (String key : map.keySet()) {
     // key process
     }
     */
}

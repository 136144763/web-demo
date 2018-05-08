package com.example.webDemo;

import java.util.Iterator;

/**
 * Created by Administrator on 2018/5/7.
 */
public class ResizeArrayStack<Item> implements Iterable<Item> {

    public static void main(String[] args) {
        ResizeArrayStack resizeArrayStack=new ResizeArrayStack();
        resizeArrayStack.push(23333);
        System.out.println(resizeArrayStack.isEmpty());
    }

    private Item[] a = (Item[]) new Object[1];
    private int N = 0;

    public void push(Item item) {
        if (N >= a.length) {
            resize(2 * a.length);
        }
    }

    public Item pop() {
        Item item = a[--N];
        if (N <= a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    // 调整数组大小，使得栈具有伸缩性
    private void resize(int size) {
        Item[] tmp = (Item[]) new Object[size];
        for (int i = 0; i < N; i++) {
            tmp[i] = a[i];
        }
        a = tmp;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }


    @Override
    public Iterator<Item> iterator() {
        // 需要返回逆序遍历的迭代器
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }
    }
}

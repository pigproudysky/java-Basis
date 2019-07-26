package com.pig.basis.valueTransfer;

/**
 * @author bolong
 * @project-name java-Basis
 * @create 2019/7/25
 */
public class ValueTransfer {

    public static void change(int a) {
        System.out.println("传入的值a=" + a);
        a = 22;
        System.out.println("重新赋值后的值a=" + a);
    }

    public static void main(String[] args) {
        int a = 11;
        change(a);
        System.out.println("方法执行后的值a=" + a);
    }

}

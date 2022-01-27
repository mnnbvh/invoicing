package com.xu.bean;

/**
 * Author: Darryl
 * Desc:
 * File created at 2020-6-23
 */
public enum Role {
        //定义两个常量及其值
        Manager(0),
        Employee(1);


        private int value;

        Role(int value) {
                this.value = value;
            }

        public int value() {
            return this.value;
        }
}

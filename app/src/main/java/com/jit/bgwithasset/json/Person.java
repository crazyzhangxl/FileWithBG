package com.jit.bgwithasset.json;

import java.util.List;

/**
 * @author crazyZhangxl on 2018/12/28.
 * Describe: Json
 */
public class Person<T> {
    private String name;
    private int age;
    private List<DataBean> data;
    private T show;
    public String getName() {
        return name;
    }

    public T getShow() {
        return show;
    }

    public void setShow(T show) {
        this.show = show;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * year : 2018
         * money : 10000
         */

        private String year;
        private int money;

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }
    }
}

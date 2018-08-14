package com.how2java.tmall.util;

public class Page {
    private int start; //开始页数
    private int count; //每页显示个数
    private int total; //总个数
    private String param; //参数
    private static final int defaultCount = 5;
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public Page(){
        count = defaultCount;
    }
    public Page(int start, int count) {
        this();
        this.start = start;
        this.count = count;
    }
    public boolean isHasPreviouse(){
        if(start == 0) {
            return false;
        }
        return true;
    }
    public  boolean isHasNext() {
        if((start + count) > total)
            return false;
        return true;
    }
    public  int getLast() {
        int last;
        if(total % count == 0) {
            last = total - count > 0 ?total - count  : 0;
        }
        else {
            last = total - total % count;
        }
        return last;
    }
    public  int getTotalPage(){
        if(total % count > 0) {
            return total / count + 1;
        }
       return total / count == 0 ? 1 : total / count;
    }
    @Override
    public String toString() {
        return "Page [start=" + start + ", count=" + count + ", total=" + total + ", getStart()=" + getStart()
                + ", getCount()=" + getCount() + ", isHasPreviouse()=" + isHasPreviouse() + ", isHasNext()="
                + isHasNext() + ", getTotalPage()=" + getTotalPage() + ", getLast()=" + getLast() + "]";
    }
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}

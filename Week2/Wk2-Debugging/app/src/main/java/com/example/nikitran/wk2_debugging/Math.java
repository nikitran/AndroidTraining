package com.example.nikitran.wk2_debugging;

/**
 * Created by nikitran on 2/6/17.
 */

public class Math {
    private int val1;
    private int val2;

    public Math(int val1, int val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    public int getVal1() {
        return val1;
    }

    public void setVal1(int val1) {
        this.val1 = val1;
    }

    public int getVal2() {
        return val2;
    }

    public void setVal2(int val2) {
        this.val2 = val2;
    }

    public int add()
    {
        return val1 + val2;
    }

    public int multiply()
    {
        return val1 * val2;
    }
}

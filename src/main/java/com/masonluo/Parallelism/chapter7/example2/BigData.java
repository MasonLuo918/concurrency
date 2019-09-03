package com.masonluo.Parallelism.chapter7.example2;

public class BigData {

    private Integer[] bigData;

    public BigData(int size){
        bigData = new Integer[size];
        generate();
    }

    public Integer[] getBigData() {
        return bigData;
    }

    public void setBigData(Integer[] bigData) {
        this.bigData = bigData;
    }

    private void generate(){
        for(int i = 0; i < bigData.length; i++){
            bigData[i] = (int)(Math.random() * Integer.MAX_VALUE);
        }
    }
}

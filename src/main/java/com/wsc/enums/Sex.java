package com.wsc.enums;

/**
 * @author wsc
 * @date 2021/4/3
 */

public enum Sex {
    MAN(1,"男"),
    WOMAN(2,"女");

    private int value;
    private String desc;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    private Sex(int value,String desc){
        this.value = value;
        this.desc = desc;
    }

    /**
     * 根据value查出desc
     * @param value
     * @return
     */
    public String getDesc(int value) {
        for (Sex sex:Sex.values()){
            if (sex.getValue() == value){
                return sex.getDesc();
            }
        }
        return null;
    }
}

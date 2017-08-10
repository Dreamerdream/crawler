package cn.wanghaomiao.seimi.bean;

public enum  BusinessType {
    JD("0"),TAOBAO("1"),TIANMAO("2"),ALIBABA("3");
    private String val;
    BusinessType(String val){
        this.val = val;
    }
    public String val(){
        return this.val;
    }
}

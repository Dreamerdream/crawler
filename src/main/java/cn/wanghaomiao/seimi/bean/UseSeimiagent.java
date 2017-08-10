package cn.wanghaomiao.seimi.bean;

public enum  UseSeimiagent {
    USE("1"),NOTUSE("0");
    private String val;
    UseSeimiagent(String val){
        this.val = val;
    }
    public String val(){
        return this.val;
    }
}

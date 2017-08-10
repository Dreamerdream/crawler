package cn.wanghaomiao.seimi.bean;

public enum SeimiagentUsecookie {
    USE("1"),NOTUSE("0");
    private String val;
    SeimiagentUsecookie(String val){
        this.val = val;
    }
    public String val(){
        return this.val;
    }
}

package cn.wanghaomiao.seimi.bean;

public enum  Http {
    GET("0"),POST("1"),PUT("2");
    private String val;
    Http(String val){
        this.val = val;
    }
    public String val(){
        return this.val;
    }
}

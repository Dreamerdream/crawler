package cn.wanghaomiao.seimi.model;

public class User {
    private String name;
    private String address;
    private boolean useSeimiAgent = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isUseSeimiAgent() {
        return useSeimiAgent;
    }

    public void setUseSeimiAgent()
    {
        this.useSeimiAgent = true;
    }
}

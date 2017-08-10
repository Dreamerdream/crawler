package cn.wanghaomiao.seimi.model;

public class SeimiAgent {
    private Integer id;

    private String seimiagentHost;

    private Integer seimiagentPort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeimiagentHost() {
        return seimiagentHost;
    }

    public void setSeimiagentHost(String seimiagentHost) {
        this.seimiagentHost = seimiagentHost == null ? null : seimiagentHost.trim();
    }

    public Integer getSeimiagentPort() {
        return seimiagentPort;
    }

    public void setSeimiagentPort(Integer seimiagentPort) {
        this.seimiagentPort = seimiagentPort;
    }
}
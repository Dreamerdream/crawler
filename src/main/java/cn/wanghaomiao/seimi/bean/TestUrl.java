package cn.wanghaomiao.seimi.bean;

public class TestUrl {

    private String url;
    private String containerId;
    private String asyncLoad;
    private int pageSize;

    private TestB testB;
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getAsyncLoad() {
        return asyncLoad;
    }

    public void setAsyncLoad(String asyncLoad) {
        this.asyncLoad = asyncLoad;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public TestB getTestB() {
        return testB;
    }

    public void setTestB(TestB testB) {
        this.testB = testB;
    }
}

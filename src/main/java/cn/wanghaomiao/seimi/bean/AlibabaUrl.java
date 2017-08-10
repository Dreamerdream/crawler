package cn.wanghaomiao.seimi.bean;

import java.util.List;

public class AlibabaUrl {
    private String hasError;
    private String message;

    private TestUrl testUrls;

    public String getHasError() {
        return hasError;
    }

    public void setHasError(String hasError) {
        this.hasError = hasError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TestUrl getTestUrls() {
        return testUrls;
    }

    public void setTestUrls(TestUrl testUrls) {
        this.testUrls = testUrls;
    }
}

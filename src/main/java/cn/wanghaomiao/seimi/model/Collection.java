package cn.wanghaomiao.seimi.model;

public class Collection {
    private Integer id;

    private String allowRules;

    private String url;

    private Integer ruleId;

    private String httpMethod;

    private String params;

    private String stop;

    private Integer maxReqCount;

    private String skipDuplicateFilter;

    private String useSeimiagent;

    private String header;

    private Integer seimiagentRendertime;

    private String seimiagentScript;

    private String seimiagentUsecookie;

    private String seimiCookies;

    private String denyRules;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAllowRules() {
        return allowRules;
    }

    public void setAllowRules(String allowRules) {
        this.allowRules = allowRules == null ? null : allowRules.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod == null ? null : httpMethod.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop == null ? null : stop.trim();
    }

    public Integer getMaxReqCount() {
        return maxReqCount;
    }

    public void setMaxReqCount(Integer maxReqCount) {
        this.maxReqCount = maxReqCount;
    }

    public String getSkipDuplicateFilter() {
        return skipDuplicateFilter;
    }

    public void setSkipDuplicateFilter(String skipDuplicateFilter) {
        this.skipDuplicateFilter = skipDuplicateFilter == null ? null : skipDuplicateFilter.trim();
    }

    public String getUseSeimiagent() {
        return useSeimiagent;
    }

    public void setUseSeimiagent(String useSeimiagent) {
        this.useSeimiagent = useSeimiagent == null ? null : useSeimiagent.trim();
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header == null ? null : header.trim();
    }

    public Integer getSeimiagentRendertime() {
        return seimiagentRendertime;
    }

    public void setSeimiagentRendertime(Integer seimiagentRendertime) {
        this.seimiagentRendertime = seimiagentRendertime;
    }

    public String getSeimiagentScript() {
        return seimiagentScript;
    }

    public void setSeimiagentScript(String seimiagentScript) {
        this.seimiagentScript = seimiagentScript == null ? null : seimiagentScript.trim();
    }

    public String getSeimiagentUsecookie() {
        return seimiagentUsecookie;
    }

    public void setSeimiagentUsecookie(String seimiagentUsecookie) {
        this.seimiagentUsecookie = seimiagentUsecookie == null ? null : seimiagentUsecookie.trim();
    }

    public String getSeimiCookies() {
        return seimiCookies;
    }

    public void setSeimiCookies(String seimiCookies) {
        this.seimiCookies = seimiCookies == null ? null : seimiCookies.trim();
    }

    public String getDenyRules() {
        return denyRules;
    }

    public void setDenyRules(String denyRules) {
        this.denyRules = denyRules == null ? null : denyRules.trim();
    }
}
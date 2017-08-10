package cn.wanghaomiao.seimi.model;

import java.util.ArrayList;
import java.util.List;

public class CollectionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CollectionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAllowRulesIsNull() {
            addCriterion("allow_rules is null");
            return (Criteria) this;
        }

        public Criteria andAllowRulesIsNotNull() {
            addCriterion("allow_rules is not null");
            return (Criteria) this;
        }

        public Criteria andAllowRulesEqualTo(String value) {
            addCriterion("allow_rules =", value, "allowRules");
            return (Criteria) this;
        }

        public Criteria andAllowRulesNotEqualTo(String value) {
            addCriterion("allow_rules <>", value, "allowRules");
            return (Criteria) this;
        }

        public Criteria andAllowRulesGreaterThan(String value) {
            addCriterion("allow_rules >", value, "allowRules");
            return (Criteria) this;
        }

        public Criteria andAllowRulesGreaterThanOrEqualTo(String value) {
            addCriterion("allow_rules >=", value, "allowRules");
            return (Criteria) this;
        }

        public Criteria andAllowRulesLessThan(String value) {
            addCriterion("allow_rules <", value, "allowRules");
            return (Criteria) this;
        }

        public Criteria andAllowRulesLessThanOrEqualTo(String value) {
            addCriterion("allow_rules <=", value, "allowRules");
            return (Criteria) this;
        }

        public Criteria andAllowRulesLike(String value) {
            addCriterion("allow_rules like", value, "allowRules");
            return (Criteria) this;
        }

        public Criteria andAllowRulesNotLike(String value) {
            addCriterion("allow_rules not like", value, "allowRules");
            return (Criteria) this;
        }

        public Criteria andAllowRulesIn(List<String> values) {
            addCriterion("allow_rules in", values, "allowRules");
            return (Criteria) this;
        }

        public Criteria andAllowRulesNotIn(List<String> values) {
            addCriterion("allow_rules not in", values, "allowRules");
            return (Criteria) this;
        }

        public Criteria andAllowRulesBetween(String value1, String value2) {
            addCriterion("allow_rules between", value1, value2, "allowRules");
            return (Criteria) this;
        }

        public Criteria andAllowRulesNotBetween(String value1, String value2) {
            addCriterion("allow_rules not between", value1, value2, "allowRules");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andRuleIdIsNull() {
            addCriterion("rule_id is null");
            return (Criteria) this;
        }

        public Criteria andRuleIdIsNotNull() {
            addCriterion("rule_id is not null");
            return (Criteria) this;
        }

        public Criteria andRuleIdEqualTo(Integer value) {
            addCriterion("rule_id =", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdNotEqualTo(Integer value) {
            addCriterion("rule_id <>", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdGreaterThan(Integer value) {
            addCriterion("rule_id >", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("rule_id >=", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdLessThan(Integer value) {
            addCriterion("rule_id <", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdLessThanOrEqualTo(Integer value) {
            addCriterion("rule_id <=", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdIn(List<Integer> values) {
            addCriterion("rule_id in", values, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdNotIn(List<Integer> values) {
            addCriterion("rule_id not in", values, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdBetween(Integer value1, Integer value2) {
            addCriterion("rule_id between", value1, value2, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("rule_id not between", value1, value2, "ruleId");
            return (Criteria) this;
        }

        public Criteria andHttpMethodIsNull() {
            addCriterion("http_method is null");
            return (Criteria) this;
        }

        public Criteria andHttpMethodIsNotNull() {
            addCriterion("http_method is not null");
            return (Criteria) this;
        }

        public Criteria andHttpMethodEqualTo(String value) {
            addCriterion("http_method =", value, "httpMethod");
            return (Criteria) this;
        }

        public Criteria andHttpMethodNotEqualTo(String value) {
            addCriterion("http_method <>", value, "httpMethod");
            return (Criteria) this;
        }

        public Criteria andHttpMethodGreaterThan(String value) {
            addCriterion("http_method >", value, "httpMethod");
            return (Criteria) this;
        }

        public Criteria andHttpMethodGreaterThanOrEqualTo(String value) {
            addCriterion("http_method >=", value, "httpMethod");
            return (Criteria) this;
        }

        public Criteria andHttpMethodLessThan(String value) {
            addCriterion("http_method <", value, "httpMethod");
            return (Criteria) this;
        }

        public Criteria andHttpMethodLessThanOrEqualTo(String value) {
            addCriterion("http_method <=", value, "httpMethod");
            return (Criteria) this;
        }

        public Criteria andHttpMethodLike(String value) {
            addCriterion("http_method like", value, "httpMethod");
            return (Criteria) this;
        }

        public Criteria andHttpMethodNotLike(String value) {
            addCriterion("http_method not like", value, "httpMethod");
            return (Criteria) this;
        }

        public Criteria andHttpMethodIn(List<String> values) {
            addCriterion("http_method in", values, "httpMethod");
            return (Criteria) this;
        }

        public Criteria andHttpMethodNotIn(List<String> values) {
            addCriterion("http_method not in", values, "httpMethod");
            return (Criteria) this;
        }

        public Criteria andHttpMethodBetween(String value1, String value2) {
            addCriterion("http_method between", value1, value2, "httpMethod");
            return (Criteria) this;
        }

        public Criteria andHttpMethodNotBetween(String value1, String value2) {
            addCriterion("http_method not between", value1, value2, "httpMethod");
            return (Criteria) this;
        }

        public Criteria andParamsIsNull() {
            addCriterion("params is null");
            return (Criteria) this;
        }

        public Criteria andParamsIsNotNull() {
            addCriterion("params is not null");
            return (Criteria) this;
        }

        public Criteria andParamsEqualTo(String value) {
            addCriterion("params =", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotEqualTo(String value) {
            addCriterion("params <>", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsGreaterThan(String value) {
            addCriterion("params >", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsGreaterThanOrEqualTo(String value) {
            addCriterion("params >=", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsLessThan(String value) {
            addCriterion("params <", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsLessThanOrEqualTo(String value) {
            addCriterion("params <=", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsLike(String value) {
            addCriterion("params like", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotLike(String value) {
            addCriterion("params not like", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsIn(List<String> values) {
            addCriterion("params in", values, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotIn(List<String> values) {
            addCriterion("params not in", values, "params");
            return (Criteria) this;
        }

        public Criteria andParamsBetween(String value1, String value2) {
            addCriterion("params between", value1, value2, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotBetween(String value1, String value2) {
            addCriterion("params not between", value1, value2, "params");
            return (Criteria) this;
        }

        public Criteria andStopIsNull() {
            addCriterion("stop is null");
            return (Criteria) this;
        }

        public Criteria andStopIsNotNull() {
            addCriterion("stop is not null");
            return (Criteria) this;
        }

        public Criteria andStopEqualTo(String value) {
            addCriterion("stop =", value, "stop");
            return (Criteria) this;
        }

        public Criteria andStopNotEqualTo(String value) {
            addCriterion("stop <>", value, "stop");
            return (Criteria) this;
        }

        public Criteria andStopGreaterThan(String value) {
            addCriterion("stop >", value, "stop");
            return (Criteria) this;
        }

        public Criteria andStopGreaterThanOrEqualTo(String value) {
            addCriterion("stop >=", value, "stop");
            return (Criteria) this;
        }

        public Criteria andStopLessThan(String value) {
            addCriterion("stop <", value, "stop");
            return (Criteria) this;
        }

        public Criteria andStopLessThanOrEqualTo(String value) {
            addCriterion("stop <=", value, "stop");
            return (Criteria) this;
        }

        public Criteria andStopLike(String value) {
            addCriterion("stop like", value, "stop");
            return (Criteria) this;
        }

        public Criteria andStopNotLike(String value) {
            addCriterion("stop not like", value, "stop");
            return (Criteria) this;
        }

        public Criteria andStopIn(List<String> values) {
            addCriterion("stop in", values, "stop");
            return (Criteria) this;
        }

        public Criteria andStopNotIn(List<String> values) {
            addCriterion("stop not in", values, "stop");
            return (Criteria) this;
        }

        public Criteria andStopBetween(String value1, String value2) {
            addCriterion("stop between", value1, value2, "stop");
            return (Criteria) this;
        }

        public Criteria andStopNotBetween(String value1, String value2) {
            addCriterion("stop not between", value1, value2, "stop");
            return (Criteria) this;
        }

        public Criteria andMaxReqCountIsNull() {
            addCriterion("max_req_count is null");
            return (Criteria) this;
        }

        public Criteria andMaxReqCountIsNotNull() {
            addCriterion("max_req_count is not null");
            return (Criteria) this;
        }

        public Criteria andMaxReqCountEqualTo(Integer value) {
            addCriterion("max_req_count =", value, "maxReqCount");
            return (Criteria) this;
        }

        public Criteria andMaxReqCountNotEqualTo(Integer value) {
            addCriterion("max_req_count <>", value, "maxReqCount");
            return (Criteria) this;
        }

        public Criteria andMaxReqCountGreaterThan(Integer value) {
            addCriterion("max_req_count >", value, "maxReqCount");
            return (Criteria) this;
        }

        public Criteria andMaxReqCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_req_count >=", value, "maxReqCount");
            return (Criteria) this;
        }

        public Criteria andMaxReqCountLessThan(Integer value) {
            addCriterion("max_req_count <", value, "maxReqCount");
            return (Criteria) this;
        }

        public Criteria andMaxReqCountLessThanOrEqualTo(Integer value) {
            addCriterion("max_req_count <=", value, "maxReqCount");
            return (Criteria) this;
        }

        public Criteria andMaxReqCountIn(List<Integer> values) {
            addCriterion("max_req_count in", values, "maxReqCount");
            return (Criteria) this;
        }

        public Criteria andMaxReqCountNotIn(List<Integer> values) {
            addCriterion("max_req_count not in", values, "maxReqCount");
            return (Criteria) this;
        }

        public Criteria andMaxReqCountBetween(Integer value1, Integer value2) {
            addCriterion("max_req_count between", value1, value2, "maxReqCount");
            return (Criteria) this;
        }

        public Criteria andMaxReqCountNotBetween(Integer value1, Integer value2) {
            addCriterion("max_req_count not between", value1, value2, "maxReqCount");
            return (Criteria) this;
        }

        public Criteria andSkipDuplicateFilterIsNull() {
            addCriterion("skip_duplicate_filter is null");
            return (Criteria) this;
        }

        public Criteria andSkipDuplicateFilterIsNotNull() {
            addCriterion("skip_duplicate_filter is not null");
            return (Criteria) this;
        }

        public Criteria andSkipDuplicateFilterEqualTo(String value) {
            addCriterion("skip_duplicate_filter =", value, "skipDuplicateFilter");
            return (Criteria) this;
        }

        public Criteria andSkipDuplicateFilterNotEqualTo(String value) {
            addCriterion("skip_duplicate_filter <>", value, "skipDuplicateFilter");
            return (Criteria) this;
        }

        public Criteria andSkipDuplicateFilterGreaterThan(String value) {
            addCriterion("skip_duplicate_filter >", value, "skipDuplicateFilter");
            return (Criteria) this;
        }

        public Criteria andSkipDuplicateFilterGreaterThanOrEqualTo(String value) {
            addCriterion("skip_duplicate_filter >=", value, "skipDuplicateFilter");
            return (Criteria) this;
        }

        public Criteria andSkipDuplicateFilterLessThan(String value) {
            addCriterion("skip_duplicate_filter <", value, "skipDuplicateFilter");
            return (Criteria) this;
        }

        public Criteria andSkipDuplicateFilterLessThanOrEqualTo(String value) {
            addCriterion("skip_duplicate_filter <=", value, "skipDuplicateFilter");
            return (Criteria) this;
        }

        public Criteria andSkipDuplicateFilterLike(String value) {
            addCriterion("skip_duplicate_filter like", value, "skipDuplicateFilter");
            return (Criteria) this;
        }

        public Criteria andSkipDuplicateFilterNotLike(String value) {
            addCriterion("skip_duplicate_filter not like", value, "skipDuplicateFilter");
            return (Criteria) this;
        }

        public Criteria andSkipDuplicateFilterIn(List<String> values) {
            addCriterion("skip_duplicate_filter in", values, "skipDuplicateFilter");
            return (Criteria) this;
        }

        public Criteria andSkipDuplicateFilterNotIn(List<String> values) {
            addCriterion("skip_duplicate_filter not in", values, "skipDuplicateFilter");
            return (Criteria) this;
        }

        public Criteria andSkipDuplicateFilterBetween(String value1, String value2) {
            addCriterion("skip_duplicate_filter between", value1, value2, "skipDuplicateFilter");
            return (Criteria) this;
        }

        public Criteria andSkipDuplicateFilterNotBetween(String value1, String value2) {
            addCriterion("skip_duplicate_filter not between", value1, value2, "skipDuplicateFilter");
            return (Criteria) this;
        }

        public Criteria andUseSeimiagentIsNull() {
            addCriterion("use_seimiagent is null");
            return (Criteria) this;
        }

        public Criteria andUseSeimiagentIsNotNull() {
            addCriterion("use_seimiagent is not null");
            return (Criteria) this;
        }

        public Criteria andUseSeimiagentEqualTo(String value) {
            addCriterion("use_seimiagent =", value, "useSeimiagent");
            return (Criteria) this;
        }

        public Criteria andUseSeimiagentNotEqualTo(String value) {
            addCriterion("use_seimiagent <>", value, "useSeimiagent");
            return (Criteria) this;
        }

        public Criteria andUseSeimiagentGreaterThan(String value) {
            addCriterion("use_seimiagent >", value, "useSeimiagent");
            return (Criteria) this;
        }

        public Criteria andUseSeimiagentGreaterThanOrEqualTo(String value) {
            addCriterion("use_seimiagent >=", value, "useSeimiagent");
            return (Criteria) this;
        }

        public Criteria andUseSeimiagentLessThan(String value) {
            addCriterion("use_seimiagent <", value, "useSeimiagent");
            return (Criteria) this;
        }

        public Criteria andUseSeimiagentLessThanOrEqualTo(String value) {
            addCriterion("use_seimiagent <=", value, "useSeimiagent");
            return (Criteria) this;
        }

        public Criteria andUseSeimiagentLike(String value) {
            addCriterion("use_seimiagent like", value, "useSeimiagent");
            return (Criteria) this;
        }

        public Criteria andUseSeimiagentNotLike(String value) {
            addCriterion("use_seimiagent not like", value, "useSeimiagent");
            return (Criteria) this;
        }

        public Criteria andUseSeimiagentIn(List<String> values) {
            addCriterion("use_seimiagent in", values, "useSeimiagent");
            return (Criteria) this;
        }

        public Criteria andUseSeimiagentNotIn(List<String> values) {
            addCriterion("use_seimiagent not in", values, "useSeimiagent");
            return (Criteria) this;
        }

        public Criteria andUseSeimiagentBetween(String value1, String value2) {
            addCriterion("use_seimiagent between", value1, value2, "useSeimiagent");
            return (Criteria) this;
        }

        public Criteria andUseSeimiagentNotBetween(String value1, String value2) {
            addCriterion("use_seimiagent not between", value1, value2, "useSeimiagent");
            return (Criteria) this;
        }

        public Criteria andHeaderIsNull() {
            addCriterion("header is null");
            return (Criteria) this;
        }

        public Criteria andHeaderIsNotNull() {
            addCriterion("header is not null");
            return (Criteria) this;
        }

        public Criteria andHeaderEqualTo(String value) {
            addCriterion("header =", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderNotEqualTo(String value) {
            addCriterion("header <>", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderGreaterThan(String value) {
            addCriterion("header >", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderGreaterThanOrEqualTo(String value) {
            addCriterion("header >=", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderLessThan(String value) {
            addCriterion("header <", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderLessThanOrEqualTo(String value) {
            addCriterion("header <=", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderLike(String value) {
            addCriterion("header like", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderNotLike(String value) {
            addCriterion("header not like", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderIn(List<String> values) {
            addCriterion("header in", values, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderNotIn(List<String> values) {
            addCriterion("header not in", values, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderBetween(String value1, String value2) {
            addCriterion("header between", value1, value2, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderNotBetween(String value1, String value2) {
            addCriterion("header not between", value1, value2, "header");
            return (Criteria) this;
        }

        public Criteria andSeimiagentRendertimeIsNull() {
            addCriterion("seimiagent_rendertime is null");
            return (Criteria) this;
        }

        public Criteria andSeimiagentRendertimeIsNotNull() {
            addCriterion("seimiagent_rendertime is not null");
            return (Criteria) this;
        }

        public Criteria andSeimiagentRendertimeEqualTo(Integer value) {
            addCriterion("seimiagent_rendertime =", value, "seimiagentRendertime");
            return (Criteria) this;
        }

        public Criteria andSeimiagentRendertimeNotEqualTo(Integer value) {
            addCriterion("seimiagent_rendertime <>", value, "seimiagentRendertime");
            return (Criteria) this;
        }

        public Criteria andSeimiagentRendertimeGreaterThan(Integer value) {
            addCriterion("seimiagent_rendertime >", value, "seimiagentRendertime");
            return (Criteria) this;
        }

        public Criteria andSeimiagentRendertimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("seimiagent_rendertime >=", value, "seimiagentRendertime");
            return (Criteria) this;
        }

        public Criteria andSeimiagentRendertimeLessThan(Integer value) {
            addCriterion("seimiagent_rendertime <", value, "seimiagentRendertime");
            return (Criteria) this;
        }

        public Criteria andSeimiagentRendertimeLessThanOrEqualTo(Integer value) {
            addCriterion("seimiagent_rendertime <=", value, "seimiagentRendertime");
            return (Criteria) this;
        }

        public Criteria andSeimiagentRendertimeIn(List<Integer> values) {
            addCriterion("seimiagent_rendertime in", values, "seimiagentRendertime");
            return (Criteria) this;
        }

        public Criteria andSeimiagentRendertimeNotIn(List<Integer> values) {
            addCriterion("seimiagent_rendertime not in", values, "seimiagentRendertime");
            return (Criteria) this;
        }

        public Criteria andSeimiagentRendertimeBetween(Integer value1, Integer value2) {
            addCriterion("seimiagent_rendertime between", value1, value2, "seimiagentRendertime");
            return (Criteria) this;
        }

        public Criteria andSeimiagentRendertimeNotBetween(Integer value1, Integer value2) {
            addCriterion("seimiagent_rendertime not between", value1, value2, "seimiagentRendertime");
            return (Criteria) this;
        }

        public Criteria andSeimiagentScriptIsNull() {
            addCriterion("seimiagent_script is null");
            return (Criteria) this;
        }

        public Criteria andSeimiagentScriptIsNotNull() {
            addCriterion("seimiagent_script is not null");
            return (Criteria) this;
        }

        public Criteria andSeimiagentScriptEqualTo(String value) {
            addCriterion("seimiagent_script =", value, "seimiagentScript");
            return (Criteria) this;
        }

        public Criteria andSeimiagentScriptNotEqualTo(String value) {
            addCriterion("seimiagent_script <>", value, "seimiagentScript");
            return (Criteria) this;
        }

        public Criteria andSeimiagentScriptGreaterThan(String value) {
            addCriterion("seimiagent_script >", value, "seimiagentScript");
            return (Criteria) this;
        }

        public Criteria andSeimiagentScriptGreaterThanOrEqualTo(String value) {
            addCriterion("seimiagent_script >=", value, "seimiagentScript");
            return (Criteria) this;
        }

        public Criteria andSeimiagentScriptLessThan(String value) {
            addCriterion("seimiagent_script <", value, "seimiagentScript");
            return (Criteria) this;
        }

        public Criteria andSeimiagentScriptLessThanOrEqualTo(String value) {
            addCriterion("seimiagent_script <=", value, "seimiagentScript");
            return (Criteria) this;
        }

        public Criteria andSeimiagentScriptLike(String value) {
            addCriterion("seimiagent_script like", value, "seimiagentScript");
            return (Criteria) this;
        }

        public Criteria andSeimiagentScriptNotLike(String value) {
            addCriterion("seimiagent_script not like", value, "seimiagentScript");
            return (Criteria) this;
        }

        public Criteria andSeimiagentScriptIn(List<String> values) {
            addCriterion("seimiagent_script in", values, "seimiagentScript");
            return (Criteria) this;
        }

        public Criteria andSeimiagentScriptNotIn(List<String> values) {
            addCriterion("seimiagent_script not in", values, "seimiagentScript");
            return (Criteria) this;
        }

        public Criteria andSeimiagentScriptBetween(String value1, String value2) {
            addCriterion("seimiagent_script between", value1, value2, "seimiagentScript");
            return (Criteria) this;
        }

        public Criteria andSeimiagentScriptNotBetween(String value1, String value2) {
            addCriterion("seimiagent_script not between", value1, value2, "seimiagentScript");
            return (Criteria) this;
        }

        public Criteria andSeimiagentUsecookieIsNull() {
            addCriterion("seimiagent_usecookie is null");
            return (Criteria) this;
        }

        public Criteria andSeimiagentUsecookieIsNotNull() {
            addCriterion("seimiagent_usecookie is not null");
            return (Criteria) this;
        }

        public Criteria andSeimiagentUsecookieEqualTo(String value) {
            addCriterion("seimiagent_usecookie =", value, "seimiagentUsecookie");
            return (Criteria) this;
        }

        public Criteria andSeimiagentUsecookieNotEqualTo(String value) {
            addCriterion("seimiagent_usecookie <>", value, "seimiagentUsecookie");
            return (Criteria) this;
        }

        public Criteria andSeimiagentUsecookieGreaterThan(String value) {
            addCriterion("seimiagent_usecookie >", value, "seimiagentUsecookie");
            return (Criteria) this;
        }

        public Criteria andSeimiagentUsecookieGreaterThanOrEqualTo(String value) {
            addCriterion("seimiagent_usecookie >=", value, "seimiagentUsecookie");
            return (Criteria) this;
        }

        public Criteria andSeimiagentUsecookieLessThan(String value) {
            addCriterion("seimiagent_usecookie <", value, "seimiagentUsecookie");
            return (Criteria) this;
        }

        public Criteria andSeimiagentUsecookieLessThanOrEqualTo(String value) {
            addCriterion("seimiagent_usecookie <=", value, "seimiagentUsecookie");
            return (Criteria) this;
        }

        public Criteria andSeimiagentUsecookieLike(String value) {
            addCriterion("seimiagent_usecookie like", value, "seimiagentUsecookie");
            return (Criteria) this;
        }

        public Criteria andSeimiagentUsecookieNotLike(String value) {
            addCriterion("seimiagent_usecookie not like", value, "seimiagentUsecookie");
            return (Criteria) this;
        }

        public Criteria andSeimiagentUsecookieIn(List<String> values) {
            addCriterion("seimiagent_usecookie in", values, "seimiagentUsecookie");
            return (Criteria) this;
        }

        public Criteria andSeimiagentUsecookieNotIn(List<String> values) {
            addCriterion("seimiagent_usecookie not in", values, "seimiagentUsecookie");
            return (Criteria) this;
        }

        public Criteria andSeimiagentUsecookieBetween(String value1, String value2) {
            addCriterion("seimiagent_usecookie between", value1, value2, "seimiagentUsecookie");
            return (Criteria) this;
        }

        public Criteria andSeimiagentUsecookieNotBetween(String value1, String value2) {
            addCriterion("seimiagent_usecookie not between", value1, value2, "seimiagentUsecookie");
            return (Criteria) this;
        }

        public Criteria andSeimiCookiesIsNull() {
            addCriterion("seimi_cookies is null");
            return (Criteria) this;
        }

        public Criteria andSeimiCookiesIsNotNull() {
            addCriterion("seimi_cookies is not null");
            return (Criteria) this;
        }

        public Criteria andSeimiCookiesEqualTo(String value) {
            addCriterion("seimi_cookies =", value, "seimiCookies");
            return (Criteria) this;
        }

        public Criteria andSeimiCookiesNotEqualTo(String value) {
            addCriterion("seimi_cookies <>", value, "seimiCookies");
            return (Criteria) this;
        }

        public Criteria andSeimiCookiesGreaterThan(String value) {
            addCriterion("seimi_cookies >", value, "seimiCookies");
            return (Criteria) this;
        }

        public Criteria andSeimiCookiesGreaterThanOrEqualTo(String value) {
            addCriterion("seimi_cookies >=", value, "seimiCookies");
            return (Criteria) this;
        }

        public Criteria andSeimiCookiesLessThan(String value) {
            addCriterion("seimi_cookies <", value, "seimiCookies");
            return (Criteria) this;
        }

        public Criteria andSeimiCookiesLessThanOrEqualTo(String value) {
            addCriterion("seimi_cookies <=", value, "seimiCookies");
            return (Criteria) this;
        }

        public Criteria andSeimiCookiesLike(String value) {
            addCriterion("seimi_cookies like", value, "seimiCookies");
            return (Criteria) this;
        }

        public Criteria andSeimiCookiesNotLike(String value) {
            addCriterion("seimi_cookies not like", value, "seimiCookies");
            return (Criteria) this;
        }

        public Criteria andSeimiCookiesIn(List<String> values) {
            addCriterion("seimi_cookies in", values, "seimiCookies");
            return (Criteria) this;
        }

        public Criteria andSeimiCookiesNotIn(List<String> values) {
            addCriterion("seimi_cookies not in", values, "seimiCookies");
            return (Criteria) this;
        }

        public Criteria andSeimiCookiesBetween(String value1, String value2) {
            addCriterion("seimi_cookies between", value1, value2, "seimiCookies");
            return (Criteria) this;
        }

        public Criteria andSeimiCookiesNotBetween(String value1, String value2) {
            addCriterion("seimi_cookies not between", value1, value2, "seimiCookies");
            return (Criteria) this;
        }

        public Criteria andDenyRulesIsNull() {
            addCriterion("deny_rules is null");
            return (Criteria) this;
        }

        public Criteria andDenyRulesIsNotNull() {
            addCriterion("deny_rules is not null");
            return (Criteria) this;
        }

        public Criteria andDenyRulesEqualTo(String value) {
            addCriterion("deny_rules =", value, "denyRules");
            return (Criteria) this;
        }

        public Criteria andDenyRulesNotEqualTo(String value) {
            addCriterion("deny_rules <>", value, "denyRules");
            return (Criteria) this;
        }

        public Criteria andDenyRulesGreaterThan(String value) {
            addCriterion("deny_rules >", value, "denyRules");
            return (Criteria) this;
        }

        public Criteria andDenyRulesGreaterThanOrEqualTo(String value) {
            addCriterion("deny_rules >=", value, "denyRules");
            return (Criteria) this;
        }

        public Criteria andDenyRulesLessThan(String value) {
            addCriterion("deny_rules <", value, "denyRules");
            return (Criteria) this;
        }

        public Criteria andDenyRulesLessThanOrEqualTo(String value) {
            addCriterion("deny_rules <=", value, "denyRules");
            return (Criteria) this;
        }

        public Criteria andDenyRulesLike(String value) {
            addCriterion("deny_rules like", value, "denyRules");
            return (Criteria) this;
        }

        public Criteria andDenyRulesNotLike(String value) {
            addCriterion("deny_rules not like", value, "denyRules");
            return (Criteria) this;
        }

        public Criteria andDenyRulesIn(List<String> values) {
            addCriterion("deny_rules in", values, "denyRules");
            return (Criteria) this;
        }

        public Criteria andDenyRulesNotIn(List<String> values) {
            addCriterion("deny_rules not in", values, "denyRules");
            return (Criteria) this;
        }

        public Criteria andDenyRulesBetween(String value1, String value2) {
            addCriterion("deny_rules between", value1, value2, "denyRules");
            return (Criteria) this;
        }

        public Criteria andDenyRulesNotBetween(String value1, String value2) {
            addCriterion("deny_rules not between", value1, value2, "denyRules");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
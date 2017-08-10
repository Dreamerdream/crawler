package cn.wanghaomiao.seimi.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.http.HttpMethod;
import cn.wanghaomiao.seimi.http.SeimiAgentContentType;
import cn.wanghaomiao.seimi.mapper.*;
import cn.wanghaomiao.seimi.model.Collection;
import cn.wanghaomiao.seimi.model.Rule;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import cn.wanghaomiao.seimi.util.RequestUtil;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/7/13.
 */
@Crawler(name = "elemecrawler")
public class ElemeCrawlers extends BaseSeimiCrawler {
    private Collection collection;
    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private ProxyMapper proxyMapper;
    @Autowired
    private RuleMapper ruleMapper;
    @Autowired
    private SeimiAgentMapper seimiAgentMapper;
    @Autowired
    private UserAgentMapper userAgentMapper;
    private ConcurrentHashMap map = new ConcurrentHashMap();

    @Override
    public String[] startUrls() {
        return null;
    }

    @Override
    public void start(Response response) {

    }

    @Override
    public List<Request> startRequests() {
        map.clear();
        List<Request> requests = new ArrayList<>();
        collection = collectionMapper.selectByPrimaryKey(2);
        Rule rule = ruleMapper.selectByPrimaryKey(collection.getRuleId());
        Request request = null;
        if (collection != null) {
            ruleMapper.selectByPrimaryKey(collection.getRuleId());
            request = RequestUtil.getRequest(collection, rule, "eleme");
            if (!map.containsKey(request)) {
                map.put(request, rule);
            }
            requests.add(request);
        }
        return requests;
    }


    public void eleme(Response response) throws XpathSyntaxErrorException {
        JXDocument doc = response.document();
        Rule rule = (Rule) map.get(response.getRequest());
        String parentRule = rule.getRule();
        Rule childRule = ruleMapper.selectByPrimaryKey(rule.getChildId());


        if (parentRule.contains("@href") || parentRule.contains("@src")) {

            if (parentRule.contains("/img/@src")){
                List<Object> result = doc.sel(parentRule);
                if (result != null && result.size() != 0) {
                    for (Object o : result) {
                        logger.info("结果-----" +o.toString());
                    }
                }
                return;
            }
            List<Object> sel = doc.sel(parentRule);
            if (sel != null && sel.size() != 0) {
              //  for (Object o : sel) {
                    String url = sel.get(0).toString();
                    if (!url.contains("http://") && !url.contains("https://")) {
                        url = "https://www.ele.me" + url;
                    }
                    Request request = new Request();
                    request.setUrl(url);
                    request.setUseSeimiAgent(true);
                    request.setSeimiAgentUseCookie(true);
                    request.setSeimiAgentRenderTime(6000);
                    request.setSeimiAgentContentType(SeimiAgentContentType.HTML);
                    request.setCallBack("eleme");
                    request.setSeimiAgentScript(collection.getSeimiagentScript());
                    if (childRule != null){
                        map.put(request, childRule);
                    }
                    push(request);
              //  }
            } else {
                List<Object> result = doc.sel(parentRule);
                if (result != null && result.size() != 0) {
                    for (Object o : result) {
                        logger.info("结果-----" +o.toString());
                    }
                }
            }

        }

    }

    @Override
    public String seimiAgentHost() {
        return "192.168.111.129";
    }

    @Override
    public int seimiAgentPort() {
        return 8000;
    }

}

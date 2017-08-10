package cn.wanghaomiao.seimi.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.http.HttpMethod;

import cn.wanghaomiao.seimi.mapper.*;
import cn.wanghaomiao.seimi.model.*;
import cn.wanghaomiao.seimi.model.Collection;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2017/7/10.
 */
@Crawler(name = "mycrawler", delay = 6000)
public class MyCrawlers extends BaseSeimiCrawler {
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
    private List<Rule> rules = new ArrayList<>(); //一个爬取的所有规则

    //  private Map<String, String> map = new HashMap<>();
    private boolean flag = true;

    private ConcurrentHashMap map = new ConcurrentHashMap();

    @Override
    public String[] startUrls() {
        return null;
    }


    @Override
    public List<Request> startRequests() {
        List<Request> requests = new LinkedList<>();
        collection = collectionMapper.selectByPrimaryKey(2);

        //第一个Rule
        //Rule rule = ruleMapper.selectByPrimaryKey(collection.getRuleId());

        // map.put(collection.getRuleId()+rule.getChildId(),rule.getRule());
        //得到所有子规则
        // Integer ruleId = collection.getRuleId();
        //   getRule(ruleId);

        //      getRule(collection.getRuleId());


        if (collection != null) {
            Rule rule = ruleMapper.selectByPrimaryKey(collection.getRuleId());
            Request request = new Request();
            request.setUrl(collection.getUrl());
            request.setMaxReqCount(collection.getMaxReqCount());
            request.setSkipDuplicateFilter("0".equals(collection.getSkipDuplicateFilter()) ? false : true);
            if ("0".equals(collection.getHttpMethod())) {
                //get请求
                request.setHttpMethod(HttpMethod.GET);

            } else if ("1".equals(collection.getHttpMethod())) {
                //post请求
                request.setHttpMethod(HttpMethod.POST);
                //将string转为map
                Map<String, String> map = new HashMap();
                request.setParams(map);
            } else {
                // put 请求
            }

            if ("1".equals(collection.getUseSeimiagent())) {
                request.setUseSeimiAgent(true);
                request.setSeimiAgentUseCookie(true);
                request.setSeimiAgentRenderTime(collection.getSeimiagentRendertime().longValue());
                //设置脚本
                // request.setSeimiAgentScript(collection.getSeimiagentScript());
            }
            //添加自定义cookies
           /* if (StringUtils.isBlank(collection.getSeimiCookies())){
                List<SeimiCookie> seimiCookies = new ArrayList<>();
                String[] split = collection.getSeimiCookies().split(",");
                for (int i = 0; i <= split.length; i++) {
                    SeimiCookie seimiCookie = new SeimiCookie("","","","");
                    seimiCookies.add(seimiCookie);
                }
                request.setSeimiCookies(seimiCookies);
            }*/
            request.setCallBack("abcd");

            if (!map.containsKey(request)) {
                map.put(request, rule);
            }
            requests.add(request);
        }

        //return super.startRequests();
        return requests;
    }


    //这样问题就是怎么才能让rule成为参数了。
    //这里rule能不能直接带过来，而不是通过数据库去查
    //如果第一页规则有N条（即需要解析多个，比如TB，我要得到页数，还有Url，应该怎么办）

    public void abcd(Response response) throws XpathSyntaxErrorException {
        JXDocument doc = response.document();
        Rule rule = (Rule) map.get(response.getRequest());
        map.remove(response.getRequest());
        Rule rule2 = ruleMapper.selectByPrimaryKey(rule.getChildId());
        String parentRule = rule.getRule();
        String[] split = parentRule.split(";");
        for (int i = 0; i <= split.length; i++) {
            //如果要爬的是img的src，怎么办
            if (rule.getRule().contains("@href") || rule.getRule().contains("@src")) {
             /*   if(rule.getRule().contains("/img/@src")){
                    List<Object> sel = doc.sel(rule1);
                    for (Object o: sel) {
                        logger.info("结果------"+o.toString());
                    }
                }*/

                List<Object> sel = doc.sel(parentRule);
                //构建Request请求，
                for (Object o : sel) {
                    //这里如果Url不包含http或者https应该给它填上去

                    String url = o.toString();
                    if (!url.contains("http") && !url.contains("https")) {
                        url = collection.getUrl().split("://")[0] + ":" + url;
                    }

                    Request request = new Request();
                    request.setUrl(url);
                    request.setCallBack("abcd");
                    if ("1".equals(collection.getUseSeimiagent())) {
                        request.setUseSeimiAgent(true);
                        request.setSeimiAgentUseCookie(true);
                        request.setSeimiAgentRenderTime(collection.getSeimiagentRendertime().longValue());
                        //设置脚本
                        // request.setSeimiAgentScript(collection.getSeimiagentScript());
                    }
//                    request.useSeimiAgent();
//                    request.setSeimiAgentUseCookie(true);
//                    request.setSeimiAgentRenderTime(collection.getSeimiagentRendertime().longValue());

                    map.put(request, rule2);
                    push(request);
                }
            } else {
                //解析最后值,所以淘宝也有可能这里是页数，怎么区分这是接下去爬取还需要的东西，还是已经结束了，可以返回了
                if (rule2 != null) {
                    //还有规则，继续，问题又来了，这个规则是干嘛的又不知道了
                    List<Object> sel = doc.sel(parentRule);
                    for (Object o : sel) {
                        logger.info("结果" + o.toString());
                    }
                } else {
                    //说明接下去没有规则了，直接取数据就好了
                    List<Object> sel = doc.sel(parentRule);
                    for (Object o : sel) {
                        logger.info("结果" + o.toString());
                    }
                }

            }
        }

    }


    //淘宝第一次爬取的时候我要取链接和总共几页。
    @Override
    public void start(Response response) {


    }

    public void taobao(Response response) throws XpathSyntaxErrorException {
        JXDocument doc = response.document();
        Rule rule = (Rule) map.get(response.getRequest());
        Rule childRule = ruleMapper.selectByPrimaryKey(rule.getChildId());
        String parentRule = rule.getRule();
        String[] split = parentRule.split(";");
        if (split != null && split.length != 0) {
            for (int i = 0; i <= split.length; i++) {
                if (split[i].contains("@href") || split[i].contains("@src")) {
                    //说明是个链接，需要构建Request
                    List<Object> sel = doc.sel(split[i]);
                    //构建Request请求，
                    for (Object o : sel) {
                        //这里如果Url不包含http或者https应该给它填上去
                        String url = o.toString();
                        if (!url.contains("http") && !url.contains("https")) {
                            url = collection.getUrl().split("://")[0] + ":" + url;
                        }
                        Request request = new Request();
                        request.setUrl(url);
                        request.setCallBack("taobao");
                        //这里应该和第一个Url构建的Request一样吧？
                        if ("1".equals(collection.getUseSeimiagent())) {
                            request.setUseSeimiAgent(true);
                            request.setSeimiAgentUseCookie(true);
                            request.setSeimiAgentRenderTime(collection.getSeimiagentRendertime().longValue());
                            //设置脚本
                            // request.setSeimiAgentScript(collection.getSeimiagentScript());
                        }
                        map.put(request, childRule);
                        push(request);
                    }

                } else {
                    //淘宝需要知道页数
                    if (split[i].contains("@class='total'")) {
                        if (flag){
                            List<Object> total = doc.sel(split[i]);
                            if (total != null && total.size() != 0) {
                                String page = total.get(0).toString();
                                Pattern p = Pattern.compile("\\d{1,}");//这个2是指连续数字的最少个数
                                Matcher m = p.matcher(page);
                                if (m.find()) {
                                    Integer num = Integer.valueOf(m.group());
                                    for (int j = 1; j < num; j++) {
                                        String url = collection.getUrl() + "&s=" + (i * 44);
                                        Request request = Request.build(url, "taobao")
                                                .setUseSeimiAgent(true)
                                                .setSeimiAgentUseCookie(true)
                                                .setSeimiAgentRenderTime(5000);
                                        map.put(request,split[i]);
                                        push(request);
                                    }
                                }
                            }
                            flag = false;
                        }

                    } else {
                        //说明接下去没有规则了，直接取数据就好了
                        List<Object> sel = doc.sel(split[i]);
                        for (Object o : sel) {
                            logger.info("结果" + o.toString());
                        }
                    }

                }

            }
        }
    }

    @Override
    public String getUserAgent() {
        //  UserAgentExample example = new UserAgentExample();
        // userAgentMapper.selectByExample(example);
        return super.getUserAgent();
    }

    @Override
    public String[] allowRules() {
        //return collection.getAllowRules();
        return super.allowRules();
    }

    @Override
    public String[] denyRules() {
        //return collection.getDenyRules();
        return super.denyRules();
    }

    @Override
    public String proxy() {
        // ProxyExample example = new ProxyExample();
        // proxyMapper.selectByExample(example);
        return super.proxy();
    }

    @Override
    public String seimiAgentHost() {
        return "192.168.111.128";
        // return super.seimiAgentHost();
    }

    @Override
    public int seimiAgentPort() {
        return 8000;
    }


}

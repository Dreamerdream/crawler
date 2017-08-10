package cn.wanghaomiao.seimi.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.http.HttpMethod;
import cn.wanghaomiao.seimi.http.SeimiHttpType;
import cn.wanghaomiao.seimi.mapper.*;
import cn.wanghaomiao.seimi.model.Collection;
import cn.wanghaomiao.seimi.struct.BodyType;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by Administrator on 2017/7/11.
 */
@Crawler(name = "crawler")
public class Crawlers extends BaseSeimiCrawler {
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
    private List<String> rules = new ArrayList<>();

    private Map<Integer,String> map = new HashMap<>();
    private boolean flag = true ;
    @Override
    public String[] startUrls() {

        return null;
    }

    @Override
    public void start(Response response) {

        JXDocument document = response.document();
        String referer = response.getReferer();
        Request request1 = response.getRequest();
        BodyType bodyType = response.getBodyType();
        String charset = response.getCharset();
        String content = response.getContent();
        String realUrl = response.getRealUrl();
        SeimiHttpType seimiHttpType = response.getSeimiHttpType();
        try {
            List<Object> sel = document.sel("//div[class='img-list']/img/@src");
            for (Object o: sel) {
                logger.info("结果"+o.toString());
            }
        } catch (XpathSyntaxErrorException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Request> startRequests() {
        List<Request> requests = new ArrayList<>();
        Request request = new Request();
        request.setUrl("http://waimai.baidu.com/shopui/shopcertificate?shop_id=1895775677");
        request.setUseSeimiAgent(true);
        request.setSeimiAgentUseCookie(true);
        request.setSeimiAgentRenderTime(5000);
        requests.add(request);
        return requests;
    }


    @Override
    public String seimiAgentHost() {
        return "192.168.111.128";
    }

    @Override
    public int seimiAgentPort() {
        return 8000;
    }
}

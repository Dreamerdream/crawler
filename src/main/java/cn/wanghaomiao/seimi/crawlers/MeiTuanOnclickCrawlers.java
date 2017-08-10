package cn.wanghaomiao.seimi.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */
@Crawler(name = "meituanonclick")
public class MeiTuanOnclickCrawlers extends BaseSeimiCrawler {
    @Override
    public String[] startUrls() {
        return null;
    }


    @Override
    public void start(Response response) {

    }



    @Override
    public List<Request> startRequests() {
        List<Request> requests = new ArrayList<>();
        Request request = new Request();
        request.setUseSeimiAgent(true);
        request.setUrl("http://waimai.meituan.com/home/wtq6n96qfhnj");
        request.setSkipDuplicateFilter(false);
        request.setSeimiAgentRenderTime(10000);
        request.setSeimiAgentUseCookie(true);
        request.setCallBack("onclick");
        //  request.setSeimiAgentContentType(SeimiAgentContentType.HTML);
        // request.setSeimiAgentScript("$(\"#fetchMoreRst\").click();");
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

    public void onclick(Response response) throws XpathSyntaxErrorException{
        JXDocument document = response.document();
        List<Object> sel = document.sel("//a[@class='rest-atag']/@href");
        if (sel!=null && sel.size()!=0){
            logger.info("结果--"+sel.size());
            for (Object o:sel) {

                logger.info("链接"+o.toString());
            }
        }
    }
}

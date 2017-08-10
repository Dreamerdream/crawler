package cn.wanghaomiao.seimi.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.http.SeimiAgentContentType;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */
@Crawler(name = "elemeonclick")
public class ElemeOnclickCrawlers extends BaseSeimiCrawler {
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
        request.setUrl("https://www.ele.me/place/wx4etb2h13h?latitude=39.9923&longitude=116.26837");
        request.setSkipDuplicateFilter(false);
        request.setSeimiAgentRenderTime(10000);
        request.setSeimiAgentUseCookie(true);
        request.setCallBack("onclick");
        request.setSeimiAgentContentType(SeimiAgentContentType.HTML);
       // request.setSeimiAgentScript("$(\"#fetchMoreRst\").click();");
        //$('#content').scrollTop( $('#content')[0].scrollHeight );
       // request.setSeimiAgentScript("$(document).ready(function(){$(\".ng-scope\").scrollTop( $(\".ng-scope\")[0].scrollHeight);});");
        request.setSeimiAgentScript("window.onload=function(){document.body.scrollTop=100};");
        requests.add(request);
        return requests;
    }

    public void onclick(Response response) throws XpathSyntaxErrorException{
        JXDocument document = response.document();
        List<Object> sel = document.sel("//div[@class='clearfix']/a");
        if (sel!=null && sel.size()!=0){
            logger.info("结果--"+sel.size());
            for (Object o:sel) {

                logger.info("链接"+o.toString());
            }
        }

        List<Object> sel1 = document.sel("//div[@class='rstblock-title']/text()");
        if (sel1!=null && sel1.size()!=0){
            logger.info("结果--"+sel1.size());
            for (Object o:sel1) {

                logger.info("链接"+o.toString());
            }
        }
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

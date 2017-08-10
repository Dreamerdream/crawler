package cn.wanghaomiao.seimi.crawlers;

import cn.wanghaomiao.seimi.annotation.Interceptor;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaobaoCrawlers extends BaseSeimiCrawler {
    private static final String URL = "https://s.taobao.com/search?q=%E6%97%A5%E5%BC%A5&ie=utf8";
    private static final String PAGE_PARAMETER = "&s=";
    @Override
    public String[] startUrls() {
        return null;
    }

    @Override
    public List<Request> startRequests() {

        List<Request> requests = new ArrayList<>();
        Request request = new Request();
        request.setUrl(URL);
        request.setUseSeimiAgent(true);
        request.setSeimiAgentUseCookie(true);
        request.setSeimiAgentRenderTime(5000);
        request.setCallBack("taobao");
        requests.add(request);
        return requests;
    }

    @Override
    public void start(Response response) {

    }

    public void taobao(Response response) throws XpathSyntaxErrorException {
        JXDocument document = response.document();
        List<Object> sel = document.sel("//div[@class='total']/text()");
        if (sel != null && sel.size() != 0) {
            String page = sel.get(0).toString();
            Pattern p = Pattern.compile("\\d{1,}");//这个2是指连续数字的最少个数
            Matcher m = p.matcher(page);

            if (m.find()) {
                Integer num = Integer.valueOf(m.group());
                for (int i = 0; i <= num; i++) {
                    String url = URL + PAGE_PARAMETER + (i * 44);
                    Request request = Request.build(url, "getUrl")
                            .setUseSeimiAgent(true)
                            .setSeimiAgentUseCookie(true)
                            .setSeimiAgentRenderTime(5000);
                    push(request);
                }
            }
        }

    }

    public void getUrl(Response response) throws XpathSyntaxErrorException{
        JXDocument document = response.document();
        List<Object> sel = document.sel("//div[@class='row row-2 title']/a/@href");
        if (sel != null && sel.size() != 0) {
            for (int i = 0; i <sel.size() ; i++) {
                String url = sel.get(i).toString();
                Request request = new Request();
                request.setUrl(url);
                request.setUseSeimiAgent(true);
                request.setSeimiAgentUseCookie(true);
                request.setSeimiAgentRenderTime(5000);
                request.setCallBack("getData");
                push(request);
            }
        }
    }

    public void getData(Response response) throws XpathSyntaxErrorException{
        JXDocument document = response.document();
        List<Object> sel = document.sel("//ul[@class='attributes-list']/li/text()");
        if (sel != null && sel.size() != 0) {
            for (int i = 0; i <sel.size() ; i++) {
                logger.info("结果------" + sel.get(i).toString());
            }
        }

    }
}

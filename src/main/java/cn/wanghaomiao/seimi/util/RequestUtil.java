package cn.wanghaomiao.seimi.util;

import cn.wanghaomiao.seimi.http.HttpMethod;
import cn.wanghaomiao.seimi.model.Collection;
import cn.wanghaomiao.seimi.model.Rule;
import cn.wanghaomiao.seimi.struct.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/13.
 */
public class RequestUtil {

    public static Request getRequest(Collection collection,Rule rule,String callback) {
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
                request.setSeimiAgentScript(collection.getSeimiagentScript());
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
            request.setCallBack(callback);
        return request;
    }
}
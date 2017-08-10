package cn.wanghaomiao.seimi.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.bean.*;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.http.HttpMethod;
import cn.wanghaomiao.seimi.http.SeimiAgentContentType;
import cn.wanghaomiao.seimi.http.SeimiCookie;
import cn.wanghaomiao.seimi.mapper.CollectionMapper;
import cn.wanghaomiao.seimi.mapper.RuleMapper;
import cn.wanghaomiao.seimi.model.Collection;
import cn.wanghaomiao.seimi.model.Rule;
import cn.wanghaomiao.seimi.redis.JedisClient;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Crawler(name = "allCrawlers",httpTimeOut = 30000,delay = 3)
public class AllCrawlers extends BaseSeimiCrawler {


    private Collection collection;
    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private RuleMapper ruleMapper;
    @Autowired
    private JedisClient jedisClientPool;
    private ConcurrentHashMap map = new ConcurrentHashMap();
    private Vector vector = new Vector();
    private static final int NUM = 100;
    private String[] ip;
    private static final String TAOBAO_URL = "https://s.taobao.com/search?q=%E6%97%A5%E5%BC%A5&ie=utf8";
    private static final String TAOBAO_PAGE_PARAMETER = "&s=";
    private static final String TIANMAO_URL = "https://list.tmall.com/search_product.htm?q=%CA%D6%B1%ED";
    private static final String TIANMAO_PAGE_PARAMETER = "&s=";
    private static final String JD_URL = "https://search.jd.com/Search?keyword=%e8%af%ba%e5%9f%ba%e4%ba%9a&enc=utf-8&pvid=90bade88c4f5462da903b12ded6e5d6e";
    private static final String JD_PAGE_PARAMETER = "&page=";
    private static final String JD_SCROLLING = "&click=0&scrolling=y";
    private static final String HTTPS = "https:";
    private static final String BUSINESS_DETAIL = "business_detail";
    private static final String ALIBABA_URL = "https://s.1688.com/selloffer/offer_search.htm?keywords=%C2%DE%C3%C9";
    private static final String ALIBABA_PAGE_PARAMETER = "&beginPage=";
    //这个参数是为了区分是1688,tb,tianmao,jd的,后期应该是从数据库带过来
    private static final String BUSINESS_TYPE = "0";
    private boolean flag = true;
   // private CountDownLatch countDownLatch = null;
    //验证成功的代理IP队列
    private static ConcurrentLinkedQueue<String> verifyQueue = new ConcurrentLinkedQueue<String>();
    //未验证的代理IP队列,即原始队列
    private static ConcurrentLinkedQueue<String> originalQueue = new ConcurrentLinkedQueue<String>();
    //需要验证的代理IP队列,即原始队列
    private static LinkedBlockingQueue<String>  getIPsQueue = new LinkedBlockingQueue<>();
   // private static LinkedBlockingQueue<String>  proxyQueue = new LinkedBlockingQueue<>();
    //线程池
    private ExecutorService executorService = Executors.newFixedThreadPool(50);
    private AtomicInteger integer = new AtomicInteger(0);
    private static final int  BASE_THREAD_NUM = 2;
    @Override
    public String[] startUrls() {
        return null;
    }

    @Override
    public void start(Response response) {


    }

    @Override
    public List<Request> startRequests() {
        getIPs();
        for (int i = 1; i <= 50 ; i++) {
            executorService.execute(new VaildThread());
        }
        //vaildIP();
        collection = collectionMapper.selectByPrimaryKey(6);
        List<Request> requests = new ArrayList<>();
        if (collection != null) {
            Request request = new Request();
            request.setUrl(collection.getUrl());
            request.setCallBack("pageNum");
            if (Http.GET.val().equals(collection.getHttpMethod())) {
                //Get方法
                request.setHttpMethod(HttpMethod.GET);
            } else if (Http.POST.val().equals(collection.getHttpMethod())) {
                //Post方法
                if (collection.getParams() != null) {
                    request.setHttpMethod(HttpMethod.POST);
                    Map<String, String> paramMap = new HashMap<>();
                    //参数最好如:xxx:xxx;
                    //切割
                    String params = collection.getParams();
                    String[] split = params.split(";");
                    if (split != null) {
                        for (int i = 0; i < split.length; i++) {
                            String param = split[i];
                            String[] strings = param.split(":");
                            paramMap.put(strings[0], strings[1]);
                        }
                        request.setParams(paramMap);
                    }
                }
            } else {
                //Put方法
            }
            request.setMaxReqCount(collection.getMaxReqCount() == 0 ? 3 : collection.getMaxReqCount());
            //request.setStop("0".equals(collection.getStop()));  这个参数暂时不懂，是否停止的信号，收到该信号的处理线程会退出
            request.setSkipDuplicateFilter("0".equals(collection.getSkipDuplicateFilter())); //是否去重，
            if (UseSeimiagent.USE.val().equals(collection.getUseSeimiagent())) {
                //开启js渲染
                request.setUseSeimiAgent(true);
                request.setSeimiAgentRenderTime(collection.getSeimiagentRendertime());
                request.setSeimiAgentScript(collection.getSeimiagentScript());
                request.setSeimiAgentUseCookie(SeimiagentUsecookie.USE.val().equals(collection.getSeimiagentUsecookie()));
            }
            //header
            if (!StringUtils.isBlank(collection.getHeader())) {
                Map<String, String> headerMap = new HashMap<>();
                //参数最好如:xxx:xxx;
                //切割
                String header = collection.getHeader();
                String[] split = header.split(";");
                if (split != null) {
                    for (int i = 0; i < split.length; i++) {
                        String param = split[i];
                        String[] strings = param.split(":");
                        headerMap.put(strings[0], strings[1]);
                    }
                    request.setHeader(headerMap);
                }
            }
            //cookies
            if (!StringUtils.isBlank(collection.getSeimiCookies())) {
                //参数最好如:xxx:xxx:xxx:xxx;
                //切割
                List<SeimiCookie> seimiCookies = new ArrayList<>();
                String cookies = collection.getSeimiCookies();
                String[] split = cookies.split(";");
                if (split != null) {
                    for (int i = 0; i < split.length; i++) {
                        String param = split[i];
                        String[] strings = param.split(":");
                        SeimiCookie seimiCookie = new SeimiCookie(strings[0], strings[1], strings[2], strings[3]);
                        seimiCookies.add(seimiCookie);
                    }
                    request.setSeimiCookies(seimiCookies);
                }
            }
            requests.add(request);
           // Rule rule = ruleMapper.selectByPrimaryKey(collection.getRuleId());

          /*  if (!map.containsKey(request)) {
                map.put(request,rule);
            }*/
        } else {
            logger.info("url为空!");
        }

        return requests;
    }

//    private void vaildIP() {
//
//        String[] IPs = getIP();
//        int count = IPs.length / NUM;
//        if ((IPs.length%NUM) == 0){
//            for (int i=0;i<NUM;i++){
//                String[] ips = new String[count];
//                for (int j = 0; j < count; j++) {
//                    ips[j]=IPs[(count*i)+j];
//                }
//                VaildThread vaildThread = new VaildThread(ips);
//                executorService.execute(vaildThread);
//              //  new Thread(vaildThread).start();
//
//            }
//        }else {
//            for (int i=0;i<(NUM+1);i++){
//                String[] ips = new String[count];
//                for (int j = 0; j < count; j++) {
//                    if (((count*i)+j)>=IPs.length){
//                        break;
//                    }
//                    ips[j]=IPs[(count*i)+j];
//
//                }
//                VaildThread vaildThread = new VaildThread(ips);
//                executorService.execute(vaildThread);
//                //new Thread(vaildThread).start();
//
//            }
//        }
//
//    }

    //获取页数
    public void pageNum(Response response) throws XpathSyntaxErrorException {
        if (collection != null) {
            //得到Rule
            Rule rule = ruleMapper.selectByPrimaryKey(collection.getRuleId());
            JXDocument document = response.document();
            List<Object> sel = document.sel(rule.getRule());
            if (sel != null && sel.size() != 0) {
                String page = sel.get(0).toString();
                if (BusinessType.JD.val().equals(BUSINESS_TYPE)){
                    //京东的页数
                    Integer pageNum = Integer.valueOf(page);
                    for (int i = 1; i <= pageNum; i++) {
                        //这里不知道行不行啊
                        if(i == pageNum){
                            String url = JD_URL + JD_PAGE_PARAMETER + (2*pageNum-1); //
                            Request request = new Request();
                            request.setUseSeimiAgent(true);
                            request.setSeimiAgentScript(collection.getSeimiagentScript());
                            request.setSeimiAgentUseCookie(true);
                            request.setSeimiAgentRenderTime(collection.getSeimiagentRendertime());
                            request.setUrl(url);
                            request.setCallBack("getUrl");
                            push(request);
                            map.put(request,rule);
                        }else {
                            for (int j=1; j<= (pageNum-1)*2;j++){
                                String url = JD_URL +JD_SCROLLING + JD_PAGE_PARAMETER + j; //
                                Request request = new Request();
                                request.setUseSeimiAgent(true);
                                request.setSeimiAgentUseCookie(true);
                                request.setSeimiAgentRenderTime(collection.getSeimiagentRendertime());
                                request.setUrl(url);
                                request.setCallBack("getUrl");
                                push(request);
                                map.put(request,rule);
                            }
                        }

                    }
                }else if ((BusinessType.TAOBAO.val().equals(BUSINESS_TYPE))){
                    //淘宝的页数
                    Pattern p = Pattern.compile("\\d{1,}");//这个2是指连续数字的最少个数
                    Matcher m = p.matcher(page);
                    if (m.find()) {
                        Integer pageNum = Integer.valueOf(m.group());
                       for (int i = 0; i < pageNum; i++) {
                            String url = TAOBAO_URL + TAOBAO_PAGE_PARAMETER + (i * 44); //
                            Request request = new Request();
                            request.setUseSeimiAgent(true);
                            request.setSeimiAgentUseCookie(true);
                            request.setCallBack("getUrl");
                            request.setUrl(url);
                            request.setSeimiAgentRenderTime(collection.getSeimiagentRendertime());
                            push(request);
                            map.put(request,rule);
                        }
                    }
                }else if ((BusinessType.TIANMAO.val().equals(BUSINESS_TYPE))){
                    //天猫的页数
                    Integer pageNum = Integer.valueOf(page);
                    for (int i = 0; i < pageNum ; i++) {
                        String url = TIANMAO_URL + TIANMAO_PAGE_PARAMETER + 0; //(i*60)
                        Request request = new Request();
                        request.setUrl(url);
                        request.setUseSeimiAgent(true);
                        request.setSeimiAgentRenderTime(collection.getSeimiagentRendertime());
                        request.setCallBack("getUrl");
                        request.setSeimiAgentUseCookie(true);
                        push(request);
                        map.put(request,rule);
                    }
                }else if ((BusinessType.ALIBABA.val().equals(BUSINESS_TYPE))){
                    //1688的页数
                    Integer pageNum = Integer.valueOf(page);
                  //  for (int i = 1; i <= pageNum ; i++) {
                        String url = ALIBABA_URL + ALIBABA_PAGE_PARAMETER + 1; //i
                        Request request = new Request();
                        request.setUrl(url);
                        request.setUseSeimiAgent(true);
                        request.setSeimiAgentRenderTime(5000);
                        request.setCallBack("getGoods");
                        request.setSeimiAgentUseCookie(true);
                        push(request);
                        map.put(request,rule);
              //      }
                }
            }

        }
    }

    //只给1688用的
    public void getGoods(Response response)throws XpathSyntaxErrorException{
        Request parentRequest = response.getRequest();
        Rule rule  = (Rule) map.get(parentRequest);
        map.remove(parentRequest);
        Rule urlRule = ruleMapper.selectByPrimaryKey(rule.getChildId());
        JXDocument document = response.document();
        List<Object> sel = document.sel(urlRule.getRule());
        if (urlRule != null){
            if (sel != null && sel.size() != 0){
                String content = sel.get(0).toString();
                JSONObject jsonObject = JSON.parseObject(content);
                String url = (String)jsonObject.get("url");
                url = url.replace("asyncCount=20", "asyncCount=60").replace("startIndex=20", "startIndex=0");
                Request request = new Request();
               // request.setUseSeimiAgent(true);
               // request.setSeimiAgentRenderTime(5000);
               // request.setSeimiAgentUseCookie(true);
                request.setCallBack("getUrl");
               // request.setHttpMethod(HttpMethod.GET);
                request.setUrl(url);
                push(request);
            }
        }

    }

    //获取店铺链接
    public void getUrl(Response response) throws XpathSyntaxErrorException{


        if ((BusinessType.ALIBABA.val().equals(BUSINESS_TYPE))){
          //  int count = 0;
            String content = response.getContent();
            JXDocument document = response.document();
            Pattern pattern = Pattern.compile("t-click-item=\\\\\"title\\\\\".*?target=\\\\\"_blank\\\\\"");
            Matcher match = pattern.matcher (content);
            if (match.find()){
                while (match.find()){
                    String group = match.group();
                    String substring = group.substring(group.indexOf("https"), group.indexOf("target")).replace("\\","");
                    String url = substring.substring(0, substring.indexOf("\""));
                    if (url.contains("amp;")){
                        url = url.replace("amp;","");
                    }
                    Request request = new Request();
                    request.setUrl(url);
                    request.setCallBack("getData");
                    request.setUseSeimiAgent(true);
                    request.setSeimiAgentRenderTime(5000);
                    request.setSeimiAgentUseCookie(true);
                    // count ++ ;
                    // if (count == 60){
                    push(request);
                    // }
                }
            }else {

                push(response.getRequest());
            }

            //logger.info("count------" + count);
        }else {
            Request parentRequest = response.getRequest();
            Rule rule = (Rule) map.get(parentRequest);
            map.remove(parentRequest);
            //url的rule
            Rule urlRule = ruleMapper.selectByPrimaryKey(rule.getChildId());
            JXDocument document = response.document();
            if (urlRule != null){
                List<Object> sel = document.sel(urlRule.getRule());
                if (sel != null && sel.size() != 0){
                    for (Object o: sel) {
                        String url = o.toString();
                        if (!url.contains(HTTPS)){
                            url = HTTPS + url;
                        }
                        // 淘宝中出现的天猫店不查询
//                        if (url.contains("detail.tmall.com") && BusinessType.TAOBAO.val().equals("1")){
//                            continue;
//                        }else {
                            Request request = new Request();
                            request.setUrl(url);
                            request.setUseSeimiAgent(true);
                            request.setSeimiAgentRenderTime(collection.getSeimiagentRendertime());
                            request.setSeimiAgentUseCookie(true);
                            request.setCallBack("getData");
                            push(request);

                 //       }
                    }
                }
            }
        }

  //      }

    }

    //获取详细信息,会报String input must not be null,估计response为空了
    public void getData(Response response) throws Exception{
        JXDocument document = response.document();
        Request urlRequest = response.getRequest();
        String url = urlRequest.getUrl();
      /*  Rule urlRule = (Rule) map.get(urlRequest);
        map.remove(urlRequest);*/
      /*  Rule detailRule = ruleMapper.selectByPrimaryKey(urlRule.getChildId());
        if (detailRule != null){
            List<Object> sel = document.sel(detailRule.getRule());
            if (sel != null && sel.size() != 0){
                for (Object o:sel) {
                    //存数据库
                }
            }
        }
*/

        if (url.contains("item.taobao.com")){
            String key = "TAOBAO_" + url;
            TaobaoDetail detail = response.render(TaobaoDetail.class);
            detail.setShopUrl(url);
            jedisClientPool.hset(BUSINESS_DETAIL, key, JSON.toJSONString(detail));
        }else if (url.contains("detail.tmall.com")){
            String key = "TIANMAO_" + url;
            TianmaoDetail detail = response.render(TianmaoDetail.class);
            detail.setShopUrl(url);
            jedisClientPool.hset(BUSINESS_DETAIL, key, JSON.toJSONString(detail));
        }else if (url.contains("jd.com")){
            String key = "JD" + url;
            JdDetail detail = response.render(JdDetail.class);
            detail.setShopUrl(url);
            jedisClientPool.hset(BUSINESS_DETAIL, key, JSON.toJSONString(detail));
        }else {
            String key = "ALIBABA" + url;
            AlibabaDetail detail = response.render(AlibabaDetail.class);
            detail.setShopUrl(url);
            jedisClientPool.hset(BUSINESS_DETAIL, key, JSON.toJSONString(detail));
        }


    }
/*    @Override
    public String[] denyRules() {
        if (collection != null) {
            String denyRules = collection.getDenyRules();
            if (!StringUtils.isBlank(denyRules)){
                String[] split = collection.getDenyRules().split(";");
                if (split != null) {
                    return split;
                }
            }
        }
        return super.denyRules();
    }*/

 /*   @Override
    public String[] allowRules() {
        if (collection != null) {
            String allowRules = collection.getAllowRules();
            if (!StringUtils.isBlank(allowRules)){
                String[] split = allowRules.split(";");
                if (split != null) {
                    return split;
                }
            }

        }
        return super.allowRules();
    }*/

    @Override
    public String seimiAgentHost() {
        return "192.168.111.130";
    }

    @Override
    public int seimiAgentPort() {
        return 8000;
    }



    //return (String) vector.get(RandomUtils.nextInt(0,vector.size()));
     /*   for (int i = 0; i < 200; i++) {
            Future<String> future = executorService.submit(new ValidIPFuture());
            try {
                if (future != null && future.get() != null){
                    System.out.println("代理IP----------" + future.get());
                    return future.get();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }*/

    @Override
    public String proxy() {

     if (!verifyQueue.isEmpty()){
         String proxy = verifyQueue.poll();
         verifyQueue.offer(proxy);
         return proxy;
     }else {
         if (!originalQueue.isEmpty()){
             //这里如果验完的IP用完了，然后走这步,也就说，等于全在用废弃IP在用。。
             String proxy = verifyQueue.poll();
             return proxy;
         }else {
             getIPs();
             return super.proxy();
         }

     }
    }

    @Override
    public void handleErrorRequest(Request request) {
        //将处理失败的请求上传服务器,现在是继续push进队列
       push(request);
    }



   /* public  String[] getIP(){
        HttpGet httpGet = new HttpGet("http://ip.baizhongsou.com/?u=bsbsbs1&p=60827015e5d605ed&sl=1000");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response != null){
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == 200){
                    HttpEntity entity = response.getEntity();
                    InputStream inputStream = entity.getContent();
                    String content = IOUtils.toString(inputStream);
                    System.out.println(content);
                    String[] IPs = content.split("<br>");
                    System.out.println("IP个数-----"+IPs.length);
                    return IPs;
                }else {
                    return null;
                }
            }else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    class VaildThread implements Runnable {

    /*    private String[] IPs;

        public VaildThread(String[] ips){
            this.IPs = ips;
        }*/

        @Override
        public void run() {

              /*  CloseableHttpClient httpClient = HttpClients.createDefault();
                for (int i = 0; i < IPs.length; i++) {
                    String ip = IPs[i];
                    if (!StringUtils.isBlank(ip)) {
                        String[] split = ip.split(":");
                        HttpHost host = new HttpHost(split[0], Integer.valueOf(split[1]));
                        RequestConfig config = RequestConfig.custom().setProxy(host).build();
                        HttpGet httpGet = new HttpGet("http://1212.ip138.com/ic.asp");
                        httpGet.setConfig(config);
                        CloseableHttpResponse response = null;
                        try {
                            response = httpClient.execute(httpGet);
                            if (response != null) {
                                StatusLine statusLine = response.getStatusLine();
                                if (statusLine.getStatusCode() == 200) {
                                    HttpEntity entity = response.getEntity();
                                    String content = IOUtils.toString(entity.getContent(), "GBK");
                                    if (content.contains(split[0])) {
                                        System.out.println(ip);
                                     //   System.out.println(integer.incrementAndGet());
                                        blockingDeque.put(ip);
                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println(integer.incrementAndGet());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            System.out.println(integer.incrementAndGet());
                        }

                    }
                }*/
              while (true){
                  CloseableHttpClient httpClient = HttpClients.createDefault();
                  String ip = null;
                  try {
                      if (!getIPsQueue.isEmpty()){
                          ip = getIPsQueue.take();
                      }
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
                  if (!StringUtils.isBlank(ip)) {
                      String[] split = ip.split(":");
                      HttpHost host = new HttpHost(split[0], Integer.valueOf(split[1]));
                      RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(2000).setProxy(host).build();
                      HttpGet httpGet = new HttpGet("http://1212.ip138.com/ic.asp");
                      httpGet.setConfig(config);
                      CloseableHttpResponse response = null;
                      try {
                          response = httpClient.execute(httpGet);
                          if (response != null) {
                              StatusLine statusLine = response.getStatusLine();
                              if (statusLine.getStatusCode() == 200) {
                                  HttpEntity entity = response.getEntity();
                                  String content = IOUtils.toString(entity.getContent(), "GBK");
                                  if (content.contains(split[0])) {
//                                      blockingDeque.push(ip);
//                                      vector.add(ip);
                                      verifyQueue.offer(ip);
                                  }
                              }
                          }
                      } catch (IOException e) {
                          e.printStackTrace();
                          System.out.println(integer.incrementAndGet());
                      }
                  }
              }

        }

    }


    //每隔5分钟取一次IP,先清空队列然后放入队列,
    @Scheduled(cron = "0 0/5 * * * ?")
    public void ip(){
        getIPs();
    }


    public void getIPs(){
        HttpGet httpGet = new HttpGet("http://ip.baizhongsou.com/?u=bsbsbs1&p=60827015e5d605ed&sl=1000");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response != null){
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == 200){
                    HttpEntity entity = response.getEntity();
                    InputStream inputStream = entity.getContent();
                    String content = IOUtils.toString(inputStream);
                    System.out.println(content);
                    String[] IPs = content.split("<br>");
                    getIPsQueue.clear();
                    originalQueue.clear();
                    System.out.println("IP个数-----"+IPs.length);
                    for (int i = 0; i <IPs.length; i++) {
                        //将IP放队列
                        getIPsQueue.put(IPs[i]);
                        originalQueue.offer(IPs[i]);
                    }


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

/*
    class ValidIPFuture implements Callable<String>{

        @Override
        public String call() throws Exception {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String ip = queue.poll();
            if (!StringUtils.isBlank(ip)) {
                String[] split = ip.split(":");
                HttpHost host = new HttpHost(split[0], Integer.valueOf(split[1]));
                RequestConfig config = RequestConfig.custom().setProxy(host).build();
                HttpGet httpGet = new HttpGet("http://1212.ip138.com/ic.asp");
                httpGet.setConfig(config);
                CloseableHttpResponse response = null;
                try {
                    response = httpClient.execute(httpGet);
                    if (response != null) {
                        StatusLine statusLine = response.getStatusLine();
                        if (statusLine.getStatusCode() == 200) {
                            HttpEntity entity = response.getEntity();
                            String content = IOUtils.toString(entity.getContent(), "GBK");
                            if (content.contains(split[0])) {

                                System.out.println(integer.incrementAndGet());
                                return ip;
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println(integer.incrementAndGet());
                    e.printStackTrace();
                    return null;
                }
            }
           return null;
        }
    }*/
}

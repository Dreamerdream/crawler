package com.hgh;

import cn.wanghaomiao.seimi.bean.AlibabaUrl;
import cn.wanghaomiao.seimi.bean.TestB;
import cn.wanghaomiao.seimi.bean.TestUrl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestA {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


     /*   ExecutorService service = Executors.newFixedThreadPool(1);
        Future submit = service.submit(new FutureA());
        int a = (int)submit.get();
        System.out.println(a);*/

   /* String a = "({\n" +
            "\"hasError\":false,\n" +
            "\"message\":\"success\",\n" +
            "\"content\":{\n" +
            " \"offerResult\":{\n" +
            " \"html\":{\n" +"  }" +
            "\"tracerId\":\"1016414021901501736196767000249\",\n" +
            "\n" +
            "\"end\":0\n" +
            "\n" +
            "}\n" +
            "})";
        String replace = a.replace("(", "").replace(")", "");

        JSONObject jsonObject = JSON.parseObject(replace);
        String content = (String) jsonObject.get("content");
        JSONObject object = JSON.parseObject(content);
        String result = (String) object.get("offerResult");
        JSONObject jsonObject1 = JSON.parseObject(result);
        String html = (String) jsonObject1.get("html");
        System.out.println(html);*/

       /* String regex = "\\{[^\\}]*\\}";
        String string = "你好{abc}我是{def}早上好";
        Pattern pattern = Pattern.compile (regex);
        Matcher match = pattern.matcher (string);
        while (match.find ())
        {
            System.out.println (match.group ());
        }*/

//       String a = "abdcde";
//        String substring = a.substring(0, a.indexOf("e"));
//        System.out.println(substring);

        new TestRunnable();

       /* AlibabaUrl alibabaUrl = new AlibabaUrl();
        TestB testB = new TestB();
        testB.setAdd("add");
        testB.setName("ghg");
        TestUrl testUrl = new TestUrl();
        testUrl.setAsyncLoad("1");
        testUrl.setContainerId("333");
        testUrl.setPageSize(9);
        testUrl.setUrl("dfsdf");
        testUrl.setTestB(testB);
        alibabaUrl.setTestUrls(testUrl);
        alibabaUrl.setHasError("dfsdf");
        alibabaUrl.setMessage("dfd");
        String string = JSON.toJSONString(alibabaUrl);
        System.out.println(string);*/
    }
}

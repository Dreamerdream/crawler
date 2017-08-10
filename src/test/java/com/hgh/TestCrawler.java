package com.hgh;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.core.Seimi;
import cn.wanghaomiao.seimi.crawlers.TianmaoCrawlers;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.model.Rule;
import cn.wanghaomiao.seimi.struct.Request;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/7/11.
 */
public class TestCrawler {
    public static void main(String[] args) {

        Seimi seimi = new Seimi();
      //  seimi.goRun("mycrawler");
       // seimi.goRun("meituancrawler");
       // seimi.goRun("baiducrawler");
       // seimi.goRun("elemecrawler");
       // seimi.goRun("crawler");
//        HashSet set = new HashSet();
//        set.add(new String("aaa"));
//        Iterator iterator = set.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next().toString());
//        }
//
//        HashMap map = new HashMap();
//        map.put("aaa",null);
//        System.out.println(map.get("aaa"));
//        Hashtable table = new Hashtable();


        seimi.goRun("jdcrawlers");
       // String a = "156014000";
       // int i = a.hashCode();
       // System.out.println(i);
        //  String url = "https://s.taobao.com/search?q=DOPE&commend=all&ssid=s5-e&search_type=item&sourceId=tb.index&spm=a21bo.50862.201856-taobao-item.1&ie=utf8&s=0";

      //  int i = url.lastIndexOf("=");

       // String substring = url.substring(0, i+1);


      //  System.out.println(substring);

//        ConcurrentHashMap map = new ConcurrentHashMap();
//        Request request = new Request();
//        Rule rule = new Rule();
//        rule.setId(1);
//        rule.setRule("1111");
//        map.put(request,rule);
//
//        Rule o = (Rule)map.get(request);
//        System.out.println(o.toString());

     //   String a = "aa";
      //  System.out.println(a.hashCode());
//        Crawler c =  TianmaoCrawlers.class.getAnnotation(Crawler.class);
//        c.proxy();
//        System.out.println(c.proxy());
      //  Cra crawlers = new TianmaoCrawlers();
      //  crawlers.proxy();
    }
}

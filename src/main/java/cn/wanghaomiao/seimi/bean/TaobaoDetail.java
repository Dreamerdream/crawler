package cn.wanghaomiao.seimi.bean;

import cn.wanghaomiao.seimi.annotation.Xpath;

public class TaobaoDetail extends Detail{

    private String shopUrl;

    @Xpath("//h3[@class='tb-main-title']/text() | //h1[@class='title']/text()")
    private String title;

    @Xpath("//ul[@class='attributes-list']/li/text()")
    private String detail;

    @Xpath("//div[@class='tb-shop-name']/dl/dd/strong/a/text() | //div[@class='wangwang']/a/text()")
    private String shop;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }
}

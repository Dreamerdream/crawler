package cn.wanghaomiao.seimi.bean;

import cn.wanghaomiao.seimi.annotation.Xpath;

public class JdDetail extends Detail{
    private String shopUrl;

    @Xpath("//div[@class='sku-name']/text()")
    private String title;

    @Xpath("//div[@class='p-parameter']/ul/li/text() |//div[@class='Ptable']/div/dl/dt/text()")
    private String detail;

    @Xpath("//div[@class='item']/div[@class='name']/a/text() | //div[@class='shopInfo']/div[@class='shopName']/strong/span/a/text()")
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

package cn.wanghaomiao.seimi.bean;

import cn.wanghaomiao.seimi.annotation.Xpath;

public class TianmaoDetail extends Detail {
    private String shopUrl;

    @Xpath("//div[@class='tb-detail-hd']/h1/text()")
    private String title;

    @Xpath("//ul[@id='J_AttrUL']/li/text()")
    private String detail;

    @Xpath("//a[@class='slogo-shopname']/strong/text()")
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

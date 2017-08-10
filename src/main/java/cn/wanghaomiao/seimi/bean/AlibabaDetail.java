package cn.wanghaomiao.seimi.bean;

import cn.wanghaomiao.seimi.annotation.Xpath;

public class AlibabaDetail {
    private String shopUrl;

    @Xpath("//h1[@class='d-title']/text() | //div[@id='mod-detail-title']/h1[@class='d-title']/text()")
    private String title;

    @Xpath("//div[@class='obj-content']/table/tbody/tr/td/text()")
    private String detail;

    @Xpath("//div[@class='company-name']/text() | //div[@class='name-wrap']/table/tbody/tr/td/a[1]/text()")
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

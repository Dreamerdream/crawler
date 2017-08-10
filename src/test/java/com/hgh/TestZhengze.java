package com.hgh;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestZhengze {

    public static void main(String[] args) {

        String string = "asdt-click-item=\"title\" href=\"https:\\/\\/detail.1688.com\\/offer\\/552039104915.html\" target=\"_blank\"asd";
        String b = "<a data-spm-click=\\\"gostr=\\/1688hjlj;locaid=of0\\\" t-click-item=\\\"title\\\" href=\\\"https:\\/\\/detail.1688.com\\/offer\\/552039104915.html\\\"  target=\\\"_blank\\\" offer-stat=\\\"title\\\" title=\\\"布衣2017夏短袖传说特种博兵柏利旗舰店c威罗kt斯斯专柜正品男装\\\"  ><a data-spm-click=\\\"gostr=\\/1688hjlj;locaid=of0\\\" t-click-item=\\\"title\\\" href=\\\"https:\\/\\/detail.1688.com\\/offer\\/550156343031.html\\\"  target=\\\"_blank\\\" offer-stat=\\\"title\\\" title=\\\"保罗普罗蒙 纯色男士衬衫白修身长袖衬衣寸商务免烫职业正装韩版\\\"  >";

        Pattern pattern = Pattern.compile("t-click-item=\\\\\"title\\\\\".*?target=\\\\\"_blank\\\\\"");
        Matcher match = pattern.matcher (b);
        if (match.find()){
            while (match.find()){
                String group = match.group();
                System.out.println(group);
                //  String substring = group.substring(group.indexOf("https"), group.indexOf("target")).replace("\\","");
                // substring = substring.substring(0, substring.indexOf("\""));
                // System.out.println(substring);
            }
        }

    }
}

package com.cskaoyan.week3.xmldatafromsever;

/**
 * Created by zhao on 2017/3/28.
 */

//               phone	string	查询的手机号码
//               msg	string	出错消息,当出错时将会出现此节点，否则不出现
//               area	string	手机号码所在地区区号
//               postno	string	所在地区邮编
//               att	string	手机号码归属地
//               ctype	string	号码卡类型
//               par	string	所有号码区间
//               prefix	string	运营商号段
//               operators	string	所属运营商
//               style_simcall	string	简写归属地

public class LocationInfo {

    // <phone>13800138000</phone>
    String pohne;
    //<area>010</area>
    String area;
    //<postno>100000</postno>
    String postno;
    //<att>中国,北京</att>
    String att;
    //<ctype>中国移动138卡</ctype>
    String ctype;
    //<operators>中国移动</operators>
    String operators;
    //<style_simcall>中国,北京</style_simcall>
    String style_simcall;

    @Override
    public String toString() {
        return "LocationInfo{" +
                "pohne='" + pohne + '\'' +
                ", area='" + area + '\'' +
                ", postno='" + postno + '\'' +
                ", att='" + att + '\'' +
                ", ctype='" + ctype + '\'' +
                ", operators='" + operators + '\'' +
                ", style_simcall='" + style_simcall + '\'' +
                '}';
    }

    public String getPohne() {
        return pohne;
    }

    public void setPohne(String pohne) {
        this.pohne = pohne;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPostno() {
        return postno;
    }

    public void setPostno(String postno) {
        this.postno = postno;
    }

    public String getAtt() {
        return att;
    }

    public void setAtt(String att) {
        this.att = att;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators;
    }

    public String getStyle_simcall() {
        return style_simcall;
    }

    public void setStyle_simcall(String style_simcall) {
        this.style_simcall = style_simcall;
    }
}

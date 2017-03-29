package com.example.homework_day_14_xml_json.domain;

/**
 * Created by Roc on 2017/3/29.
 */

/*
*
phone	string	查询的手机号码
msg	string	出错消息,当出错时将会出现此节点，否则不出现
area	string	手机号码所在地区区号
postno	string	所在地区邮编
att	string	手机号码归属地
ctype	string	号码卡类型
par	string	所有号码区间
prefix	string	运营商号段
operators	string	所属运营商
style_simcall	string	简写归属地
style_citynm	string	完整归属地

<phone>13800138000</phone>
<area>010</area>
<postno>100000</postno>
<att>中国,北京</att>
<ctype>北京移动全球通卡</ctype>
<par>1380013</par>
<prefix>138</prefix>
<operators>移动</operators>
<style_simcall>中国,北京</style_simcall>
<style_citynm>中华人民共和国,北京市</style_citynm>
*/
public class NumberLocation {
    private String phone;
    //private String msg;
    private String area;
    private String postno;
    private String att;
    private String ctype;
    private String par;
    private String prefix;
    private String operators;
    private String style_simcall;
    private String style_citynm;

    public NumberLocation() {
    }

    public NumberLocation(String phone, String area, String postno, String att, String ctype, String par, String prefix, String operators, String style_simcall, String style_citynm) {
        this.phone = phone;
        this.area = area;
        this.postno = postno;
        this.att = att;
        this.ctype = ctype;
        this.par = par;
        this.prefix = prefix;
        this.operators = operators;
        this.style_simcall = style_simcall;
        this.style_citynm = style_citynm;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par = par;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
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

    public String getStyle_citynm() {
        return style_citynm;
    }

    public void setStyle_citynm(String style_citynm) {
        this.style_citynm = style_citynm;
    }

    @Override
    public String toString() {
        return "NumberLocation{" +
                "phone='" + phone + '\'' +
                ", area='" + area + '\'' +
                ", postno='" + postno + '\'' +
                ", att='" + att + '\'' +
                ", ctype='" + ctype + '\'' +
                ", par='" + par + '\'' +
                ", prefix='" + prefix + '\'' +
                ", operators='" + operators + '\'' +
                ", style_simcall='" + style_simcall + '\'' +
                ", style_citynm='" + style_citynm + '\'' +
                '}';
    }
}

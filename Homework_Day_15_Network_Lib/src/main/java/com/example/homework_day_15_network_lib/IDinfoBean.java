package com.example.homework_day_15_network_lib;

/**
 * Created by Roc on 2017/3/29.
 */

public class IDinfoBean {

    private String success;
    private Result result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return result.toString();
    }

    class Result {
        private String status;
        private String par;
        private String idcard;
        private String born;
        private String sex;
        private String att;
        private String postno;
        private String areano;
        private String styleSimcall;
        private String styleCitynm;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPar() {
            return par;
        }

        public void setPar(String par) {
            this.par = par;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getBorn() {
            return born;
        }

        public void setBorn(String born) {
            this.born = born;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAtt() {
            return att;
        }

        public void setAtt(String att) {
            this.att = att;
        }

        public String getPostno() {
            return postno;
        }

        public void setPostno(String postno) {
            this.postno = postno;
        }

        public String getAreano() {
            return areano;
        }

        public void setAreano(String areano) {
            this.areano = areano;
        }

        public String getStyleSimcall() {
            return styleSimcall;
        }

        public void setStyleSimcall(String styleSimcall) {
            this.styleSimcall = styleSimcall;
        }

        public String getStyleCitynm() {
            return styleCitynm;
        }

        public void setStyleCitynm(String styleCitynm) {
            this.styleCitynm = styleCitynm;
        }

        @Override
        public String toString() {
            return "身份证前缀:" + par +
                    "\n查询的身份证号码:" + idcard +
                    "\n出生年月日:" + born +
                    "\n性别:" + sex +
                    "\n归属地:" + att +
                    "\n邮编:" + postno +
                    "\n区号:" + areano +
                    "\n地区1:" + styleSimcall +
                    "\n地区2:" + styleCitynm;
        }
    }
}

package com.example.homework_day_10_listview_recycleview;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * Created by Roc on 2017/3/25.
 */

public class GoodsBean implements Parcelable {

    public static final Creator<GoodsBean> CREATOR = new Creator<GoodsBean>() {
        @Override
        public GoodsBean createFromParcel(Parcel in) {
            return new GoodsBean(in);
        }

        @Override
        public GoodsBean[] newArray(int size) {
            return new GoodsBean[size];
        }
    };
    private String goodsID;
    private int goodsPhoto;
    private String goodsTitle;
    private BigDecimal goodsOrig;
    private BigDecimal goodsDiscount;

    protected GoodsBean(Parcel in) {
        goodsID = in.readString();
        goodsPhoto = in.readInt();
        goodsTitle = in.readString();

        goodsOrig = BigDecimal.valueOf(Double.parseDouble(in.readString()));
        goodsDiscount = BigDecimal.valueOf(Double.parseDouble(in.readString()));
    }

    public GoodsBean() {
    }

    public GoodsBean(String goodsID, int goodsPhoto, String goodsTitle, BigDecimal goodsOrig, BigDecimal goodsDiscount) {
        this.goodsID = goodsID;
        this.goodsPhoto = goodsPhoto;
        this.goodsTitle = goodsTitle;
        this.goodsOrig = goodsOrig;
        this.goodsDiscount = goodsDiscount;
    }

    public static Creator<GoodsBean> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(goodsID);
        dest.writeInt(goodsPhoto);
        dest.writeString(goodsTitle);
        dest.writeString(goodsOrig.toString());
        dest.writeString(goodsDiscount.toString());

    }

    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public int getGoodsPhoto() {
        return goodsPhoto;
    }

    public void setGoodsPhoto(int goodsPhoto) {
        this.goodsPhoto = goodsPhoto;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public BigDecimal getGoodsOrig() {
        return goodsOrig;
    }

    public void setGoodsOrig(BigDecimal goodsOrig) {
        this.goodsOrig = goodsOrig;
    }

    public BigDecimal getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(BigDecimal goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "goodsID='" + goodsID + '\'' +
                ", goodsPhoto=" + goodsPhoto +
                ", goodsTitle='" + goodsTitle + '\'' +
                ", goodsOrig=" + goodsOrig +
                ", goodsDiscount=" + goodsDiscount +
                '}';
    }
}

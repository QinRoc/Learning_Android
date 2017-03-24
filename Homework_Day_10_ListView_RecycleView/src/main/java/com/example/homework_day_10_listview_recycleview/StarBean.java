package com.example.homework_day_10_listview_recycleview;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Roc on 2017/3/25.
 */

public class StarBean implements Parcelable {

    public static final Creator<StarBean> CREATOR = new Creator<StarBean>() {
        @Override
        public StarBean createFromParcel(Parcel in) {
            return new StarBean(in);
        }

        @Override
        public StarBean[] newArray(int size) {
            return new StarBean[size];
        }
    };
    private String id;
    private String name;
    private String nationality;
    private int photo;

    protected StarBean(Parcel in) {
        id = in.readString();
        name = in.readString();
        nationality = in.readString();
        photo = in.readInt();
    }

    public StarBean() {
    }

    public StarBean(String id, String name, String nationality, int photo) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.photo = photo;
    }

    public static Creator<StarBean> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(nationality);
        dest.writeInt(photo);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "StarBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}

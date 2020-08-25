package out.example.spotviewer3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Shop implements Parcelable {
    String shopName;
    String shopCode;
    String shopCondition;
    String shopType;
    String address;
    String addressOld;
    String tel;
    String availableLocalCurrency;
    String currencyRealistic;
    String currencyVirtual;
    String currencyMobile;
    String postalCode;

    double latitude;
    double longitude;

    String date;

    public Shop() {

    }

    protected Shop(Parcel in) {
        shopName = in.readString();
        shopCode = in.readString();
        shopCondition = in.readString();
        shopType = in.readString();
        address = in.readString();
        addressOld = in.readString();
        tel = in.readString();
        availableLocalCurrency = in.readString();
        currencyRealistic = in.readString();
        currencyVirtual = in.readString();
        currencyMobile = in.readString();
        postalCode = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        date = in.readString();
    }

    public static final Creator<Shop> CREATOR = new Creator<Shop>() {
        @Override
        public Shop createFromParcel(Parcel in) {
            return new Shop(in);
        }

        @Override
        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(shopName);
        parcel.writeString(shopCode);
        parcel.writeString(shopCondition);
        parcel.writeString(shopType);
        parcel.writeString(address);
        parcel.writeString(addressOld);
        parcel.writeString(tel);
        parcel.writeString(availableLocalCurrency);
        parcel.writeString(currencyRealistic);
        parcel.writeString(currencyVirtual);
        parcel.writeString(currencyMobile);
        parcel.writeString(postalCode);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeString(date);
    }
}

package hanks.pub.themedemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * User
 * Created by hanks on 2017/8/9.
 */

public class User implements Parcelable {
    public String avatar;
    public String name;
    public int age;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.avatar);
        dest.writeString(this.name);
        dest.writeInt(this.age);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.avatar = in.readString();
        this.name = in.readString();
        this.age = in.readInt();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

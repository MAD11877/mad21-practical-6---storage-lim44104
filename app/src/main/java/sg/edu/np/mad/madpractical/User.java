package sg.edu.np.mad.madpractical;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public String name, description;
    public int id;
    public boolean followed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public User(String name, String description, int id, boolean followed) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.followed = followed;
    }


    // Implementing Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.name);
        out.writeString(this.description);
        out.writeInt(this.id);
        out.writeInt(this.followed ? 1 : 0);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private User(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.id = in.readInt();
        this.followed = in.readInt() == 1;
    }
}

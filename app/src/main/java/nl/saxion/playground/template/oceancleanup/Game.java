package nl.saxion.playground.template.oceancleanup;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import nl.saxion.playground.template.lib.GameModel;
import nl.saxion.playground.template.oceancleanup.model.EntityCreator;

import nl.saxion.playground.template.oceancleanup.model.Submarine;

public class Game extends GameModel implements Serializable, Parcelable {

    public Game() {
    }

    protected Game(Parcel in) {
        this.actualWidth = in.readFloat();
        this.actualHeight = in.readFloat();
    }

    @Override
    public void start() {
        addEntity(new BackgroundOcean(this));

        addEntity(new Submarine(this));

        addEntity(new Collision(this));

        addEntity(new EntityCreator(this));
    }

    @Override
    public float getWidth() {
        // Virtual screen should be at least 100 wide and 100 high.
        return 100f * actualWidth / Math.min(actualWidth, actualHeight);
    }

    @Override
    public float getHeight() {
        // Virtual screen should be at least 100 wide and 100 high.
        return 100f * actualHeight / Math.min(actualWidth, actualHeight);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.actualWidth);
        dest.writeFloat(this.actualHeight);
    }

    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}

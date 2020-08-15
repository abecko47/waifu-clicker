package ua.kotsur.waifuclicker.data.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

public class Waifu {
    private Drawable image;
    private int id;
    public Waifu(Context context, int imageResource, int id) {
        this.image = ContextCompat.getDrawable(context, imageResource);
        this.id = id;
    }

    public Drawable getImage() {
        return image;
    }

    public int getId() {
        return id;
    }
}

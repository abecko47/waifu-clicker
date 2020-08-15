package ua.kotsur.waifuclicker.data.local;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Random;

import ua.kotsur.waifuclicker.R;
import ua.kotsur.waifuclicker.data.model.Waifu;

public class WaifuProvider {

    private static WaifuProvider instance;
    private Waifu current;

    private WaifuProvider() {

    }

    public static WaifuProvider getInstance() {
        if (instance == null) {
            instance = new WaifuProvider();
        }
        return instance;
    }

    public ArrayList<Waifu> getWaifus(Context context) {
        ArrayList<Waifu> waifus = new ArrayList<>();
        waifus.add(new Waifu(context, R.drawable.waifu1, 1));
        waifus.add(new Waifu(context, R.drawable.waifu2, 2));
        waifus.add(new Waifu(context, R.drawable.waifu3, 3));
        waifus.add(new Waifu(context, R.drawable.waifu4, 4));
        waifus.add(new Waifu(context, R.drawable.waifu5, 5));
        waifus.add(new Waifu(context, R.drawable.waifu6, 6));
        waifus.add(new Waifu(context, R.drawable.waifu7, 7));
        waifus.add(new Waifu(context, R.drawable.waifu8, 8));
        waifus.add(new Waifu(context, R.drawable.waifu9, 9));
        waifus.add(new Waifu(context, R.drawable.waifu10, 10));
        waifus.add(new Waifu(context, R.drawable.waifu11, 11));
        waifus.add(new Waifu(context, R.drawable.waifu12, 12));
        waifus.add(new Waifu(context, R.drawable.waifu13, 13));
        waifus.add(new Waifu(context, R.drawable.waifu14, 14));
        waifus.add(new Waifu(context, R.drawable.waifu15, 15));
        waifus.add(new Waifu(context, R.drawable.waifu16, 16));

        return waifus;
    }

    public Waifu getRandomWaifu(Context context) {
        Random rand = new Random();

        if (current == null) {
            current = getWaifus(context).get(rand.nextInt(getWaifus(context).size()));
        } else {
            ArrayList<Waifu> waifus = getWaifus(context);
            int index = 0;
            for (Waifu waifu : waifus) {
                if (waifu.getId() == current.getId())
                    break;
                index++;
            }
            waifus.remove(index);
            current = waifus.get(rand.nextInt(waifus.size()));
        }
        return current;
    }

    public int getLiked(Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        int defaultValue = 0;
        return sharedPref.getInt(activity.getString(R.string.waifu_liked_key), defaultValue);
    }

    public void setLiked(Activity activity, int likes) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(activity.getString(R.string.waifu_liked_key), likes);
        editor.apply();
    }
}

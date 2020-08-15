package ua.kotsur.waifuclicker.activities.splash;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import ua.kotsur.waifuclicker.R;
import ua.kotsur.waifuclicker.activities.main.MainActivity;
import ua.kotsur.waifuclicker.base.BaseActivity;
import ua.kotsur.waifuclicker.data.local.WaifuProvider;
import ua.kotsur.waifuclicker.utils.Constants;

public class SplashActivity extends BaseActivity {

    private ImageView waifu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        bindViews();
        setupUI();
        fadeIn();
        startMain();
    }

    private void setupUI() {
        waifu.setImageDrawable(WaifuProvider.getInstance().getRandomWaifu(this).getImage());
    }

    private void fadeIn() {
        Animation fadeIn = new AlphaAnimation(0, 1);

        fadeIn.setInterpolator(new DecelerateInterpolator()); //and this
        fadeIn.setDuration(Constants.FADE_IN_SPEED);

        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(fadeIn);

        waifu.startAnimation(animation);
    }

    @Override
    public void bindViews() {
        waifu = findViewById(R.id.waifu_splash);
    }

    private void startMain() {
        new Handler().postDelayed(() -> {
            activityContext.startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();
        }, Constants.FADE_IN_SPEED);
    }
}
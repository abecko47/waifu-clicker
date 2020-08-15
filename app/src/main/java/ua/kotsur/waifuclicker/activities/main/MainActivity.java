package ua.kotsur.waifuclicker.activities.main;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import ua.kotsur.waifuclicker.R;
import ua.kotsur.waifuclicker.base.BaseActivity;
import ua.kotsur.waifuclicker.data.local.WaifuProvider;

public class MainActivity extends BaseActivity {

    private ImageView likeWaifuImage;
    private ImageView likeWaifuFull;
    private TextView waifuLikedView;
    private int waifuLiked = 0;

    private boolean isOnAnimation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setupUI();
        initClickListeners();
    }

    private void setupUI() {
        likeWaifuFull.setVisibility(View.GONE);
        likeWaifuImage.setImageDrawable(WaifuProvider.getInstance().getRandomWaifu(this).getImage());
        waifuLiked = WaifuProvider.getInstance().getLiked(this);
        waifuLikedView.setText(getResources().getString(R.string.waifu_power, waifuLiked));
    }

    @Override
    public void bindViews() {
        likeWaifuFull = findViewById(R.id.heart_full);
        likeWaifuImage = findViewById(R.id.waifu);
        waifuLikedView = findViewById(R.id.waifu_power);
    }

    private void initClickListeners() {
        likeWaifuImage.setOnClickListener(l -> {
            if (!isOnAnimation) {
                animateWaifu();
                animateHeart();
                waifuLiked++;
                waifuLikedView.setText(getResources().getString(R.string.waifu_power, waifuLiked));
                WaifuProvider.getInstance().setLiked(this, waifuLiked);
            }
        });
    }

    private void animateHeart() {
        isOnAnimation = true;
        ValueAnimator anim = ValueAnimator.ofInt(likeWaifuFull.getMeasuredHeight(), Math.round(getResources().getDimension(R.dimen.heart_like)),
                -Math.round(getResources().getDimension(R.dimen.heart_like)));
        anim.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = likeWaifuFull.getLayoutParams();
            layoutParams.height = val;
            layoutParams.width = val;

            likeWaifuFull.setLayoutParams(layoutParams);
        });
        anim.setDuration(500);
        anim.start();
        new Handler().postDelayed(() -> likeWaifuFull.setVisibility(View.VISIBLE), 50);
        new Handler().postDelayed(() -> likeWaifuFull.setVisibility(View.GONE), 400);
        new Handler().postDelayed(() -> isOnAnimation = false, 500);
    }

    private void animateWaifu() {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(250);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setDuration(250);

        AnimationSet animation = new AnimationSet(false); //change to false
        animation.addAnimation(fadeOut);
        animation.addAnimation(fadeIn);
        likeWaifuImage.setAnimation(animation);
        likeWaifuImage.setImageDrawable(WaifuProvider.getInstance().getRandomWaifu(this).getImage());
    }
}
package ua.kotsur.waifuclicker.base;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements ActivityBinder {

    protected Context activityContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hideAppBar();
        activityContext = this;
    }

    private void hideAppBar() {
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
    }
}

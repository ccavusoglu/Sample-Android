package tr.org.sample.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by ccavusoglu on 30.03.2016.
 */
public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

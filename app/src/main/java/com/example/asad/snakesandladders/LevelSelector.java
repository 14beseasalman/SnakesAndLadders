package com.example.asad.snakesandladders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.etiennelawlor.discreteslider.library.ui.DiscreteSlider;
import com.facebook.shimmer.ShimmerFrameLayout;

public class LevelSelector extends AppCompatActivity implements View.OnClickListener {
    DiscreteSlider slider;
    int level=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selector);
        slider = (DiscreteSlider) findViewById(R.id.discrete_slider);
        slider.setOnDiscreteSliderChangeListener(new DiscreteSlider.OnDiscreteSliderChangeListener() {
            @Override
            public void onPositionChanged(int position) {
                level = position+1;

            }
        });
        ShimmerFrameLayout container =
                (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        container.startShimmerAnimation();
        findViewById(R.id.play_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.play_btn){
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("GAME_LEVEL", level);
            startActivity(intent);
        }
    }
}

package com.isolsgroup.demo;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

public class AboutUsActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener
{
    private SliderLayout mDemoSlider;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_about_us);
        mDemoSlider = findViewById(R.id.slider);

        HashMap<String,String> url_maps = new HashMap<String, String> ();
        url_maps.put("Hannibal", "https://media.allure.com/photos/5bfd4527a921cb2f21747a23/1:1/w_1600%2Cc_limit/ReDew.png");
        url_maps.put("Big Bang Theory", "https://media.allure.com/photos/5bfc01ced5590e42469e61eb/1:1/w_1600%2Cc_limit/CliniqueID.png");
        url_maps.put("House of Cards", "https://media.allure.com/photos/5c643030afca3e2c7cfc8048/1:1/w_1600%2Cc_limit/Joico.png");
        url_maps.put("Game of Thrones", "https://media.allure.com/photos/5bfc01cfc1aed93f02ec47a5/1:1/w_1600%2Cc_limit/LauraMercier.png");

        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView (this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation ());
        mDemoSlider.setDuration(4000);

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
}

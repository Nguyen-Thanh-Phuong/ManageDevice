package com.danang.managedevice.Controller;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.danang.managedevice.R;

import android.animation.Animator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import org.jetbrains.annotations.NotNull;

public class Animation_View {
    boolean show = true;
    Techniques tech_In =Techniques.SlideInUp,tech_Out = Techniques.SlideOutDown;
    View view;
    ImageView view_Drop;
    int duration=1000;
    YoYo.YoYoString rope=null;

    public Animation_View(View view, ImageView view_Drop) {
        this.view = view;
        this.view_Drop = view_Drop;

        setData();
    }



    public Animation_View(boolean dropdown, Techniques tech_In, Techniques tech_Out, View view, ImageView view_Drop, int duration) {
        this.show = dropdown;
        this.tech_In = tech_In;
        this.tech_Out = tech_Out;
        this.view = view;
        this.view_Drop = view_Drop;
        this.duration = duration;

        setData();
    }



    public Animation_View(boolean dropdown,@NotNull Techniques tech_In, @NotNull Techniques tech_Out, @NotNull View view,@NotNull ImageView view_Drop) {
        this.show = dropdown;
        this.tech_In = tech_In;
        this.tech_Out = tech_Out;
        this.view = view;
        this.view_Drop = view_Drop;


        setData();
    }

    private void setData()
    {
        view_Drop.setOnClickListener(v -> {
            handle_Animation();
        });

        handle_Animation();
    }
    public void Click()
    {
        handle_Animation();
    }
    public boolean hide()
    {
        if(show)
        {
            handle_Animation();
            return true;
        }
        return false;
    }
    private void handle_Animation()
    {
        view_Drop.setEnabled(false);

        if(rope!=null)
            rope.stop(true);
        Techniques tech;
        if(show)
            tech = this.tech_Out;
        else tech = this.tech_In;

        YoYo.AnimationComposer composer = YoYo.with(tech);

        if(rope == null && !show)
            composer.duration(0);
        else
            composer.duration(this.duration);
        rope = composer
                .pivot(YoYo.CENTER_PIVOT,YoYo.CENTER_PIVOT)
                .interpolate(new AccelerateDecelerateInterpolator())
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        if(!show)view.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        if(show)view.setVisibility(View.GONE);
                        show = !show;

                        if(show)
                            view_Drop.setImageResource(R.drawable.drop_down_64px);
                        else view_Drop.setImageResource(R.drawable.up_squared_64px);

                        view_Drop.setEnabled(true);

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                }).playOn(view);
    }


}

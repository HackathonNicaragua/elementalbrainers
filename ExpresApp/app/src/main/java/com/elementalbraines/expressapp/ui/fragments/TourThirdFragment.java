package com.elementalbraines.expressapp.ui.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.elementalbraines.expressapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Maurel on 26/11/2017.
 */

public class TourThirdFragment extends Fragment {


    @BindView(R.id.txvTourDescription)
    TextView txvTourDescription;
    @BindView(R.id.imvTourImage)
    ImageView imvTourImage;
    @BindView(R.id.relTourContent)
    RelativeLayout relTourContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tour_content, container, false);
        ButterKnife.bind(this, rootView);
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "RobotoCondensed-Light.ttf");
        txvTourDescription.setTypeface(font);
        txvTourDescription.setText("Expresate de la manera mas adecuada con las personas con incapacidad auditiva");
        relTourContent.setBackgroundResource(R.color.colorPrimary);
        imvTourImage.setImageResource(getResources().getIdentifier("tour3", "drawable", getContext().getPackageName()));
        return rootView;
    }
}

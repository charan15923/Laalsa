package com.cronlogy.charan.laalsa;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.arnaudguyon.smartfontslib.FontTextView;

public class SortFilterFragment extends DialogFragment {

    FontTextView sortTab,filterTab;
    View sortBorder,filterBorder;

    Typeface ubuntuBold,ubuntuRegular;

    LinearLayout sortLayout,filterLayout,parentPanel;

    ImageView close,refersh;
    FontTextView done;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_sort_filter, new LinearLayout(getActivity()), false);




        ubuntuBold=Typeface.createFromAsset(getActivity().getAssets(),"fonts/Ubuntu-B.ttf");
        ubuntuRegular=Typeface.createFromAsset(getActivity().getAssets(),"fonts/Ubuntu-R.ttf");

        parentPanel=(LinearLayout)view.findViewById(R.id.parentPanel);
        //slideDown(parentPanel);

        filterBorder=(View)view.findViewById(R.id.filterBorder);
        sortBorder=(View)view.findViewById(R.id.sortBorder);


        sortTab=(FontTextView)view.findViewById(R.id.sortTab);
        filterTab=(FontTextView)view.findViewById(R.id.filterTab);

        sortLayout=(LinearLayout)view.findViewById(R.id.sortLayout);
        filterLayout=(LinearLayout)view.findViewById(R.id.filterLayout);

        close=(ImageView)view.findViewById(R.id.close);
        refersh=(ImageView)view.findViewById(R.id.refresh);

        done=(FontTextView)view.findViewById(R.id.done);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // getActivity().onBackPressed();

                android.app.Fragment prev = getActivity().getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    DialogFragment df = (DialogFragment) prev;
                    df.dismiss();
                }
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getActivity().onBackPressed();

                done.performClick();
            }
        });

        sortTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortTab.setTypeface(ubuntuBold);
                filterTab.setTypeface(ubuntuRegular);
                sortBorder.setVisibility(View.VISIBLE);
                filterBorder.setVisibility(View.INVISIBLE);

                sortLayout.setVisibility(View.VISIBLE);
                filterLayout.setVisibility(View.GONE);
            }
        });

        filterTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortTab.setTypeface(ubuntuRegular);
                filterTab.setTypeface(ubuntuBold);
                sortBorder.setVisibility(View.INVISIBLE);
                filterBorder.setVisibility(View.VISIBLE);

                filterLayout.setVisibility(View.VISIBLE);
                sortLayout.setVisibility(View.GONE);
            }
        });

        sortTab.performClick();



        // Build dialog
        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setContentView(view);
        return builder;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                dialog.getWindow().setStatusBarColor(getActivity().getResources().getColor(R.color.red));
            }
        }

        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    public void slideDown(View view){
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                -view.getHeight(),                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(1000);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

}

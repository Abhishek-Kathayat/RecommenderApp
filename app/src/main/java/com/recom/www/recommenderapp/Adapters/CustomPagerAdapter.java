package com.recom.www.recommenderapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.recom.www.recommenderapp.R;

public class CustomPagerAdapter extends PagerAdapter {

    private static final String TAG = "";
    private String[] mResources = {
            "https://www.tropical-islands.de/fileadmin/_processed_/csm_TI_RESTAURANT_PALMBEACH4_RGB_2000x860_70f4da8db5.jpg",
            "https://mk0tainsights9mcv7wv.kinstacdn.com/wp-content/uploads/2018/01/premiumforrestaurants_0.jpg",
            "https://www.tropical-islands.de/fileadmin/_processed_/csm_TI_RESTAURANT_TROPICAL-GARDEN1_RGB_2000x860_c3a4238088.jpg",

    };
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public CustomPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @SuppressLint("CheckResult")
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);

        Glide.with(imageView.getContext())
                .load(mResources[position])
                .apply(new RequestOptions()
                               // .placeholder(R.drawable.ic_facebook)
                        .centerCrop()
                )
                .into(imageView);
        //imageView.setImageResource(mResources[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setAdjustViewBounds(true);
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
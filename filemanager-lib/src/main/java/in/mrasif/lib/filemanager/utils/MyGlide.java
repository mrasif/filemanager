package in.mrasif.lib.filemanager.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

public class MyGlide {

    public static void loadImage(Context context, ImageView view, String url, int resId) {
        if (null != url) {
            Glide.with(context)
                    .applyDefaultRequestOptions(RequestOptions.skipMemoryCacheOf(true))
                    .load(url)
                    .apply(new RequestOptions().placeholder(resId).error(resId))
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            int width = resource.getIntrinsicWidth();
                            int height = resource.getIntrinsicHeight();
                            float scale_factor = (float) height / (float) width;
                            view.setMinimumHeight((int) (view.getWidth() * scale_factor));
                            view.setImageDrawable(resource);
                        }
                    });
        } else {
            view.setImageResource(resId);
        }
    }

    public static void loadImage(Context context, ImageView view, String url, int resId, boolean cache) {
        if (null != url) {
            Glide.with(context)
                    .applyDefaultRequestOptions(RequestOptions.skipMemoryCacheOf(!cache))
                    .load( url)
                    .apply(new RequestOptions().placeholder(resId).error(resId))
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            int width = resource.getIntrinsicWidth();
                            int height = resource.getIntrinsicHeight();
                            float scale_factor = (float) height / (float) width;
                            view.setMinimumHeight((int) (view.getWidth() * scale_factor));
                            view.setImageDrawable(resource);
                        }
                    });
        } else {
            view.setImageResource(resId);
        }
    }

    public static void loadImage(Context context, ImageView view, String url, int resId, float scale_factor, boolean cache) {
        if (null != url) {
            Glide.with(context)
                    .applyDefaultRequestOptions(RequestOptions.skipMemoryCacheOf(!cache))
                    .load( url)
                    .apply(new RequestOptions().placeholder(resId).error(resId))
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            view.setMinimumHeight((int) (view.getWidth() * scale_factor));
                            view.setImageDrawable(resource);
                        }
                    });
        } else {
            view.setImageResource(resId);
        }
    }
}

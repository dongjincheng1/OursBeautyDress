package com.example.beautydress.view;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by JYX on 2016/7/11-10:16.
 */
public class CustomCachingGlideModule implements GlideModule
{
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);

        builder.setMemoryCache( new LruResourceCache( customMemoryCacheSize));
        builder.setBitmapPool( new LruBitmapPool( customBitmapPoolSize ));

        // set size
        int cacheSize100MegaBytes = 104857600;
        builder.setDiskCache(
        new ExternalCacheDiskCacheFactory(context,  cacheSize100MegaBytes));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}

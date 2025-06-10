package ru.dast_6_tino.advancedstateandsideeffects

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp
import ru.dast_6_tino.advancedstateandsideeffects.util.UnsplashSizingInterceptor

@HiltAndroidApp
class CraneApplication : Application(), ImageLoaderFactory {

    /**
     * Create the singleton [ImageLoader].
     * This is used by [rememberImagePainter] to load images in the app.
     */
    override fun newImageLoader(): ImageLoader = ImageLoader.Builder(this)
        .components { add(UnsplashSizingInterceptor) }
        .build()

}

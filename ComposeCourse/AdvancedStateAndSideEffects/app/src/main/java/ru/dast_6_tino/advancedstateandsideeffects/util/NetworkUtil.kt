package ru.dast_6_tino.advancedstateandsideeffects.util

import coil.intercept.Interceptor
import coil.request.ImageResult
import okhttp3.HttpUrl.Companion.toHttpUrl

/**
 * A Coil [Interceptor] which appends query params to Unsplash urls to request sized images.
 */
object UnsplashSizingInterceptor : Interceptor {

    override suspend fun intercept(chain: Interceptor.Chain): ImageResult {
        val data = chain.request.data
        if (data is String && data.startsWith("https://images.unsplash.com/photo-")) {
            val size = chain.size
            val url = data.toHttpUrl()
                .newBuilder()
                .addQueryParameter("w", size.width.toString())
                .addQueryParameter("h", size.height.toString())
                .build()
            val request = chain.request.newBuilder().data(url).build()
            return chain.proceed(request)
        }
        return chain.proceed(chain.request)
    }

}

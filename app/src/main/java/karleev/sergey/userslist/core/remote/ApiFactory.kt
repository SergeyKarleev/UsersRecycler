package karleev.sergey.userslist.core.remote

import karleev.sergey.userslist.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory {

    private val TIMEOUT = 60L
    private val WRITE_TIMEOUT = 120L
    private val CONNECT_TIMEOUT = 30L
    private val CACHE_SIZE = 100L * 1024 * 1024
    private val CACHE_RELOAD_TIMEOUT_SECONDS = 5

    //    val myCache = Cache(get().getCacheDir(), CACHE_SIZE)
    private val connectedHeader = "public, stale-if-error, max-age=$CACHE_RELOAD_TIMEOUT_SECONDS"
    private val disconnectedHeader = "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7


    //Creating Auth Interceptor to add api_key query in front of all the requests.
    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url
            .newBuilder()
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        val response = chain.proceed(newRequest)

        return@Interceptor response
    }

    //OkhttpClient for building http request url
    private val rushClient = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(rushClient)
        .baseUrl(BuildConfig.API_SERVER)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()


    val userApi = retrofit.create(RemoteApi.UserApi::class.java)

}


package com.valentinerutto.shamiri.di

import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.valentinerutto.shamiri.data.LocationDatabase
import com.valentinerutto.shamiri.data.LocationRepository
import com.valentinerutto.shamiri.data.remote.ApiService
import com.valentinerutto.shamiri.ui.LocationViewmodel
import com.valentinerutto.shamiri.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

val networkingModule = module {
    single<ApiService> { get<Retrofit>().create() }

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = when (BuildConfig.BUILD_TYPE) {
            "release" -> HttpLoggingInterceptor.Level.NONE
            else -> HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    single {
        val gson = GsonBuilder()
            .serializeNulls()
            .create()

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(get())
            .build()
    }


}
val repositoryModule = module {
    single { LocationRepository(get(),database().locationDao(),database().residentsDao()) }
}
val viewmodelModule = module{

    viewModel {  LocationViewmodel(get())}

}

val databaseModule=module{

    single { LocationDatabase.getDatabase(context = androidContext()) }


}

fun Scope.database() = get<LocationDatabase>()

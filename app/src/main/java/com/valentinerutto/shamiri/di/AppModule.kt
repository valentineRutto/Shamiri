package com.valentinerutto.shamiri.di

import com.valentinerutto.shamiri.data.LocationDatabase
import com.valentinerutto.shamiri.data.LocationRepository
import com.valentinerutto.shamiri.data.remote.ApiService
import com.valentinerutto.shamiri.data.remote.RetrofitClient.createOkClient
import com.valentinerutto.shamiri.data.remote.RetrofitClient.createRetrofit
import com.valentinerutto.shamiri.ui.LocationViewmodel
import com.valentinerutto.shamiri.utils.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit

val networkingModule = module {
    single<ApiService> { (get() as Retrofit).create(ApiService::class.java) }
    single { createOkClient() }

    single {
        createRetrofit(
            baseUrl = Constants.BASE_URL, get()
        )
    }

}
val repositoryModule = module {
    single { LocationRepository(get(), database().locationDao(), database().residentsDao()) }
}
val viewmodelModule = module {

    viewModel { LocationViewmodel(get()) }

}

val databaseModule = module {

    single { LocationDatabase.getDatabase(context = androidContext()) }


}

fun Scope.database() = get<LocationDatabase>()

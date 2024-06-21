package com.example.apppractice.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.apppractice.data.IApiService
import com.example.apppractice.data.LogUserRepository
import com.example.apppractice.data.MapsOrsRepository
import com.example.apppractice.dbroom.LogUserDataBase
import com.example.apppractice.dbroom.LogUserQueryDao
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleInject {

    /** Interceptor **/
    val logging = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build()

    var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    @Singleton
    @Provides
    fun provideRetrofit(retrofit: Retrofit): IApiService {
        return retrofit.create(IApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(/*okHttpClient: OkHttpClient*/): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openrouteservice.org/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    /** Inject data base room **/
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): LogUserDataBase {
        return Room.databaseBuilder(
            context,
            LogUserDataBase::class.java,
            "users_db"
        ).build()
    }

    @Provides
    fun provideUserDao(database: LogUserDataBase): LogUserQueryDao {
        return database.userDao()
    }

    @Provides
    fun provideProductRepository(productDao: LogUserQueryDao): LogUserRepository {
        return LogUserRepository(productDao)
    }

    @Provides
    fun provideContextRepository(service: IApiService, @ApplicationContext context: Context) = MapsOrsRepository(service, context)
}
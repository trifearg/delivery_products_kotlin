package ru.app.fastestdelivery.data.di

import android.content.Context
import androidx.room.Room
import ru.app.fastestdelivery.data.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.app.fastestdelivery.data.room.BagProductDao
import ru.app.fastestdelivery.data.room.ProductDao
import ru.app.fastestdelivery.data.room.UserDao

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val APP_DB_NAME = "APP_DB_NAME"

    @Provides
    fun providesDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            context = appContext,
            klass = AppDatabase::class.java,
            name = APP_DB_NAME
        ).build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()

    @Provides
    fun provideProductDao(appDatabase: AppDatabase): ProductDao = appDatabase.productDao()

    @Provides
    fun provideBagProductDao(appDatabase: AppDatabase): BagProductDao = appDatabase.bagProductDao()

}
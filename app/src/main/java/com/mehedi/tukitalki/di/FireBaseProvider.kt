package com.mehedi.tukitalki.di

import com.google.firebase.auth.FirebaseAuth
import com.mehedi.tukitalki.services.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FireBaseProvider {


    @Provides
    @Singleton
    fun provideAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

}
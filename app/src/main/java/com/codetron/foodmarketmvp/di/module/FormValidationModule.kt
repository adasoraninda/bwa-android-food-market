package com.codetron.foodmarketmvp.di.module

import com.codetron.foodmarketmvp.base.FormValidation
import com.codetron.foodmarketmvp.model.validation.SignInFormValidation
import com.codetron.foodmarketmvp.model.validation.SignUpFormValidation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
interface FormValidationModule {

    @Binds
    @SignInValidation
    fun provideSignInValidation(signInValidation: SignInFormValidation): FormValidation

    @Binds
    @SignUpValidation
    fun provideSignUpValidation(signUpValidation: SignUpFormValidation):FormValidation
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SignInValidation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SignUpValidation
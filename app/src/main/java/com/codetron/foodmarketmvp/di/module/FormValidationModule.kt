package com.codetron.foodmarketmvp.di.module

import com.codetron.foodmarketmvp.base.FormValidation
import com.codetron.foodmarketmvp.model.domain.validation.SignInFormValidation
import com.codetron.foodmarketmvp.model.domain.validation.SignUpAddressFormValidation
import com.codetron.foodmarketmvp.model.domain.validation.SignUpFormValidation
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
    fun bindSignInValidation(validation: SignInFormValidation): FormValidation

    @Binds
    @SignUpValidation
    fun bindSignUpValidation(validation: SignUpFormValidation): FormValidation

    @Binds
    @SignUpAddressValidation
    fun bindSignUpAddressValidation(validation: SignUpAddressFormValidation): FormValidation
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SignInValidation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SignUpValidation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SignUpAddressValidation
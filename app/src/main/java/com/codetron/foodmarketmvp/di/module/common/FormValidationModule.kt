package com.codetron.foodmarketmvp.di.module.common

import com.codetron.foodmarketmvp.base.FormValidation
import com.codetron.foodmarketmvp.model.validation.SignInFormValidation
import com.codetron.foodmarketmvp.model.validation.SignUpAddressFormValidation
import com.codetron.foodmarketmvp.model.validation.SignUpFormValidation
import dagger.Binds
import dagger.Module
import javax.inject.Qualifier

@Module
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
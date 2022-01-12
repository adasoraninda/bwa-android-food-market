package com.codetron.foodmarketmvp.model.validation

import com.codetron.foodmarketmvp.base.FormValidation
import javax.inject.Inject

class SignUpAddressFormValidation @Inject constructor() : FormValidation {

    companion object {
        const val KEY_PHONE = "PHONE "
        const val KEY_ADDRESS = "ADDRESS"
        const val KEY_HOUSE = "HOUSE"
        const val KEY_CITY = "CITY"

        private const val PHONE_INVALID_ERROR = "Phone number not valid"
        private const val ADDRESS_INVALID_ERROR = "Address not valid"
        private const val HOUSE_INVALID_ERROR = "House number not valid"
        private const val CITY_INVALID_ERROR = "City not valid"
    }

    override fun validateInput(input: Map<String, String?>): Map<String, String?>? {
        phoneNumberValidation(input[KEY_PHONE])?.let { return mapOf(Pair(KEY_PHONE, it)) }
        addressValidation(input[KEY_ADDRESS])?.let { return mapOf(Pair(KEY_ADDRESS, it)) }
        houseNumberValidation(input[KEY_HOUSE])?.let { return mapOf(Pair(KEY_HOUSE, it)) }
        cityNumberValidation(input[KEY_CITY])?.let { return mapOf(Pair(KEY_CITY, it)) }
        return null
    }

    private fun phoneNumberValidation(phoneNumber: String?): String? {
        if (phoneNumber.isNullOrEmpty() || phoneNumber.isNullOrBlank()) {
            return PHONE_INVALID_ERROR
        }
        return null
    }

    private fun addressValidation(address: String?): String? {
        if (address.isNullOrEmpty() || address.isNullOrBlank()) {
            return ADDRESS_INVALID_ERROR
        }
        return null
    }

    private fun houseNumberValidation(houseNumber: String?): String? {
        if (houseNumber.isNullOrEmpty() || houseNumber.isNullOrBlank()) {
            return HOUSE_INVALID_ERROR
        }
        return null
    }

    private fun cityNumberValidation(city: String?): String? {
        if (city.isNullOrEmpty() || city.isNullOrBlank()) {
            return CITY_INVALID_ERROR
        }
        return null
    }
}
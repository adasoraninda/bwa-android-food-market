package com.codetron.foodmarketmvp.base

interface FormValidation {

    fun validateInput(input:Map<String,String?>): Map<String, String?>?

}
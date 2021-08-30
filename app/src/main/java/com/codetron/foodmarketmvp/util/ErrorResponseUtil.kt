package com.codetron.foodmarketmvp.util

import com.codetron.foodmarketmvp.model.response.base.Meta
import com.codetron.foodmarketmvp.model.response.error.ErrorResponse
import org.json.JSONObject

class ErrorResponseUtil(json: String) : JSONObject(json) {
    private val meta = this.getJSONObject("meta")
    private val data = this.getJSONObject("data")

    fun getMeta(): Meta {
        return Meta(
            meta.getInt("code"),
            meta.getString("message"),
            meta.getString("status")
        )
    }

    fun getData(): ErrorResponse {
        return ErrorResponse(
            data.getString("message")
        )
    }
}
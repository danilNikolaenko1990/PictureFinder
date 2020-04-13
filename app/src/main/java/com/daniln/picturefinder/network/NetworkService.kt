package com.daniln.picturefinder.network

class NetworkService {
    private var mInstance: NetworkService? = null

    fun getInstance(): NetworkService? {
        if (mInstance == null) {
            mInstance = NetworkService()
        }
        return mInstance
    }
}
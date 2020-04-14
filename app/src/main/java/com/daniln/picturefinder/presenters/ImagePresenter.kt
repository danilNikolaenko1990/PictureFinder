package com.daniln.picturefinder.presenters

import android.util.Log
import com.daniln.picturefinder.domain.Image
import com.daniln.picturefinder.domain.ImageRepository
import com.daniln.picturefinder.network.ImagesFetcher
import com.daniln.picturefinder.network.ResultWrapper
import com.daniln.picturefinder.network.imageResponse.Resp
import com.daniln.picturefinder.network.safeApiCall
import com.daniln.picturefinder.ui.views.ImagesView
import kotlinx.coroutines.*
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope

@InjectViewState
class ImagePresenter(private val images: ImageRepository) :
    MvpPresenter<ImagesView>() {
    fun searchImages() {
        viewState.startLoading()
        presenterScope.launch(Dispatchers.Main) {
            val result =
                safeApiCall(Dispatchers.IO) {
                    images.getImages("nas", 1, 10)
                }

            when (result) {
                is ResultWrapper.NetworkError -> showNetworkError(Any())
                is ResultWrapper.GenericError -> result.error?.errors?.get(0)?.let {
                    showNetworkError(
                        it
                    )
                }
                is ResultWrapper.Success<List<Image>> -> showSuccess(result.value)
            }
        }
    }

    private fun showSuccess(images: List<Image>) {
        Log.i("SUCCESS", images.toString())
    }

    private fun showNetworkError(any: Any) {
        Log.e("IMAGES", any.toString())
    }
}
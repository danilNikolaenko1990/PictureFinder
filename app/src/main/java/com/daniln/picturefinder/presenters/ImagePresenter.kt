package com.daniln.picturefinder.presenters

import android.util.Log
import com.daniln.picturefinder.domain.ImageGalleryItem
import com.daniln.picturefinder.domain.ImageRepository
import com.daniln.picturefinder.network.ResultWrapper
import com.daniln.picturefinder.network.safeApiCall
import com.daniln.picturefinder.ui.views.ImagesView
import kotlinx.coroutines.*
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope

@InjectViewState
class ImagePresenter(private val repository: ImageRepository) :
    MvpPresenter<ImagesView>() {
    fun searchImages() {
        viewState.loadingStarted()
        presenterScope.launch(Dispatchers.Main) {
            val result =
                safeApiCall(Dispatchers.IO) {
                    repository.getImages("nas", 1, 10)
                }

            when (result) {
                is ResultWrapper.NetworkError -> showNetworkError()
                is ResultWrapper.GenericError -> showNetworkError()
                is ResultWrapper.Success<List<ImageGalleryItem>> -> showSuccess(result.value)
            }
        }
    }

    private fun showSuccess(images: List<ImageGalleryItem>) {
        viewState.loadingFinished()
        viewState.show(images)
    }

    private fun showNetworkError() {
//        Log.e("TAG", err.toString())
        viewState.loadingFailed()
    }
}
package com.daniln.picturefinder.presenters

import com.daniln.picturefinder.domain.ImageGalleryItem
import com.daniln.picturefinder.domain.ImageRepository
import com.daniln.picturefinder.domain.ResultWrapper
import com.daniln.picturefinder.domain.callWithWrappedResult
import com.daniln.picturefinder.ui.views.ImagesView
import kotlinx.coroutines.*
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope

@InjectViewState
class ImagePresenter(private val repository: ImageRepository) :
    MvpPresenter<ImagesView>() {
    fun searchImages(searchText: String) {
        viewState.loadingStarted()
        presenterScope.launch(Dispatchers.Main) {
            val result =
                callWithWrappedResult(Dispatchers.IO) {
                    repository.getImages(searchText, 1, 100)
                }

            when (result) {
                is ResultWrapper.Error -> showError()
                is ResultWrapper.Success<List<ImageGalleryItem>> -> showSuccess(result.value)
            }
        }
    }

    private fun showSuccess(images: List<ImageGalleryItem>) {
        viewState.loadingFinished()
        viewState.show(images)
    }

    private fun showError() {
        viewState.loadingFailed()
    }
}
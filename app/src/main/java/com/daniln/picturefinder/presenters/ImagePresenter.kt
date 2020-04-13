package com.daniln.picturefinder.presenters

import android.util.Log
import com.daniln.picturefinder.network.ImagesRepo
import com.daniln.picturefinder.ui.views.ImagesView
import kotlinx.coroutines.*
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope

@InjectViewState
class ImagePresenter(private val images: ImagesRepo) :
    MvpPresenter<ImagesView>() {
    fun searchImages() {
        viewState.startLoading()
        presenterScope.launch(Dispatchers.Main) {
            val images =
                withContext(Dispatchers.IO) {
                    images.getImages("nas", 1, 10)
                }

            Log.i("TAG", images.toString())


//            viewState.showWords(wordsRepresentations)
//            viewState.stopLoading()
        }
    }
}
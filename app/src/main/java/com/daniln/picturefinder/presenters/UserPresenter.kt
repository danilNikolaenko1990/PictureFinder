package com.daniln.picturefinder.presenters

import com.daniln.picturefinder.ui.views.ImagesView
import kotlinx.coroutines.*
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope

@InjectViewState
class UserPresenter () :
    MvpPresenter<ImagesView>() {
    fun searchImages() {
        viewState.startLoading()
        presenterScope.launch(Dispatchers.Main) {
            val words =
                withContext(Dispatchers.IO) {

                }


//            viewState.showWords(wordsRepresentations)
//            viewState.stopLoading()
        }
    }
}
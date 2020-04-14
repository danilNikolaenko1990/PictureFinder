package com.daniln.picturefinder.ui.views

import com.daniln.picturefinder.domain.ImageGalleryItem
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface ImagesView : MvpView {
    fun loadingStarted()
    fun loadingFinished()
    fun show(images: List<ImageGalleryItem>)
    fun loadingFailed()
}
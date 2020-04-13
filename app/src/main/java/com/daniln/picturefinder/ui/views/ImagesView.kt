package com.daniln.picturefinder.ui.views

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = SkipStrategy::class)
interface ImagesView : MvpView {
    fun startLoading()
}
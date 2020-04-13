package com.daniln.picturefinder.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.daniln.AndroidApplication
import com.daniln.picturefinder.R
import com.daniln.picturefinder.presenters.ImagePresenter
import com.daniln.picturefinder.ui.views.ImagesView
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class ImagesFragment : MvpAppCompatFragment(), ImagesView {
    @Inject
    @InjectPresenter
    lateinit var imagePresenter: ImagePresenter

    @ProvidePresenter
    fun providePresenter(): ImagePresenter {
        return imagePresenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity?.application as AndroidApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        imagePresenter.searchImages()

        return inflater.inflate(R.layout.fragment_images, container, false)
    }

    override fun startLoading() {
    }

    companion object {
        fun newInstance(): ImagesFragment {

            return ImagesFragment()
        }
    }
}

package com.daniln.picturefinder.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.daniln.AndroidApplication
import com.daniln.picturefinder.R
import com.daniln.picturefinder.domain.ImageGalleryItem
import com.daniln.picturefinder.presenters.ImagePresenter
import com.daniln.picturefinder.ui.adapters.ImageViewHolder
import com.daniln.picturefinder.ui.adapters.ImagesAdapter
import com.daniln.picturefinder.ui.views.ImagesView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_images.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class ImagesFragment : MvpAppCompatFragment(), ImagesView, ImageViewHolder.OnImageListener {
    @Inject
    @InjectPresenter
    lateinit var imagePresenter: ImagePresenter
    private var mImageAdapter: ImagesAdapter = ImagesAdapter(this)

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        rvElements.layoutManager = GridLayoutManager(activity, 3)

        rvElements.adapter = mImageAdapter
        rvElements.isVerticalScrollBarEnabled = true

        super.onActivityCreated(savedInstanceState)
    }

    override fun show(images: List<ImageGalleryItem>) {
        mImageAdapter.setup(images)
    }

    override fun loadingFailed() {
        Snackbar.make(imagesFragment, R.string.loading_failed, Snackbar.LENGTH_LONG)
            .show()
    }

    override fun loadingFinished() {
        ProgressBarLoading.visibility = View.GONE
    }

    override fun loadingStarted() {
        ProgressBarLoading.visibility = View.VISIBLE
    }

    override fun open(position: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        fun newInstance(): ImagesFragment {

            return ImagesFragment()
        }
    }
}

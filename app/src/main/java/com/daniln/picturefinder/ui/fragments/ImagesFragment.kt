package com.daniln.picturefinder.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.daniln.AndroidApplication
import com.daniln.picturefinder.R
import com.daniln.picturefinder.domain.ImageGalleryItem
import com.daniln.picturefinder.presenters.ImagePresenter
import com.daniln.picturefinder.ui.adapters.ImagesAdapter
import com.daniln.picturefinder.ui.views.ImagesView
import kotlinx.android.synthetic.main.fragment_images.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class ImagesFragment : MvpAppCompatFragment(), ImagesView {
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
        return inflater.inflate(R.layout.fragment_images, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.top_menu, menu);

        val searchItem = menu.findItem(R.id.menu_item_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                hideKeyboard()
                query?.let {
                    imagePresenter.searchImages(it)
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    imagePresenter.searchImages(it)
                }
                return true
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        rvElements.layoutManager = GridLayoutManager(activity, 2)
        rvElements.adapter = mImageAdapter
        rvElements.isVerticalScrollBarEnabled = true
        setHasOptionsMenu(true)

        super.onActivityCreated(savedInstanceState)
    }

    override fun show(images: List<ImageGalleryItem>) {
        mImageAdapter.setup(images)
    }

    override fun loadingFailed() {
        val toast = Toast.makeText(
            context,
            R.string.loading_failed, Toast.LENGTH_LONG
        )
        toast.show()
    }

    override fun loadingFinished() {
        ProgressBarLoading.visibility = View.GONE
    }

    override fun loadingStarted() {
        ProgressBarLoading.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance(): ImagesFragment {

            return ImagesFragment()
        }
    }

    private fun hideKeyboard() {
        val view = activity!!.currentFocus
        if (view != null) {
            val imm =
                activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}

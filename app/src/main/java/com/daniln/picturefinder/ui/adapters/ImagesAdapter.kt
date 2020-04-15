package com.daniln.picturefinder.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daniln.picturefinder.R
import com.daniln.picturefinder.domain.ImageGalleryItem

class ImagesAdapter(
    private var fragment: Fragment
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mImages: ArrayList<ImageGalleryItem> = ArrayList()

    fun setup(images: List<ImageGalleryItem>) {
        mImages.clear()
        mImages.addAll(images)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView: View = layoutInflater.inflate(R.layout.cell_image, parent, false)

        return ImageViewHolder(
            itemView
        )
    }

    override fun getItemCount(): Int {
        return mImages.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ImageViewHolder) {
            val imageView = holder.mImageView
            val image = mImages[position]

            Glide.with(fragment)
                .load(image.thumbUrl)
                .fitCenter()
                .centerCrop()
                .into(imageView)

            if (!image.location.isNullOrEmpty()) {
                holder.mLocationTextView.text = image.location
            } else {
                holder.mLocationTextView.text =
                    fragment.context?.resources?.getString(R.string.unknown)
            }

            image.likes?.let {
                holder.mLikesTextView.text = it.toString()
            }

            image.userName?.let {
                holder.mUserNameTextView.text = it
            }

            image.altDescription?.let {
                holder.mDescriptionTextView.text = it
            }
        }
    }
}

private class ImageViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    var mImageView: ImageView = itemView.findViewById(R.id.imageItem)
    var mLocationTextView: TextView = itemView.findViewById(R.id.textView_location)
    var mLikesTextView: TextView = itemView.findViewById(R.id.textView_Likes)
    var mUserNameTextView: TextView = itemView.findViewById(R.id.textView_userName)
    var mDescriptionTextView: TextView = itemView.findViewById(R.id.textView_description)
}
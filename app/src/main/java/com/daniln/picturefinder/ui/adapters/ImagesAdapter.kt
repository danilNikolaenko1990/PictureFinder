package com.daniln.picturefinder.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daniln.picturefinder.R
import com.daniln.picturefinder.domain.ImageGalleryItem

class ImagesAdapter(private var mOnWordListener: ImageViewHolder.OnImageListener) :
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
            itemView,
            mOnWordListener
        )
    }

    override fun getItemCount(): Int {
        return mImages.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ImageViewHolder) {
            holder.bind(mImages[position])
        }
    }
}

class ImageViewHolder(itemView: View, private var mOnImageListener: OnImageListener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private var mWordRusTextView: TextView = itemView.findViewById(R.id.imageUrl)

    init {
        itemView.setOnClickListener(this)
    }

    fun bind(imageItem: ImageGalleryItem) {
        mWordRusTextView.text = imageItem.thumbUrl
    }

    override fun onClick(v: View) {
        mOnImageListener.open(adapterPosition)
    }

    interface OnImageListener {
        fun open(position: Int)
    }
}
package ru.netology.kotlin.dz3_1standroidapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.netology.kotlin.dz3_1standroidapp.R
import ru.netology.kotlin.dz3_1standroidapp.adapter.postfeed.*
import ru.netology.kotlin.dz3_1standroidapp.dto.Post
import ru.netology.kotlin.dz3_1standroidapp.dto.PostType

const val VIEW_TYPE_POST = 1
const val VIEW_TYPE_REPOST = 2
const val VIEW_TYPE_REPLY = 3
const val VIEW_TYPE_EVENT = 4
const val VIEW_TYPE_VIDEO = 5
const val VIEW_TYPE_ADVERT = 6

fun viewTypeToPostType(viewType: Int) = when (viewType) {
    VIEW_TYPE_POST -> PostType.POST
    VIEW_TYPE_REPOST -> PostType.REPOST
    VIEW_TYPE_REPLY -> PostType.REPLY
    VIEW_TYPE_EVENT -> PostType.EVENT
    VIEW_TYPE_VIDEO -> PostType.VIDEO
    VIEW_TYPE_ADVERT -> PostType.ADVERT
    else -> TODO("unknown view type")
}

class PostAdapter(val list: List<Post>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewTypeToPostType(viewType)) {
            PostType.POST -> PostViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(
                    R.layout.post_feed_post_card,
                    parent,
                    false
                )
            )
            PostType.REPOST -> RepostViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(
                    R.layout.post_feed_repost_card,
                    parent,
                    false
                )
            )
            PostType.REPLY -> ReplyViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(
                    R.layout.post_feed_reply_card,
                    parent,
                    false
                )
            )
            PostType.EVENT -> EventViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(
                    R.layout.post_feed_event_card,
                    parent,
                    false
                )
            )
            PostType.VIDEO -> VideoViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(
                    R.layout.post_feed_video_card,
                    parent,
                    false
                )
            )
            PostType.ADVERT -> AdvertViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(
                    R.layout.post_feed_advert_card,
                    parent,
                    false
                )
            )

        }




    override fun getItemCount() = list.size

    override fun getItemId(position: Int) = list[position].id

    override fun getItemViewType(position: Int) = when (list[position].postType) {
        PostType.POST -> VIEW_TYPE_POST
        PostType.REPOST -> VIEW_TYPE_REPOST
        PostType.REPLY -> VIEW_TYPE_REPLY
        PostType.EVENT -> VIEW_TYPE_EVENT
        PostType.VIDEO -> VIEW_TYPE_VIDEO
        PostType.ADVERT -> VIEW_TYPE_ADVERT
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as BaseViewHolder) {
            bind(list[position])
        }
    }
}
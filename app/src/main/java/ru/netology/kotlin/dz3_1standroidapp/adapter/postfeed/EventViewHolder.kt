package ru.netology.kotlin.dz3_1standroidapp.adapter.postfeed

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import ru.netology.kotlin.dz3_1standroidapp.adapter.PostAdapter
import ru.netology.kotlin.dz3_1standroidapp.dto.Post
//Отличие вьюхолдеров постов разных типов задается этой строчкой (ниже).
//В каждом классе-вьюхолдере она ссылается на свою собственную форму вывода, поэтому
//несмотря на то, что в разных формах есть одинаковые элементы с одинаковыми именами,
//неопределенности не возникает, т.к. программа видит к какому конкретному элементу нужно
//обратиться:
import kotlinx.android.synthetic.main.post_feed_event_card.view.*


class EventViewHolder(adapter: PostAdapter, view: View)
    : BaseViewHolder(adapter, view) {
    init { //При инициализации вьюхолдера назначить универсальный лиснер кнопкам на форме
        with (itemView) {
            likeBtn.setOnClickListener(btnListener(likeQtyTv))
            commentBtn.setOnClickListener(btnListener(commentQtyTv))
            shareBtn.setOnClickListener(btnListener(shareQtyTv))
        }
    }

    //Абстрактный метод bind сделать реализованным, используя готовый общий для всех метод
    // родительского класса, указывая в качестве параметров элементы текущей конкретной формы.
    override fun bind(post: Post) {
        with (itemView) {
            super.bind(
                post,
                createdTv,
                authorTv,
                contentTv,
                likeQtyTv,
                commentQtyTv,
                shareQtyTv,
                likeBtn,
                commentBtn,
                shareBtn
            )
            //Дополнительно: Открыть карту по нажатию на кнопку locationBtn.
            //Координаты берутся из текущего элемента списка post и каждый раз разные
            locationBtn.setOnClickListener {
                val intent = Intent().apply {
                    action = Intent.ACTION_VIEW
                    data = Uri.parse("geo:${post.coordinates?.first},${post.coordinates?.second}")
                }
                context.startActivity(intent)
            }
            addressTv.text = post.address
        }

    }
}
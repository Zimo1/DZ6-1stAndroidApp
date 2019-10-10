package ru.netology.kotlin.dz3_1standroidapp.adapter.postfeed

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.netology.kotlin.dz3_1standroidapp.adapter.PostAdapter
import ru.netology.kotlin.dz3_1standroidapp.dto.Post
//Отличие вьюхолдеров постов разных типов задается этой строчкой (ниже).
//В каждом классе-вьюхолдере она ссылается на свою собственную форму вывода, поэтому
//несмотря на то, что в разных формах есть одинаковые элементы с одинаковыми именами,
//неопределенности не возникает, т.к. программа видит к какому конкретному элементу нужно
//обратиться:
import kotlinx.android.synthetic.main.post_feed_advert_card.view.*

class AdvertViewHolder(adapter: PostAdapter, view: View)
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
            //Дополнительно: открыть рекламный сайт по ссылке в post.reference
            pictureIv.setOnClickListener {
                val intent = Intent().apply {
                    action = Intent.ACTION_VIEW
                    data = Uri.parse(post.reference)
                }
                context.startActivity(intent)
            }
        }

    }
}
package ru.netology.kotlin.dz3_1standroidapp.adapter.postfeed

import android.graphics.Color
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.netology.kotlin.dz3_1standroidapp.R
import ru.netology.kotlin.dz3_1standroidapp.adapter.PostAdapter
import ru.netology.kotlin.dz3_1standroidapp.dto.Post


abstract class BaseViewHolder(val adapter: PostAdapter, view: View) : RecyclerView.ViewHolder(view) {
    //Класс служит основой (родителем) для вьюхолдеров постов различного типа.
    //В нем собрано всё общее, что есть у этих типов (поля, кнопки) и их обработка.
    //Особенности каждого типа постов будут обрабатываться в наследниках этого класса.

    // Константы - цвета текста надписей о количестве лайков и т.п. у кнопок
    val activeColor = Color.RED
    val inactiveColor = Color.BLACK

    //Функция bind заносит данные из списка постов в форму на экране.
    //Здесь эта функция представлена в двух вариантах, отличающихся набором параметров.
    // Первый из них - абстрактный, используется в адаптере (PostAdapter), т.к. там еще нет
    // определенности, какого типа вьюхолдер будет использоваться.
    // Второй - реализованный, в качестве параметров содержит общие элементы интерфейса, которые
    // присутствуют на всех формах (и имеют на них одинаковые названия, независимо от формы).
    // Этот вариант используется в наследниках BaseViewHolder.
    abstract fun bind(post: Post)
    open fun bind(post: Post, //Элемент списка с данными.
                  curCreatedTv: TextView, //Поля, которые есть у поста любого типа (общие).
                  curAuthorTv: TextView,
                  curContentTv: TextView,
                  curLikeQtyTv: TextView,
                  curCommentQtyTv: TextView,
                  curShareQtyTv: TextView,
                  curLikeBtn: ImageButton, //Кнопки, которые есть у поста любого типа (общие).
                  curCommentBtn: ImageButton,
                  curShareBtn: ImageButton) {
        with(itemView) {
            //Заполнить текстовые поля
            curCreatedTv.text = post.created
            curAuthorTv.text = post.author
            curContentTv.text = post.content
            //val adapterPos = adapterPosition
            //Перерисовать кнопки
            redrawCurBtn(curLikeBtn, curLikeQtyTv, post.likedByMe, post.likesQty)
            redrawCurBtn(curCommentBtn, curCommentQtyTv, post.commentedByMe, post.commentsQty)
            redrawCurBtn(curShareBtn, curShareQtyTv, post.sharedByMe, post.sharesQty)
        }
    } //-----Конец fun bind------

    //Создадим универсальный listener, работающий для любых наших 3 общих кнопок
    fun btnListener(qtyView: TextView) = View.OnClickListener {
            if (adapterPosition == RecyclerView.NO_POSITION)
                TODO("Что-то случилась с адаптером RecyclerView, он не видит текущую позицию")
            //Получить элемент списка list, соответствующий текущей позиции адаптера
            val post: Post = adapter.list[adapterPosition]
            var trigger =
                post::likedByMe //инициализация переменной для хранения ссылки на свойство-флаг нажатия
            var counter =
                post::likesQty //инициализация переменной для хранения ссылки на свойство-количество нажатий
            when (it.id) { //в зависимости от типа кнопки связать эти переменные с соответствующими полями
                R.id.likeBtn -> {
                    trigger = post::likedByMe
                    counter = post::likesQty
                }
                R.id.commentBtn -> {
                    trigger = post::commentedByMe
                    counter = post::commentsQty
                }
                R.id.shareBtn -> {
                    trigger = post::sharedByMe
                    counter = post::sharesQty
                }
            }
            if (trigger.get()) {//если метка ранее уже стояла, значит счетчик нужно уменьшить на 1
                counter.set(counter.get() - 1)
            } else {//если ранее не стояла - счетчик увеличить на 1
                counter.set(counter.get() + 1)
            }
            //далее саму метку инвертировать
            trigger.set(!trigger.get())
            //перерисовать текущую кнопку и надпись рядом с ней
            redrawCurBtn(it as ImageButton, qtyView, trigger.get(), counter.get())
            //adapter.notifyItemChanged(adapterPosition)

    } //--------Конец btnListener---------

    //Универсальная функция перерисовывает заданную кнопку и надпись рядом с ней (кол-во лайков
    //и т.п.), нажатые и не нажатые кнопки отличаются цветом картинки и надписи(красный/серый).
    fun redrawCurBtn(btn: ImageButton,
                     qtyView: TextView,
                     pressed: Boolean,
                     qty: Int) {
        when (btn.id) { //В зависимости от типа и состояния кнопки, картинки на ней разные:
            R.id.likeBtn -> {btn.setImageResource(if (pressed) R.drawable.ic_favorite_active_24dp
                                                  else R.drawable.ic_favorite_inactive_24dp)
            }
            R.id.commentBtn -> {btn.setImageResource(if (pressed) R.drawable.ic_chat_bubble_active_24dp
                                                  else R.drawable.ic_chat_bubble_inactive_24dp)

            }
            R.id.shareBtn -> {btn.setImageResource(if (pressed) R.drawable.ic_share_active_24dp
                                                  else R.drawable.ic_share_inactive_24dp)
            }
        }
        //Установить цвет надписи
        qtyView.setTextColor(if (pressed) activeColor else inactiveColor)
        //Присвоить значение счетчику нажатий на экране
        qtyView.text = qty.toString()
        //При нулевом значении количества нажатий не отображать надпись вообще
        qtyView.visibility = if (qty > 0) VISIBLE else INVISIBLE
    }
}
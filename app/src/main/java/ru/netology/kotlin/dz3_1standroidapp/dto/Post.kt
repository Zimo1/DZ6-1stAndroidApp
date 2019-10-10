package ru.netology.kotlin.dz3_1standroidapp.dto

enum class PostType { //применяемые типы постов
    POST,
    REPOST,
    REPLY,
    EVENT,
    VIDEO,
    ADVERT
}
open class Post (
    val id: Long,
    val author: String,
    val content: String? = null,
    val created: String, //дата создания поста
    var likedByMe: Boolean = false,
    var commentedByMe: Boolean = false,
    var sharedByMe: Boolean = false,
    var likesQty: Int = 1,
    var commentsQty: Int = 5,
    var sharesQty: Int = 2,
    val postType: PostType = PostType.POST,
    val address: String? = null,
    val coordinates: Pair<Double, Double>? = null,
    val source: Post? = null, //Для репоста и реплики - ссылка на исходный пост
    val reference: String? = null //Ссылка в Интернете для видео и рекламы
)
# Домашнее задание к лекции "Корутины"

Для каждой задачи создайте решение на базе Gradle и залейте его в GitHub.

Для этого ДЗ вы можете сдавать всё в виде одного проекта*, где финальный проект содержит решение всех трёх задач.

Примечание*: для первой задачи необходимо отдельно выложить обычный Java-проект (на базе Gradle), который сериализует данные в формат JSON.

Все проекты должны быть созданы с использованием Gradle (без него не принимаются).

Не забудьте про [.gitignore](../.gitignore)

## Задача №1 - Загрузка данных

Добавьте в своё приложение возможность отображения списка постов, загруженных по сети с GitHub.

Для этого, напишите одно новое приложение и доработайте существующее:
1. Обычный Java-проект (на базе Gradle), который с помощью библиотеки GSON сериализует данные в JSON
2. В своё Android-приложение добавьте корутины и Ktor, с помощью которых и загружайте данные при старте Activity.

Вся функциональность, описанная в предыдущих задачах должна работать:
1. Лайки/дизлайки
1. Количество просмотров
1. Поделиться (шаринг)
1. Отображение постов различных типов:
    1. События (адрес и геопозиция)
    1. Репосты
    1. Видео с Youtube

## Задача №2 - ProgressBar

Пользователи очень не любят, когда не понимают, что происходит с приложением.

Первая мысль, которая у них возникает: "приложение зависло!", соответственно, они пытаются ему всячески помочь - как можно активнее кликают, сворачивают, разворачивают, "убивают" и запускают снова.

Чтобы такого не происходило, необходимо отображать пользователю индикатор активности.

Используя `ContstraintLayout` (или `FrameLayout`) в качестве родительского, разместите виджет [`ProgressBar`](https://developer.android.com/reference/kotlin/android/widget/ProgressBar.html) со стилем `android:indeterminate="true"` по своему вкусу (обратите внимание, он должен быть поверх `RecyclerView`).

Виджет будет показан по умолчанию. Чтобы скрыть его, необходимо изменить свойство `visibility`:

```kotlin
progressBar.visibility = View.GONE
```

Доступные значения:
* `VISIBLE` - показывается
* `INVISIBLE` - не показывается, но занимает место на экране
* `GONE` - не показывается и не занимает место на экране

Естественно, скрывать `ProgressBar` нужно после окончания загрузки.

Примечание: [`FrameLayout`](https://developer.android.com/reference/kotlin/android/widget/FrameLayout.html) - это один из упрощённых видов разметки, который позволяет показывать один или несколько дочерних `View`. При этом самый нижний в дереве оказывается на самом верху. Для позиционирования дочерних виджетов используется атрибут `layout_gravity` в совокупности с `padding` и `margin`.

## Задача № 3 (необязательная) - Реклама

"Внимательный читатель" мог заметить, что в первой задаче ни слова не сказано про рекламные посты. И вот почему: мы не хотим сверх меры засорять ленту пользователя рекламой.

Поэтому было принято следующее решение: всю рекламу вынести в отдельный JSON и подгружать её, перемешивания с основным контентом.

Поскольку `async/await` мы ещё не проходили, мы будем делать это последовательно (всё это делается в одном `launch`):
1. Сначала грузите основные посты
1. Затем грузите рекламные посты
1. Всё аккуратно перемешиваете из серии (три контентных поста, один рекламный, три контентных, один рекламный).

Где и как перемешивать - мы оставляем целиком на ваше усмотрение, но одно из неплохих мест для этого - адаптер.

Напоминаем, что реклама выглядит примерно вот так:

![](./ad/ad.png)



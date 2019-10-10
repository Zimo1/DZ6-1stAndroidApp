package ru.netology.kotlin.dz3_1standroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import ru.netology.kotlin.dz3_1standroidapp.adapter.PostAdapter
import io.ktor.client.request.get
import kotlinx.coroutines.*
import ru.netology.kotlin.dz3_1standroidapp.client.Api
import ru.netology.kotlin.dz3_1standroidapp.dto.Post
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list: List<Post>
        launch {
            list = withContext(Dispatchers.IO) {
                Api.client.get<List<Post>>(Api.url)
            }
            Toast.makeText(this@MainActivity, "Length: ${list.size}", Toast.LENGTH_LONG).show()

            with(container) {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = PostAdapter(list)
            }
        }
    }

//    fun fetchData() = launch {
//            var list = withContext(Dispatchers.IO) {
//            Api.client.get<List<Post>>(Api.url)
//        }
//        Toast.makeText(this@MainActivity, "Length: ${list.size}", Toast.LENGTH_LONG).show()
//    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}

// Загрузка на гитхаб через командную строку в Терминале: git push origin master
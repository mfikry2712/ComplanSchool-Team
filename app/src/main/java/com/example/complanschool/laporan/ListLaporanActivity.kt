package com.example.complanschool.laporan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.complanschool.R
import com.example.complanschool.data.Laporan
import com.example.complanschool.databinding.ActivityListLaporanBinding
import org.json.JSONArray

class ListLaporanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListLaporanBinding
    private lateinit var rvLaporan: RecyclerView
    private val list = ArrayList<Laporan>()
    private lateinit var adapter: ListLaporanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListLaporanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvLaporan = findViewById(R.id.rv_laporan)
        rvLaporan.setHasFixedSize(true)

        adapter = ListLaporanAdapter(list)
//        viewRecycler.setHasFixedSize(true)

//        showProfile()
    }

    private fun showProfile() {
//        loading.visibility = View.VISIBLE
//        val client = AsyncHttpClient()
//        client.addHeader("User-Agent", "request")
//        client.addHeader("Authorization", "token $superKey")
//        val url = "https://api.github.com/users"
//        client.get(url, object : AsyncHttpResponseHandler() {
//            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
//                loading.visibility = View.INVISIBLE
//                val result = String(responseBody)
//                Log.d(MainActivity::class.java.simpleName, result)
//                val jsonArray = JSONArray(result)
//                for (i in 0 until jsonArray.length()) {
//                    val jsonObject = jsonArray.getJSONObject(i)
//                    val username: String = jsonObject.getString("login")
//                    showDetailProfile(username)
//                }
//            }
//
//            override fun onFailure(statusCode: Int, headers: Array<Header>?, responseBody: ByteArray?, error: Throwable) {
//                loading.visibility = View.INVISIBLE
//                Toast.makeText(this@MainActivity, "Undefined", Toast.LENGTH_LONG).show()
//            }
//        })
    }
}
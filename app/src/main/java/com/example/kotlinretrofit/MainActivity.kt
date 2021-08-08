package com.example.kotlinretrofit

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getMyData()
       Log.d("Halo","I have added data to master an created new branch Feature 1 ")
        Log.d("Halo","data to new branch Feature 1 ")
    }

    companion object {
        const val url: String = "https://jsonplaceholder.typicode.com/"
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        // retrofitData.enqueue() presee CTRL+ SHIFT+ spacebar - code is generated automatically
        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                val responsebody = response.body()!!

                val myStringBuilder = StringBuilder()
                for (mydata in responsebody) {
                    myStringBuilder.append(mydata.id)
                    myStringBuilder.append((mydata.title))
                    myStringBuilder.append("\n")
                }

                txtId.setText(myStringBuilder)
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d(TAG, "on Failure" + t.message)
            }
        })
    }
}
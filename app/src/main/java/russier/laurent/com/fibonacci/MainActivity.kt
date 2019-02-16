package russier.laurent.com.fibonacci

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val input = findViewById<EditText>(R.id.input)
        val result = findViewById<TextView>(R.id.result)

        button
            .setOnClickListener {
                Retrofit
                    .Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://xxxxx.cloudfunctions.net/")
                    .build()
                    .create(FibonacciService::class.java)
                    .getFibonacci(Index(input.text.toString().toInt()))
                    .enqueue(object : Callback<String> {
                        override fun onFailure(call: Call<String>, t: Throwable) {
                            result.text = t.message
                        }

                        override fun onResponse(call: Call<String>, response: Response<String>) {
                            result.text = response.body()
                        }
                    })
            }
    }
}

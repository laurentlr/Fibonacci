package russier.laurent.com.fibonacci

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface FibonacciService {

    @POST("fibonacci")
    fun getFibonacci(@Body index: Index): Call<String>

}
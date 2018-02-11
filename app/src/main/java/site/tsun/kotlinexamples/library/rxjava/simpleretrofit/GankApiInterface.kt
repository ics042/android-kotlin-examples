package site.tsun.kotlinexamples.library.rxjava.simpleretrofit

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface GankApiInterface {
    @GET("data/{category}/{count}/{page}")
    fun getCategoryData(@Path("category") category: String, @Path("count") count: Int, @Path("page") page: Int): Observable<GankResult>
}
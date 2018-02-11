package site.tsun.kotlinexamples.common.video

import io.reactivex.Observable
import retrofit2.http.GET


interface VideoApiInterface {
    @GET("video.php")
    fun getVideoData(): Observable<ArrayList<String>>
}
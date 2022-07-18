package com.example.closedprs.Network

import com.example.closedprs.Model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("repos/tarughiya/ClosedPRs/pulls?state=closed")
    suspend fun getPosts(): List<Post>
}
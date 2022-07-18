package com.example.closedprs.Repository

import com.example.closedprs.Model.Post
import com.example.closedprs.Network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostRepository @Inject constructor(private val apiService: ApiService) {

    fun getPost(): Flow<List<Post>> = flow {
        val response = apiService.getPosts()
        emit(response)
    }.flowOn(Dispatchers.IO)
}
package com.example.closedprs.Ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.closedprs.Adapter.PostAdapter
import com.example.closedprs.Model.Post
import com.example.closedprs.R
import com.example.closedprs.ViewModel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var errorText: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var postAdapter: PostAdapter
    private lateinit var postViewModel: PostViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUi()
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        postViewModel.getPost()
        postViewModel.postLiveData.observe(this, Observer { response ->
            postAdapter.updatePostList(response as ArrayList<Post>)
            progressBar.visibility = View.GONE
            if (response.isEmpty()) {
                errorText.visibility = View.VISIBLE
                errorText.text = getResources().getString(R.string.empty_list_error);

            } else {
                errorText.visibility = View.GONE
            }
        })
    }

    private fun setUi() {
        recyclerView = findViewById(R.id.recyclerView)
        errorText = findViewById(R.id.error_text)
        progressBar = findViewById(R.id.simpleProgressBar);
        postAdapter = PostAdapter(this, ArrayList())
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
    }
}
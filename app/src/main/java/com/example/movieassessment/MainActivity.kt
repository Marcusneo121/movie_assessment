package com.example.movieassessment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.movieassessment.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {
    lateinit var movieViewModel: MovieViewModel
//    lateinit var retrofitService: MovieService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setupViewModel()

//        val navHostFragment = supportFragmentManager.findFragmentById(androidx.navigation.fragment.R.id.nav_host_fragment_container) as NavHostFragment
//        val navController = navHostFragment.navController
    }

    private fun setupViewModel(){
//        val movieRepository = MovieRepository(MovieDatabase(this))
//        val viewModelProviderFactory = MovieViewModelFactory(application, movieRepository)
        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]

//        retrofitService = RetrofitInstance.getRetrofitInstance().create(MovieService::class.java)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(androidx.navigation.fragment.R.id.nav_host_fragment_container)
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
}
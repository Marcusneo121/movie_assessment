package com.example.movieassessment.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movieassessment.MainActivity
import com.example.movieassessment.R
import com.example.movieassessment.adapter.MovieAdapter
import com.example.movieassessment.databinding.FragmentHomeBinding
import com.example.movieassessment.databinding.FragmentLoginBinding
import com.example.movieassessment.model.SearchResult
import com.example.movieassessment.service.MovieService
import com.example.movieassessment.utils.RetrofitInstance
import com.example.movieassessment.viewmodel.MovieViewModel
import com.example.notesapproom.model.Movie
import retrofit2.Response

class HomeFragment : Fragment() {

    private var homeBinding: FragmentHomeBinding? = null
    private val binding get() = homeBinding!!

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    private val handler = Handler(Looper.getMainLooper())
    private var searchRunnable: Runnable? = null
    private val debouncePeriod: Long = 500 // 300 milliseconds debounce period

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })

        movieViewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
        setupHomeRecyclerView()

        movieViewModel.getAllMovies().observe(viewLifecycleOwner){ movie ->
            movieAdapter.differ.submitList(movie)
            updateUI(movie)
        }

        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    debounce {
                        movieViewModel.searchMovies(it.toString())
                    }

                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun debounce(action: () -> Unit) {
        searchRunnable?.let { handler.removeCallbacks(it) }
        searchRunnable = Runnable { action() }
        handler.postDelayed(searchRunnable!!, debouncePeriod)
    }

    private fun updateUI(note: List<Movie>?){
        if(note != null){
            if(note.isNotEmpty()){
                binding.tvMovieRating.visibility = View.GONE
                binding.movieRV.visibility = View.VISIBLE
            } else {
                binding.tvMovieRating.visibility = View.VISIBLE
                binding.movieRV.visibility = View.GONE
            }
        }
    }

    private fun setupHomeRecyclerView(){
        movieAdapter = MovieAdapter()
        binding.movieRV.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        activity?.let {
            movieViewModel.getAllMovies().observe(viewLifecycleOwner){ movie ->
                movieAdapter.differ.submitList(movie)
                updateUI(movie)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        homeBinding = null
    }
}
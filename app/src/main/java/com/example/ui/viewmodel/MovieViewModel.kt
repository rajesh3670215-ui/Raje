package com.example.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.DownloadQualityOption
import com.example.data.model.DownloadServer
import com.example.data.model.DownloadTask
import com.example.data.model.Movie
import com.example.data.model.MovieIndustry
import com.example.data.repository.MovieRepository
import com.example.downloader.DownloadEngine
import com.example.downloader.NativeDownloadHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

enum class AppTab(val title: String) {
    HOME("Discover"),
    SEARCH("Search"),
    DOWNLOADS("Downloader"),
    WATCHLIST("Library"),
    SETTINGS("Settings")
}

class MovieViewModel : ViewModel() {

    private val _currentTab = MutableStateFlow(AppTab.HOME)
    val currentTab: StateFlow<AppTab> = _currentTab.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedIndustry = MutableStateFlow(MovieIndustry.ALL)
    val selectedIndustry: StateFlow<MovieIndustry> = _selectedIndustry.asStateFlow()

    private val _selectedGenre = MutableStateFlow("All")
    val selectedGenre: StateFlow<String> = _selectedGenre.asStateFlow()

    private val _selectedSort = MutableStateFlow("Popularity")
    val selectedSort: StateFlow<String> = _selectedSort.asStateFlow()

    private val _watchlist = MutableStateFlow<Set<String>>(setOf("mov_oppenheimer", "mov_interstellar", "mov_parasite"))
    val watchlist: StateFlow<Set<String>> = _watchlist.asStateFlow()

    private val _selectedMovieDetail = MutableStateFlow<Movie?>(null)
    val selectedMovieDetail: StateFlow<Movie?> = _selectedMovieDetail.asStateFlow()

    private val _movieForDownloadSheet = MutableStateFlow<Movie?>(null)
    val movieForDownloadSheet: StateFlow<Movie?> = _movieForDownloadSheet.asStateFlow()

    private val _movieForPlayer = MutableStateFlow<Movie?>(null)
    val movieForPlayer: StateFlow<Movie?> = _movieForPlayer.asStateFlow()

    val downloadTasks: StateFlow<List<DownloadTask>> = DownloadEngine.tasks
    val isTurboMode: StateFlow<Boolean> = DownloadEngine.turboMode
    val isWifiOnly: StateFlow<Boolean> = DownloadEngine.wifiOnly

    val searchResults: StateFlow<List<Movie>> = combine(
        _searchQuery,
        _selectedIndustry,
        _selectedGenre,
        _selectedSort
    ) { query, industry, genre, sort ->
        MovieRepository.searchMovies(
            query = query,
            industry = industry,
            genre = genre,
            sortBy = sort
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        MovieRepository.getAllMovies()
    )

    fun setTab(tab: AppTab) {
        _currentTab.value = tab
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun selectIndustry(industry: MovieIndustry) {
        _selectedIndustry.value = industry
    }

    fun selectGenre(genre: String) {
        _selectedGenre.value = genre
    }

    fun selectSort(sort: String) {
        _selectedSort.value = sort
    }

    fun toggleWatchlist(movieId: String) {
        val current = _watchlist.value.toMutableSet()
        if (current.contains(movieId)) {
            current.remove(movieId)
        } else {
            current.add(movieId)
        }
        _watchlist.value = current
    }

    fun isWatchlisted(movieId: String): Boolean {
        return _watchlist.value.contains(movieId)
    }

    fun openMovieDetail(movie: Movie) {
        _selectedMovieDetail.value = movie
    }

    fun closeMovieDetail() {
        _selectedMovieDetail.value = null
    }

    fun openDownloadSheet(movie: Movie) {
        _movieForDownloadSheet.value = movie
    }

    fun closeDownloadSheet() {
        _movieForDownloadSheet.value = null
    }

    fun openPlayer(movie: Movie) {
        _movieForPlayer.value = movie
    }

    fun closePlayer() {
        _movieForPlayer.value = null
    }

    fun startFastDownload(
        context: Context,
        movie: Movie,
        quality: DownloadQualityOption,
        server: DownloadServer,
        audioTrack: String,
        format: String = "MP4",
        enableSystemExport: Boolean = false
    ) {
        DownloadEngine.startDownload(
            movie = movie,
            quality = quality,
            server = server,
            audioTrack = audioTrack,
            format = format,
            parallelThreads = if (isTurboMode.value) 16 else 8
        )

        if (enableSystemExport) {
            NativeDownloadHelper.downloadGalleryFileToStorage(
                context = context,
                url = movie.downloadUrl,
                fileName = "${movie.title}_${quality.label}",
                fileType = com.example.data.model.MovieFileType.VIDEO_MOVIE,
                extension = format.lowercase()
            )
        }

        closeDownloadSheet()
        _currentTab.value = AppTab.DOWNLOADS
    }

    fun pauseDownload(taskId: String) {
        DownloadEngine.pauseDownload(taskId)
    }

    fun resumeDownload(taskId: String) {
        DownloadEngine.resumeDownload(taskId)
    }

    fun deleteDownload(taskId: String) {
        DownloadEngine.cancelOrDeleteDownload(taskId)
    }

    fun setTurboMode(enabled: Boolean) {
        DownloadEngine.setTurboMode(enabled)
    }

    fun setWifiOnly(enabled: Boolean) {
        DownloadEngine.setWifiOnly(enabled)
    }
}

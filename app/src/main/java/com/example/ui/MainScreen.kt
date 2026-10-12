package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.downloader.DownloadEngine
import com.example.ui.components.FastDownloadBottomSheet
import com.example.ui.components.MoviePlayerDialog
import com.example.ui.screens.DownloadsScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MovieDetailScreen
import com.example.ui.screens.SearchScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.WatchlistScreen
import com.example.ui.theme.CinemaAmberGold
import com.example.ui.theme.CinemaBackground
import com.example.ui.theme.CinemaRed
import com.example.ui.theme.CinemaSurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.viewmodel.AppTab
import com.example.ui.viewmodel.MovieViewModel

@Composable
fun MainScreen(
    viewModel: MovieViewModel = viewModel()
) {
    val context = LocalContext.current
    val currentTab by viewModel.currentTab.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedIndustry by viewModel.selectedIndustry.collectAsState()
    val selectedGenre by viewModel.selectedGenre.collectAsState()
    val selectedSort by viewModel.selectedSort.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()
    val watchlistIds by viewModel.watchlist.collectAsState()
    val downloadTasks by viewModel.downloadTasks.collectAsState()
    val isTurboMode by viewModel.isTurboMode.collectAsState()
    val isWifiOnly by viewModel.isWifiOnly.collectAsState()

    val selectedDetailMovie by viewModel.selectedMovieDetail.collectAsState()
    val movieForDownload by viewModel.movieForDownloadSheet.collectAsState()
    val movieForPlayer by viewModel.movieForPlayer.collectAsState()

    val activeDownloadsCount = DownloadEngine.getActiveDownloadsCount()

    // Back handler for tab switching
    if (selectedDetailMovie != null) {
        BackHandler { viewModel.closeMovieDetail() }
    } else if (currentTab != AppTab.HOME) {
        BackHandler { viewModel.setTab(AppTab.HOME) }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = CinemaBackground,
        bottomBar = {
            if (selectedDetailMovie == null) {
                NavigationBar(
                    containerColor = CinemaSurfaceElevated,
                    modifier = Modifier
                        .windowInsetsPadding(WindowInsets.navigationBars)
                        .testTag("main_bottom_nav")
                ) {
                    NavigationBarItem(
                        selected = currentTab == AppTab.HOME,
                        onClick = { viewModel.setTab(AppTab.HOME) },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Discover",
                                modifier = Modifier.size(22.dp)
                            )
                        },
                        label = {
                            Text(
                                text = "Discover",
                                fontSize = 11.sp,
                                fontWeight = if (currentTab == AppTab.HOME) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            selectedTextColor = CinemaAmberGold,
                            indicatorColor = CinemaAmberGold,
                            unselectedIconColor = TextSecondary,
                            unselectedTextColor = TextSecondary
                        ),
                        modifier = Modifier.testTag("nav_item_home")
                    )

                    NavigationBarItem(
                        selected = currentTab == AppTab.SEARCH,
                        onClick = { viewModel.setTab(AppTab.SEARCH) },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                modifier = Modifier.size(22.dp)
                            )
                        },
                        label = {
                            Text(
                                text = "Search",
                                fontSize = 11.sp,
                                fontWeight = if (currentTab == AppTab.SEARCH) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            selectedTextColor = CinemaAmberGold,
                            indicatorColor = CinemaAmberGold,
                            unselectedIconColor = TextSecondary,
                            unselectedTextColor = TextSecondary
                        ),
                        modifier = Modifier.testTag("nav_item_search")
                    )

                    NavigationBarItem(
                        selected = currentTab == AppTab.DOWNLOADS,
                        onClick = { viewModel.setTab(AppTab.DOWNLOADS) },
                        icon = {
                            if (activeDownloadsCount > 0) {
                                BadgedBox(
                                    badge = {
                                        Badge(
                                            containerColor = CinemaRed,
                                            contentColor = Color.White
                                        ) {
                                            Text(
                                                text = "$activeDownloadsCount",
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Download,
                                        contentDescription = "Downloader",
                                        modifier = Modifier.size(22.dp)
                                    )
                                }
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Download,
                                    contentDescription = "Downloader",
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        },
                        label = {
                            Text(
                                text = "Downloads",
                                fontSize = 11.sp,
                                fontWeight = if (currentTab == AppTab.DOWNLOADS) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            selectedTextColor = CinemaAmberGold,
                            indicatorColor = CinemaAmberGold,
                            unselectedIconColor = TextSecondary,
                            unselectedTextColor = TextSecondary
                        ),
                        modifier = Modifier.testTag("nav_item_downloads")
                    )

                    NavigationBarItem(
                        selected = currentTab == AppTab.WATCHLIST,
                        onClick = { viewModel.setTab(AppTab.WATCHLIST) },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Bookmark,
                                contentDescription = "Library",
                                modifier = Modifier.size(22.dp)
                            )
                        },
                        label = {
                            Text(
                                text = "Library",
                                fontSize = 11.sp,
                                fontWeight = if (currentTab == AppTab.WATCHLIST) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            selectedTextColor = CinemaAmberGold,
                            indicatorColor = CinemaAmberGold,
                            unselectedIconColor = TextSecondary,
                            unselectedTextColor = TextSecondary
                        ),
                        modifier = Modifier.testTag("nav_item_watchlist")
                    )

                    NavigationBarItem(
                        selected = currentTab == AppTab.SETTINGS,
                        onClick = { viewModel.setTab(AppTab.SETTINGS) },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings",
                                modifier = Modifier.size(22.dp)
                            )
                        },
                        label = {
                            Text(
                                text = "Settings",
                                fontSize = 11.sp,
                                fontWeight = if (currentTab == AppTab.SETTINGS) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            selectedTextColor = CinemaAmberGold,
                            indicatorColor = CinemaAmberGold,
                            unselectedIconColor = TextSecondary,
                            unselectedTextColor = TextSecondary
                        ),
                        modifier = Modifier.testTag("nav_item_settings")
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (selectedDetailMovie != null) {
                MovieDetailScreen(
                    movie = selectedDetailMovie!!,
                    isWatchlisted = viewModel.isWatchlisted(selectedDetailMovie!!.id),
                    onToggleWatchlist = { viewModel.toggleWatchlist(it) },
                    onBack = { viewModel.closeMovieDetail() },
                    onDownloadClick = { viewModel.openDownloadSheet(it) },
                    onPlayClick = { viewModel.openPlayer(it) }
                )
            } else {
                when (currentTab) {
                    AppTab.HOME -> {
                        HomeScreen(
                            onMovieClick = { viewModel.openMovieDetail(it) },
                            onDownloadClick = { viewModel.openDownloadSheet(it) },
                            onPlayClick = { viewModel.openPlayer(it) },
                            onNavigateToSearch = { viewModel.setTab(AppTab.SEARCH) },
                            onSelectIndustrySearch = { viewModel.selectIndustry(it) },
                            onNavigateToLibrary = { viewModel.setTab(AppTab.WATCHLIST) }
                        )
                    }
                    AppTab.SEARCH -> {
                        SearchScreen(
                            searchQuery = searchQuery,
                            onQueryChange = { viewModel.onSearchQueryChange(it) },
                            selectedIndustry = selectedIndustry,
                            onIndustrySelect = { viewModel.selectIndustry(it) },
                            selectedGenre = selectedGenre,
                            onGenreSelect = { viewModel.selectGenre(it) },
                            selectedSort = selectedSort,
                            onSortSelect = { viewModel.selectSort(it) },
                            searchResults = searchResults,
                            onMovieClick = { viewModel.openMovieDetail(it) },
                            onDownloadClick = { viewModel.openDownloadSheet(it) }
                        )
                    }
                    AppTab.DOWNLOADS -> {
                        DownloadsScreen(
                            tasks = downloadTasks,
                            isTurboMode = isTurboMode,
                            onToggleTurboMode = { viewModel.setTurboMode(it) },
                            onPause = { viewModel.pauseDownload(it) },
                            onResume = { viewModel.resumeDownload(it) },
                            onDelete = { viewModel.deleteDownload(it) },
                            onPlayMovie = { viewModel.openPlayer(it) }
                        )
                    }
                    AppTab.WATCHLIST -> {
                        WatchlistScreen(
                            watchlistIds = watchlistIds,
                            onToggleWatchlist = { viewModel.toggleWatchlist(it) },
                            onMovieClick = { viewModel.openMovieDetail(it) },
                            onDownloadClick = { viewModel.openDownloadSheet(it) },
                            onPlayClick = { viewModel.openPlayer(it) }
                        )
                    }
                    AppTab.SETTINGS -> {
                        SettingsScreen(
                            isTurboMode = isTurboMode,
                            onToggleTurboMode = { viewModel.setTurboMode(it) },
                            isWifiOnly = isWifiOnly,
                            onToggleWifiOnly = { viewModel.setWifiOnly(it) }
                        )
                    }
                }
            }

            // Fast Download Sheet
            movieForDownload?.let { movie ->
                FastDownloadBottomSheet(
                    movie = movie,
                    onDismiss = { viewModel.closeDownloadSheet() },
                    onStartDownload = { quality, server, audio, format, systemExport ->
                        viewModel.startFastDownload(context, movie, quality, server, audio, format, systemExport)
                    }
                )
            }

            // Interactive Player Dialog
            movieForPlayer?.let { movie ->
                MoviePlayerDialog(
                    movie = movie,
                    onDismiss = { viewModel.closePlayer() }
                )
            }
        }
    }
}

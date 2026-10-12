package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Movie
import com.example.data.model.MovieIndustry
import com.example.data.repository.MovieRepository
import com.example.ui.components.FeaturedCarousel
import com.example.ui.components.MovieCard
import com.example.ui.theme.CinemaAmberGold
import com.example.ui.theme.CinemaBackground
import com.example.ui.theme.CinemaNeonCyan
import com.example.ui.theme.CinemaRed
import com.example.ui.theme.CinemaSurfaceElevated
import com.example.ui.theme.CinemaTurboGreen
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.ChevronRight

@Composable
fun HomeScreen(
    onMovieClick: (Movie) -> Unit,
    onDownloadClick: (Movie) -> Unit,
    onPlayClick: (Movie) -> Unit,
    onNavigateToSearch: () -> Unit,
    onSelectIndustrySearch: (MovieIndustry) -> Unit,
    onNavigateToLibrary: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val featuredMovies = MovieRepository.getFeaturedSpotlight()
    val allMovies = MovieRepository.getAllMovies()
    val hindiDubbedMovies = MovieRepository.getMoviesByIndustry(MovieIndustry.HINDI_DUBBED)
    val hollywoodMovies = MovieRepository.getMoviesByIndustry(MovieIndustry.HOLLYWOOD)
    val bollywoodMovies = MovieRepository.getMoviesByIndustry(MovieIndustry.BOLLYWOOD)
    val chineseMovies = MovieRepository.getMoviesByIndustry(MovieIndustry.CHINESE)
    val kCinemaMovies = MovieRepository.getMoviesByIndustry(MovieIndustry.K_CINEMA)
    val taiwanMovies = MovieRepository.getMoviesByIndustry(MovieIndustry.TAIWAN)
    val animatedMovies = MovieRepository.getMoviesByIndustry(MovieIndustry.ANIMATED)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(CinemaBackground)
            .testTag("home_screen"),
        contentPadding = PaddingValues(bottom = 90.dp)
    ) {
        // App Header Brand Bar
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(CinemaAmberGold),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Bolt,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Column {
                        Text(
                            text = "CINEWORLD",
                            color = TextPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "World Cinema & Fast Downloader",
                            color = CinemaAmberGold,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                // Quick Search Bar trigger button
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(CinemaSurfaceElevated)
                        .clickable { onNavigateToSearch() }
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = CinemaAmberGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Search Movies",
                        color = TextSecondary,
                        fontSize = 12.sp
                    )
                }
            }
        }

        // Hero Spotlight Carousel
        item {
            FeaturedCarousel(
                featuredMovies = featuredMovies,
                onMovieClick = onMovieClick,
                onDownloadClick = onDownloadClick,
                onPlayClick = onPlayClick
            )
        }

        // Fast Download Acceleration Banner
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = CinemaSurfaceElevated)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.horizontalGradient(
                                listOf(Color(0xFF1E2838), Color(0xFF131924))
                            )
                        )
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(CinemaTurboGreen),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Speed,
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Column {
                            Text(
                                text = "Turbo CDN Acceleration: ON",
                                color = TextPrimary,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "16 Parallel Stream Connections • Direct High-Speed",
                                color = CinemaNeonCyan,
                                fontSize = 11.sp
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(CinemaAmberGold)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "4K READY",
                            color = Color.Black,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }
        }

        // Quick Movie Gallery & Files Banner
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 2.dp)
                    .clickable { onNavigateToLibrary() },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = CinemaSurfaceElevated)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF28344A)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Collections,
                                contentDescription = null,
                                tint = CinemaAmberGold,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Column {
                            Text(
                                text = "Movie Gallery & Downloadable Files",
                                color = TextPrimary,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Download 4K posters, wallpapers & video files to gallery",
                                color = TextSecondary,
                                fontSize = 11.sp
                            )
                        }
                    }
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Open Gallery",
                        tint = CinemaAmberGold,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        // Industry / Region Quick Pills
        item {
            Column(modifier = Modifier.padding(top = 10.dp)) {
                Text(
                    text = "Explore Global Cinema by Region",
                    color = TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(MovieIndustry.entries) { industry ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(CinemaSurfaceElevated)
                                .clickable {
                                    onSelectIndustrySearch(industry)
                                    onNavigateToSearch()
                                }
                                .padding(horizontal = 12.dp, vertical = 7.dp)
                        ) {
                            Text(
                                text = "${industry.regionEmoji} ${industry.displayName}",
                                color = if (industry == MovieIndustry.ALL) CinemaAmberGold else TextPrimary,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }

        // Section: All Hindi Dubbed Unlimited Downloads
        item {
            MovieSectionRow(
                title = "🎙️ All Hindi Dubbed Movies",
                subtitle = "Unlimited downloads in 8K, 4K, 1080p, 720p with Dolby Audio",
                movies = hindiDubbedMovies,
                onMovieClick = onMovieClick,
                onDownloadClick = onDownloadClick
            )
        }

        // Section: Hollywood Global Hits (Hindi Dubbed)
        item {
            MovieSectionRow(
                title = "🇺🇸 Hollywood Movies (Hindi Dubbed)",
                subtitle = "Blockbusters with dual Hindi & English Dolby audio",
                movies = hollywoodMovies,
                onMovieClick = onMovieClick,
                onDownloadClick = onDownloadClick
            )
        }

        // Section: Bollywood Blockbusters
        item {
            MovieSectionRow(
                title = "🇮🇳 Bollywood & Indian Mega Hits",
                subtitle = "High-octane action, drama & pan-India hits in Hindi",
                movies = bollywoodMovies,
                onMovieClick = onMovieClick,
                onDownloadClick = onDownloadClick
            )
        }

        // Section: Chinese Cinema (Hindi Dubbed)
        item {
            MovieSectionRow(
                title = "🇨🇳 Chinese Movies (Hindi Dubbed)",
                subtitle = "Martial arts, Wuxia & massive sci-fi in Hindi",
                movies = chineseMovies,
                onMovieClick = onMovieClick,
                onDownloadClick = onDownloadClick
            )
        }

        // Section: Korean Cinema (Hindi Dubbed)
        item {
            MovieSectionRow(
                title = "🇰🇷 Korean Movies (Hindi Dubbed)",
                subtitle = "Award-winning thrillers & K-Cinema action in Hindi",
                movies = kCinemaMovies,
                onMovieClick = onMovieClick,
                onDownloadClick = onDownloadClick
            )
        }

        // Section: Taiwan Cinema (Hindi Dubbed)
        item {
            MovieSectionRow(
                title = "🇹🇼 Taiwan Movies (Hindi Dubbed)",
                subtitle = "Top Taiwanese mystery, horror & action in Hindi",
                movies = taiwanMovies,
                onMovieClick = onMovieClick,
                onDownloadClick = onDownloadClick
            )
        }

        // Section: Animated Movies (All Hindi Dubbed)
        item {
            MovieSectionRow(
                title = "🎌 Animated & Anime Movies (All Hindi Dubbed)",
                subtitle = "Hollywood 3D animation, Anime & Donghua in Hindi",
                movies = animatedMovies,
                onMovieClick = onMovieClick,
                onDownloadClick = onDownloadClick
            )
        }
    }
}

@Composable
fun MovieSectionRow(
    title: String,
    subtitle: String,
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit,
    onDownloadClick: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 22.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = title,
                color = TextPrimary,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
                color = TextSecondary,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(movies, key = { it.id }) { movie ->
                MovieCard(
                    movie = movie,
                    onMovieClick = onMovieClick,
                    onDownloadClick = onDownloadClick
                )
            }
        }
    }
}

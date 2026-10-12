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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkRemove
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.FolderZip
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SdStorage
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.model.Movie
import com.example.data.model.MovieFileType
import com.example.data.model.MovieGalleryFile
import com.example.data.repository.MovieRepository
import com.example.downloader.DownloadEngine
import com.example.downloader.NativeDownloadHelper
import com.example.ui.components.GalleryImageDialog
import com.example.ui.components.MovieGalleryItemCard
import com.example.ui.theme.CinemaAmberGold
import com.example.ui.theme.CinemaBackground
import com.example.ui.theme.CinemaNeonCyan
import com.example.ui.theme.CinemaRed
import com.example.ui.theme.CinemaSurfaceBorder
import com.example.ui.theme.CinemaSurfaceElevated
import com.example.ui.theme.CinemaTurboGreen
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun WatchlistScreen(
    watchlistIds: Set<String>,
    onToggleWatchlist: (String) -> Unit,
    onMovieClick: (Movie) -> Unit,
    onDownloadClick: (Movie) -> Unit,
    onPlayClick: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val allMovies = MovieRepository.getAllMovies()
    val watchlistedMovies = allMovies.filter { watchlistIds.contains(it.id) }
    val downloadedCount = DownloadEngine.getCompletedDownloadsCount()

    // 0: Watchlist, 1: 4K Gallery Art, 2: Movie Files Vault
    var libraryTab by remember { mutableIntStateOf(0) }
    var previewImageFile by remember { mutableStateOf<MovieGalleryFile?>(null) }

    val allGalleryArtFiles = remember(allMovies) {
        allMovies.flatMap { m ->
            m.galleryFiles.filter { it.fileType == MovieFileType.POSTER_IMAGE || it.fileType == MovieFileType.BACKDROP_IMAGE }
        }
    }

    val allMovieMediaFiles = remember(allMovies) {
        allMovies.flatMap { m ->
            m.galleryFiles.filter { it.fileType == MovieFileType.VIDEO_MOVIE || it.fileType == MovieFileType.VIDEO_TRAILER || it.fileType == MovieFileType.SUBTITLE_PACK }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CinemaBackground)
            .testTag("watchlist_screen")
    ) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Movie Library & Gallery",
                color = TextPrimary,
                fontSize = 22.sp,
                fontWeight = FontWeight.Black
            )
            Text(
                text = "Download movie files, 4K gallery wallpapers & offline video vault",
                color = TextSecondary,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Storage Breakdown Card
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = CinemaSurfaceElevated),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.SdStorage,
                                contentDescription = null,
                                tint = CinemaNeonCyan,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Device Storage & Media Gallery",
                                color = TextPrimary,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Text(
                            text = "$downloadedCount Movies Saved",
                            color = CinemaTurboGreen,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    LinearProgressIndicator(
                        progress = { 0.22f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(5.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = CinemaAmberGold,
                        trackColor = Color(0xFF28344A)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Gallery Files: 5.6 GB",
                            color = CinemaAmberGold,
                            fontSize = 10.sp
                        )
                        Text(
                            text = "Free Space: 48.2 GB",
                            color = TextSecondary,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }

        // Segmented Tab Controls (Watchlist | 4K Gallery Art | Movie Files)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(CinemaSurfaceElevated)
                .padding(3.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (libraryTab == 0) CinemaAmberGold else Color.Transparent)
                    .clickable { libraryTab = 0 }
                    .padding(vertical = 7.dp)
                    .testTag("tab_watchlist"),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Watchlist (${watchlistedMovies.size})",
                    color = if (libraryTab == 0) Color.Black else TextSecondary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                modifier = Modifier
                    .weight(1.1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (libraryTab == 1) CinemaAmberGold else Color.Transparent)
                    .clickable { libraryTab = 1 }
                    .padding(vertical = 7.dp)
                    .testTag("tab_gallery_art"),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "4K Gallery Art (${allGalleryArtFiles.size})",
                    color = if (libraryTab == 1) Color.Black else TextSecondary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (libraryTab == 2) CinemaAmberGold else Color.Transparent)
                    .clickable { libraryTab = 2 }
                    .padding(vertical = 7.dp)
                    .testTag("tab_movie_files"),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Files Vault",
                    color = if (libraryTab == 2) Color.Black else TextSecondary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Content Based on Selected Tab
        when (libraryTab) {
            0 -> {
                // WATCHLIST TAB
                if (watchlistedMovies.isEmpty()) {
                    EmptyLibraryState(
                        title = "Your Watchlist is empty",
                        message = "Bookmark movies from Discover or Search to download and watch later."
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(bottom = 90.dp)
                    ) {
                        items(watchlistedMovies, key = { it.id }) { movie ->
                            WatchlistMovieCard(
                                movie = movie,
                                onMovieClick = onMovieClick,
                                onDownloadClick = onDownloadClick,
                                onPlayClick = onPlayClick,
                                onRemove = { onToggleWatchlist(movie.id) }
                            )
                        }
                    }
                }
            }

            1 -> {
                // 4K MOVIE GALLERY & ARTWORK TAB
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 90.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "4K Posters & Panoramic Backdrops",
                                color = TextPrimary,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "One-tap Save to Gallery",
                                color = CinemaTurboGreen,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    items(allGalleryArtFiles, key = { it.id }) { file ->
                        MovieGalleryItemCard(
                            file = file,
                            movieTitle = file.title,
                            onDownloadToGallery = { f ->
                                NativeDownloadHelper.downloadGalleryFileToStorage(
                                    context = context,
                                    url = f.downloadUrl,
                                    fileName = f.title,
                                    fileType = f.fileType,
                                    extension = f.fileExtension
                                )
                            },
                            onPreview = { f ->
                                previewImageFile = f
                            },
                            onShare = { f ->
                                NativeDownloadHelper.shareMovieLinkOrFile(
                                    context = context,
                                    title = f.title,
                                    text = "4K Movie Gallery Art (${f.resolutionOrQuality})"
                                )
                            }
                        )
                    }
                }
            }

            2 -> {
                // MOVIE FILES VAULT TAB
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 90.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Direct Movie Video Files (.mp4 & .srt)",
                                color = TextPrimary,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "High Bitrate Encodes",
                                color = CinemaAmberGold,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    items(allMovieMediaFiles, key = { it.id }) { file ->
                        MovieGalleryItemCard(
                            file = file,
                            movieTitle = file.title,
                            onDownloadToGallery = { f ->
                                NativeDownloadHelper.downloadGalleryFileToStorage(
                                    context = context,
                                    url = f.downloadUrl,
                                    fileName = f.title,
                                    fileType = f.fileType,
                                    extension = f.fileExtension
                                )
                            },
                            onPreview = { f ->
                                val movie = allMovies.find { it.downloadUrl == f.downloadUrl } ?: allMovies.first()
                                onPlayClick(movie)
                            },
                            onShare = { f ->
                                NativeDownloadHelper.shareMovieLinkOrFile(
                                    context = context,
                                    title = f.title,
                                    text = "Direct Movie File (${f.sizeFormatted}, ${f.resolutionOrQuality})"
                                )
                            }
                        )
                    }
                }
            }
        }

        // Lightbox Image Dialog
        previewImageFile?.let { file ->
            GalleryImageDialog(
                file = file,
                onDismiss = { previewImageFile = null },
                onDownload = {
                    NativeDownloadHelper.downloadGalleryFileToStorage(
                        context = context,
                        url = file.downloadUrl,
                        fileName = file.title,
                        fileType = file.fileType,
                        extension = file.fileExtension
                    )
                },
                onShare = {
                    NativeDownloadHelper.shareMovieLinkOrFile(
                        context = context,
                        title = file.title,
                        text = "${file.title} (${file.sizeFormatted})"
                    )
                }
            )
        }
    }
}

@Composable
fun EmptyLibraryState(title: String, message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.Bookmark,
                contentDescription = null,
                tint = TextSecondary,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = title,
                color = TextPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = message,
                color = TextSecondary,
                fontSize = 12.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

@Composable
fun WatchlistMovieCard(
    movie: Movie,
    onMovieClick: (Movie) -> Unit,
    onDownloadClick: (Movie) -> Unit,
    onPlayClick: (Movie) -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onMovieClick(movie) }
            .testTag("watchlist_item_${movie.id}"),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CinemaSurfaceElevated)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = "${movie.title} poster",
                modifier = Modifier
                    .size(width = 68.dp, height = 96.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = movie.title,
                    color = TextPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                Text(
                    text = "${movie.industry.regionEmoji} ${movie.country} • ${movie.releaseYear}",
                    color = TextSecondary,
                    fontSize = 11.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = CinemaAmberGold,
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = "${movie.rating}/10",
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "• ${movie.durationMinutes}m",
                        color = TextSecondary,
                        fontSize = 11.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { onDownloadClick(movie) },
                        colors = ButtonDefaults.buttonColors(containerColor = CinemaAmberGold),
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Download,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Download",
                            color = Color.Black,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        onClick = { onPlayClick(movie) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF28344A)),
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Play",
                            color = Color.White,
                            fontSize = 11.sp
                        )
                    }
                }
            }

            IconButton(
                onClick = onRemove,
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.BookmarkRemove,
                    contentDescription = "Remove from watchlist",
                    tint = TextSecondary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

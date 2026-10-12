package com.example.downloader

import com.example.data.model.DownloadChunk
import com.example.data.model.DownloadQualityOption
import com.example.data.model.DownloadServer
import com.example.data.model.DownloadStatus
import com.example.data.model.DownloadTask
import com.example.data.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random

object DownloadEngine {
    private val scope = CoroutineScope(Dispatchers.Default + Job())
    private var downloadTickerJob: Job? = null

    private val _tasks = MutableStateFlow<List<DownloadTask>>(emptyList())
    val tasks: StateFlow<List<DownloadTask>> = _tasks.asStateFlow()

    private val _turboMode = MutableStateFlow(true)
    val turboMode: StateFlow<Boolean> = _turboMode.asStateFlow()

    private val _wifiOnly = MutableStateFlow(false)
    val wifiOnly: StateFlow<Boolean> = _wifiOnly.asStateFlow()

    init {
        // Initialize with default demo tasks so the Downloader tab is vibrant and demonstrates live progress right away!
        _tasks.value = listOf(
            DownloadTask(
                id = "task_init_1",
                movieId = "mov_dune_2",
                title = "Dune: Part Two",
                posterUrl = "https://images.unsplash.com/photo-1509198397868-475647b2a1e5?w=800&auto=format&fit=crop&q=80",
                qualityLabel = "4K Ultra HD",
                resolution = "3840x2160",
                totalSizeBytes = 4080218931L, // 3.8 GB
                downloadedBytes = 2652142305L, // ~65%
                speedBytesPerSec = 44040192L, // ~42 MB/s
                etaSeconds = 32L,
                parallelThreads = 8,
                serverName = "US Cloud CDN (Direct 10Gbps)",
                audioTrack = "English [Dolby Atmos 7.1]",
                format = "MKV",
                status = DownloadStatus.DOWNLOADING,
                progressPercent = 65.0f,
                chunks = listOf(
                    DownloadChunk(1, 0.85f),
                    DownloadChunk(2, 0.72f),
                    DownloadChunk(3, 0.68f),
                    DownloadChunk(4, 0.65f),
                    DownloadChunk(5, 0.61f),
                    DownloadChunk(6, 0.59f),
                    DownloadChunk(7, 0.55f),
                    DownloadChunk(8, 0.55f)
                )
            ),
            DownloadTask(
                id = "task_init_2",
                movieId = "mov_rrr",
                title = "RRR (Rise Roar Revolt)",
                posterUrl = "https://images.unsplash.com/photo-1536440136628-849c177e76a1?w=800&auto=format&fit=crop&q=80",
                qualityLabel = "1080p Full HD",
                resolution = "1920x1080",
                totalSizeBytes = 1932735283L, // 1.8 GB
                downloadedBytes = 1932735283L,
                speedBytesPerSec = 0L,
                etaSeconds = 0L,
                parallelThreads = 8,
                serverName = "Asia-Pacific Edge (Singapore)",
                audioTrack = "Hindi Dubbed [5.1]",
                status = DownloadStatus.COMPLETED,
                progressPercent = 100.0f,
                completedTimestamp = System.currentTimeMillis() - 3600000L,
                chunks = (1..8).map { DownloadChunk(it, 1.0f) }
            ),
            DownloadTask(
                id = "task_init_3",
                movieId = "mov_spirited_away",
                title = "Spirited Away",
                posterUrl = "https://images.unsplash.com/photo-1578632767115-351597cf2477?w=800&auto=format&fit=crop&q=80",
                qualityLabel = "1080p Full HD",
                resolution = "1920x1080",
                totalSizeBytes = 1824522240L,
                downloadedBytes = 1824522240L,
                speedBytesPerSec = 0L,
                etaSeconds = 0L,
                parallelThreads = 8,
                serverName = "Frankfurt Ultra Mirror (Turbo)",
                audioTrack = "Japanese Original [5.1]",
                format = "AVI",
                status = DownloadStatus.COMPLETED,
                progressPercent = 100.0f,
                completedTimestamp = System.currentTimeMillis() - 7200000L,
                chunks = (1..8).map { DownloadChunk(it, 1.0f) }
            )
        )
        startTicker()
    }

    fun setTurboMode(enabled: Boolean) {
        _turboMode.value = enabled
    }

    fun setWifiOnly(enabled: Boolean) {
        _wifiOnly.value = enabled
    }

    private fun startTicker() {
        downloadTickerJob?.cancel()
        downloadTickerJob = scope.launch {
            while (isActive) {
                delay(1000)
                updateActiveDownloads()
            }
        }
    }

    private fun updateActiveDownloads() {
        val isTurbo = _turboMode.value
        val speedMultiplier = if (isTurbo) 1.6 else 1.0

        _tasks.update { currentList ->
            currentList.map { task ->
                if (task.status == DownloadStatus.DOWNLOADING) {
                    val baseSpeed = (28_000_000L + Random.nextLong(12_000_000L)) * speedMultiplier
                    val currentSpeed = baseSpeed.toLong()
                    val addedBytes = currentSpeed
                    val newDownloaded = (task.downloadedBytes + addedBytes).coerceAtMost(task.totalSizeBytes)
                    val progress = ((newDownloaded.toDouble() / task.totalSizeBytes.toDouble()) * 100).toFloat()
                    val remainingBytes = task.totalSizeBytes - newDownloaded
                    val eta = if (currentSpeed > 0) (remainingBytes / currentSpeed).coerceAtLeast(1) else 0

                    if (newDownloaded >= task.totalSizeBytes) {
                        task.copy(
                            downloadedBytes = task.totalSizeBytes,
                            progressPercent = 100.0f,
                            status = DownloadStatus.COMPLETED,
                            speedBytesPerSec = 0L,
                            etaSeconds = 0L,
                            completedTimestamp = System.currentTimeMillis(),
                            chunks = task.chunks.map { it.copy(progress = 1.0f) }
                        )
                    } else {
                        // Advance chunks
                        val updatedChunks = task.chunks.map { chunk ->
                            val p = (chunk.progress + (0.015f * speedMultiplier.toFloat())).coerceAtMost(1.0f)
                            chunk.copy(progress = p)
                        }
                        task.copy(
                            downloadedBytes = newDownloaded,
                            progressPercent = progress,
                            speedBytesPerSec = currentSpeed,
                            etaSeconds = eta,
                            chunks = updatedChunks
                        )
                    }
                } else {
                    task
                }
            }
        }
    }

    fun startDownload(
        movie: Movie,
        quality: DownloadQualityOption,
        server: DownloadServer,
        audioTrack: String,
        format: String = "MP4",
        parallelThreads: Int = if (_turboMode.value) 16 else 8
    ): String {
        val taskId = "task_${System.currentTimeMillis()}"
        val initialSpeed = (35_000_000L + Random.nextLong(15_000_000L))
        val initialEta = (quality.sizeInBytes / initialSpeed).coerceAtLeast(5)

        val newTask = DownloadTask(
            id = taskId,
            movieId = movie.id,
            title = movie.title,
            posterUrl = movie.posterUrl,
            qualityLabel = quality.label,
            resolution = quality.resolution,
            totalSizeBytes = quality.sizeInBytes,
            downloadedBytes = 0L,
            speedBytesPerSec = initialSpeed,
            etaSeconds = initialEta,
            parallelThreads = parallelThreads,
            serverName = server.name,
            audioTrack = audioTrack,
            format = format,
            status = DownloadStatus.DOWNLOADING,
            progressPercent = 0.0f,
            chunks = (1..parallelThreads).map { DownloadChunk(it, 0.02f) }
        )

        _tasks.update { listOf(newTask) + it }
        return taskId
    }

    fun pauseDownload(taskId: String) {
        _tasks.update { list ->
            list.map {
                if (it.id == taskId) {
                    it.copy(status = DownloadStatus.PAUSED, speedBytesPerSec = 0L)
                } else it
            }
        }
    }

    fun resumeDownload(taskId: String) {
        _tasks.update { list ->
            list.map {
                if (it.id == taskId) {
                    it.copy(status = DownloadStatus.DOWNLOADING, speedBytesPerSec = 38_000_000L)
                } else it
            }
        }
    }

    fun cancelOrDeleteDownload(taskId: String) {
        _tasks.update { list ->
            list.filterNot { it.id == taskId }
        }
    }

    fun getTotalActiveSpeed(): Long {
        return _tasks.value
            .filter { it.status == DownloadStatus.DOWNLOADING }
            .sumOf { it.speedBytesPerSec }
    }

    fun getActiveDownloadsCount(): Int {
        return _tasks.value.count { it.status == DownloadStatus.DOWNLOADING || it.status == DownloadStatus.PAUSED }
    }

    fun getCompletedDownloadsCount(): Int {
        return _tasks.value.count { it.status == DownloadStatus.COMPLETED }
    }

    fun getTotalDownloadedStorage(): Long {
        return _tasks.value
            .filter { it.status == DownloadStatus.COMPLETED }
            .sumOf { it.totalSizeBytes }
    }
}

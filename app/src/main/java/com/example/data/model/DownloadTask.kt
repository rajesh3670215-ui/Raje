package com.example.data.model

enum class DownloadStatus {
    QUEUED,
    DOWNLOADING,
    PAUSED,
    COMPLETED,
    FAILED
}

data class DownloadChunk(
    val id: Int,
    var progress: Float // 0f to 1f
)

data class DownloadTask(
    val id: String,
    val movieId: String,
    val title: String,
    val posterUrl: String,
    val qualityLabel: String,
    val resolution: String,
    val totalSizeBytes: Long,
    val downloadedBytes: Long,
    val speedBytesPerSec: Long,
    val etaSeconds: Long,
    val parallelThreads: Int,
    val serverName: String,
    val audioTrack: String,
    val format: String = "MP4", // "MP4", "MKV", "AVI"
    val status: DownloadStatus,
    val progressPercent: Float, // 0f to 100f
    val filePath: String = "/storage/emulated/0/Download/CineWorld/",
    val completedTimestamp: Long? = null,
    val chunks: List<DownloadChunk> = (1..parallelThreads).map { DownloadChunk(it, 0f) }
) {
    val downloadedFormatted: String
        get() = formatBytes(downloadedBytes)

    val totalFormatted: String
        get() = formatBytes(totalSizeBytes)

    val speedFormatted: String
        get() = "${formatBytes(speedBytesPerSec)}/s"

    val etaFormatted: String
        get() {
            if (status == DownloadStatus.COMPLETED) return "Done"
            if (speedBytesPerSec <= 0) return "--"
            val m = etaSeconds / 60
            val s = etaSeconds % 60
            return if (m > 0) "${m}m ${s}s left" else "${s}s left"
        }

    val fileName: String
        get() = "${title.replace("[^a-zA-Z0-9]".toRegex(), "_")}_${qualityLabel.replace(" ", "_")}.${format.lowercase()}"

    companion object {
        fun formatBytes(bytes: Long): String {
            if (bytes <= 0) return "0 MB"
            val gb = bytes.toDouble() / (1024 * 1024 * 1024)
            if (gb >= 1.0) {
                return String.format("%.2f GB", gb)
            }
            val mb = bytes.toDouble() / (1024 * 1024)
            return String.format("%.1f MB", mb)
        }
    }
}

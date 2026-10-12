package com.example.data.model

enum class MovieIndustry(val displayName: String, val regionEmoji: String) {
    ALL("All Movies", "🌍"),
    HINDI_DUBBED("All Hindi Dubbed", "🎙️"),
    HOLLYWOOD("Hollywood", "🇺🇸"),
    BOLLYWOOD("Bollywood", "🇮🇳"),
    CHINESE("Chinese Cinema", "🇨🇳"),
    K_CINEMA("Korean Cinema", "🇰🇷"),
    TAIWAN("Taiwan Cinema", "🇹🇼"),
    ANIMATED("Animated & Anime", "🎌"),
    NOLLYWOOD("Nollywood", "🇳🇬"),
    EUROPEAN("European Cinema", "🇪🇺"),
    LATIN_AMERICA("Latin Cinema", "🇲🇽")
}

enum class MovieContainerFormat(val extension: String, val displayName: String, val badge: String) {
    MP4("mp4", "MP4 Video", "Universal Compatible"),
    MKV("mkv", "MKV Video", "Multi-Audio & Subs"),
    AVI("avi", "AVI Video", "Classic Format")
}

enum class MovieFileType(val label: String, val iconEmoji: String) {
    VIDEO_MOVIE("Full Movie File", "🎬"),
    VIDEO_TRAILER("Trailer / Sample Clip", "🎞️"),
    POSTER_IMAGE("4K Poster Art", "🖼️"),
    BACKDROP_IMAGE("4K Backdrop Wallpaper", "🌌"),
    SUBTITLE_PACK("Subtitle Track", "📝")
}

data class MovieGalleryFile(
    val id: String,
    val title: String,
    val fileType: MovieFileType,
    val fileExtension: String,       // "mp4", "mkv", "avi", "jpg", "srt"
    val resolutionOrQuality: String, // "7680x4320 (8K)", "3840x2160 (4K)", "1080p FHD", "720p HD"
    val sizeFormatted: String,       // "8.6 GB", "3.8 GB", "1.8 GB", "890 MB"
    val downloadUrl: String,
    val previewUrl: String? = null,
    val description: String = ""
)

data class DownloadQualityOption(
    val label: String,          // e.g. "8K Ultra HD", "4K Ultra HD", "1080p Full HD", "720p HD"
    val resolution: String,     // "7680x4320", "3840x2160", "1920x1080", "1280x720"
    val fileSizeFormatted: String, // "8.6 GB", "3.8 GB", "1.8 GB", "890 MB"
    val sizeInBytes: Long,
    val codec: String,          // "AV1 12-Bit HDR", "HEVC 10-Bit HDR", "x265 High"
    val audioChannels: String,  // "Dolby Atmos 9.2", "Dolby Atmos 7.1", "5.1 Surround"
    val estimatedSpeed: String  // "55 MB/s Turbo", "45 MB/s"
)

data class DownloadServer(
    val id: String,
    val name: String,
    val location: String,
    val speedRating: String, // "🚀 98 MB/s"
    val pingMs: Int
)

val DEFAULT_QUALITY_OPTIONS = listOf(
    DownloadQualityOption(
        label = "8K Ultra HD",
        resolution = "7680x4320",
        fileSizeFormatted = "8.6 GB",
        sizeInBytes = 9234125824L,
        codec = "AV1 / HEVC 12-bit Master",
        audioChannels = "Hindi Dubbed Dolby Atmos 9.1",
        estimatedSpeed = "58 MB/s Hyper"
    ),
    DownloadQualityOption(
        label = "4K Ultra HD",
        resolution = "3840x2160",
        fileSizeFormatted = "3.8 GB",
        sizeInBytes = 4080218931L,
        codec = "HEVC 10-bit HDR",
        audioChannels = "Hindi Dubbed Dolby Atmos 7.1",
        estimatedSpeed = "45 MB/s Turbo"
    ),
    DownloadQualityOption(
        label = "1080p Full HD",
        resolution = "1920x1080",
        fileSizeFormatted = "1.8 GB",
        sizeInBytes = 1932735283L,
        codec = "x265 High Profile",
        audioChannels = "Hindi Dubbed 5.1 Surround",
        estimatedSpeed = "35 MB/s"
    ),
    DownloadQualityOption(
        label = "720p HD",
        resolution = "1280x720",
        fileSizeFormatted = "890 MB",
        sizeInBytes = 933232640L,
        codec = "x264 Standard",
        audioChannels = "Hindi Dubbed Stereo AAC",
        estimatedSpeed = "28 MB/s"
    ),
    DownloadQualityOption(
        label = "480p Mobile Saver",
        resolution = "854x480",
        fileSizeFormatted = "390 MB",
        sizeInBytes = 408944640L,
        codec = "x264 Fast",
        audioChannels = "Hindi AAC 128k",
        estimatedSpeed = "20 MB/s"
    )
)

data class Movie(
    val id: String,
    val title: String,
    val originalTitle: String = "",
    val country: String,
    val language: String,
    val industry: MovieIndustry,
    val genres: List<String>,
    val releaseYear: Int,
    val durationMinutes: Int,
    val rating: Float, // out of 10
    val votesCount: String,
    val director: String,
    val cast: List<String>,
    val synopsis: String,
    val posterUrl: String,
    val backdropUrl: String,
    val badge: String? = null,
    val isHindiDubbed: Boolean = true,
    val galleryImages: List<String> = listOf(posterUrl, backdropUrl),
    val qualityOptions: List<DownloadQualityOption> = DEFAULT_QUALITY_OPTIONS,
    val availableFormats: List<MovieContainerFormat> = listOf(
        MovieContainerFormat.MP4,
        MovieContainerFormat.MKV,
        MovieContainerFormat.AVI
    ),
    val audioTracks: List<String> = listOf(
        "Hindi Dubbed [Dolby Audio 5.1]",
        "Multi-Audio (Hindi + Original)",
        "Original Audio",
        "English [Dolby Atmos]"
    ),
    val subtitles: List<String> = listOf("Hindi", "English [CC]", "Spanish", "French", "Japanese", "Arabic"),
    val downloadUrl: String = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
    val galleryFiles: List<MovieGalleryFile> = listOf(
        MovieGalleryFile(
            id = "${id}_8k_movie",
            title = "$title [8K UHD Hindi Dubbed MKV]",
            fileType = MovieFileType.VIDEO_MOVIE,
            fileExtension = "mkv",
            resolutionOrQuality = "7680x4320 (8K Ultra HD)",
            sizeFormatted = "8.6 GB",
            downloadUrl = downloadUrl,
            previewUrl = backdropUrl,
            description = "8K Master encode with Hindi Dubbed Dolby Atmos 9.1 audio"
        ),
        MovieGalleryFile(
            id = "${id}_4k_movie",
            title = "$title [4K UHD Hindi Dubbed MP4]",
            fileType = MovieFileType.VIDEO_MOVIE,
            fileExtension = "mp4",
            resolutionOrQuality = "3840x2160 (4K UHD)",
            sizeFormatted = "3.8 GB",
            downloadUrl = downloadUrl,
            previewUrl = backdropUrl,
            description = "4K HEVC 10-bit HDR with Hindi 5.1 Audio in MP4 container"
        ),
        MovieGalleryFile(
            id = "${id}_1080p_movie",
            title = "$title [1080p FHD Hindi Dubbed MKV]",
            fileType = MovieFileType.VIDEO_MOVIE,
            fileExtension = "mkv",
            resolutionOrQuality = "1920x1080 (1080p FHD)",
            sizeFormatted = "1.8 GB",
            downloadUrl = downloadUrl,
            previewUrl = backdropUrl,
            description = "1080p Full HD MKV with Hindi Dual Audio"
        ),
        MovieGalleryFile(
            id = "${id}_720p_avi_movie",
            title = "$title [720p HD Hindi Dubbed AVI]",
            fileType = MovieFileType.VIDEO_MOVIE,
            fileExtension = "avi",
            resolutionOrQuality = "1280x720 (720p HD)",
            sizeFormatted = "890 MB",
            downloadUrl = downloadUrl,
            previewUrl = backdropUrl,
            description = "720p HD AVI video with clear Hindi dubbing"
        ),
        MovieGalleryFile(
            id = "${id}_trailer",
            title = "$title [Official 4K Trailer Hindi]",
            fileType = MovieFileType.VIDEO_TRAILER,
            fileExtension = "mp4",
            resolutionOrQuality = "1080p Full HD",
            sizeFormatted = "34.5 MB",
            downloadUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            previewUrl = backdropUrl,
            description = "Official movie trailer & clip in Hindi"
        ),
        MovieGalleryFile(
            id = "${id}_poster_art",
            title = "$title [4K Official Poster]",
            fileType = MovieFileType.POSTER_IMAGE,
            fileExtension = "jpg",
            resolutionOrQuality = "3840x5760 (Master Print)",
            sizeFormatted = "8.4 MB",
            downloadUrl = posterUrl,
            previewUrl = posterUrl,
            description = "Official high-resolution key art poster for Gallery"
        ),
        MovieGalleryFile(
            id = "${id}_backdrop_art",
            title = "$title [Panoramic 4K Wallpaper]",
            fileType = MovieFileType.BACKDROP_IMAGE,
            fileExtension = "jpg",
            resolutionOrQuality = "3840x2160 (Ultra HD)",
            sizeFormatted = "6.1 MB",
            downloadUrl = backdropUrl,
            previewUrl = backdropUrl,
            description = "4K widescreen wallpaper for Gallery"
        ),
        MovieGalleryFile(
            id = "${id}_subtitles",
            title = "$title [Hindi & English Subtitles]",
            fileType = MovieFileType.SUBTITLE_PACK,
            fileExtension = "srt",
            resolutionOrQuality = "UTF-8 Formatted",
            sizeFormatted = "180 KB",
            downloadUrl = "https://example.com/subtitles/$id.srt",
            previewUrl = null,
            description = "Timed Hindi and English subtitles"
        )
    )
)

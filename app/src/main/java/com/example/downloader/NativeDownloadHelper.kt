package com.example.downloader

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import com.example.data.model.MovieFileType

object NativeDownloadHelper {

    fun enqueueSystemDownload(
        context: Context,
        url: String,
        title: String,
        description: String = "Downloading with CineWorld Fast Engine"
    ): Long? {
        return downloadGalleryFileToStorage(
            context = context,
            url = url,
            fileName = title,
            fileType = MovieFileType.VIDEO_MOVIE,
            extension = "mp4"
        )
    }

    fun downloadGalleryFileToStorage(
        context: Context,
        url: String,
        fileName: String,
        fileType: MovieFileType,
        extension: String
    ): Long? {
        return try {
            val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as? DownloadManager
                ?: return null

            val sanitizedName = fileName.replace("[^a-zA-Z0-9._-]".toRegex(), "_")
            val fullName = if (sanitizedName.endsWith(".$extension")) sanitizedName else "$sanitizedName.$extension"

            val (directory, mimeType) = when (fileType) {
                MovieFileType.POSTER_IMAGE, MovieFileType.BACKDROP_IMAGE ->
                    Pair(Environment.DIRECTORY_PICTURES, "image/jpeg")
                MovieFileType.VIDEO_MOVIE, MovieFileType.VIDEO_TRAILER ->
                    Pair(Environment.DIRECTORY_MOVIES, "video/mp4")
                MovieFileType.SUBTITLE_PACK ->
                    Pair(Environment.DIRECTORY_DOWNLOADS, "text/plain")
            }

            val request = DownloadManager.Request(Uri.parse(url))
                .setTitle(fullName)
                .setDescription("Saved to device gallery via CineWorld")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true)
                .setMimeType(mimeType)
                .setDestinationInExternalPublicDir(directory, "CineWorld/$fullName")

            val downloadId = downloadManager.enqueue(request)
            Toast.makeText(
                context,
                "Downloading to Gallery ($directory): $fullName",
                Toast.LENGTH_LONG
            ).show()
            downloadId
        } catch (e: Exception) {
            // Fallback to standard Downloads folder
            try {
                val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as? DownloadManager
                val request = DownloadManager.Request(Uri.parse(url))
                    .setTitle(fileName)
                    .setDescription("CineWorld Download")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(
                        Environment.DIRECTORY_DOWNLOADS,
                        "CineWorld_${fileName.replace("[^a-zA-Z0-9]".toRegex(), "_")}.$extension"
                    )
                val id = downloadManager?.enqueue(request)
                Toast.makeText(context, "Saved to Downloads: $fileName", Toast.LENGTH_SHORT).show()
                id
            } catch (err: Exception) {
                Toast.makeText(context, "Download queued in CineWorld Manager", Toast.LENGTH_SHORT).show()
                null
            }
        }
    }

    fun shareMovieLinkOrFile(context: Context, title: String, text: String) {
        try {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "🎬 $title - $text\nDownloaded via CineWorld App")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, "Share Movie File")
            context.startActivity(shareIntent)
        } catch (e: Exception) {
            Toast.makeText(context, "Unable to open share sheet", Toast.LENGTH_SHORT).show()
        }
    }

    fun openExternalGalleryOrPlayer(context: Context, url: String, isVideo: Boolean) {
        try {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(Uri.parse(url), if (isVideo) "video/*" else "image/*")
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "No external gallery viewer found", Toast.LENGTH_SHORT).show()
        }
    }
}

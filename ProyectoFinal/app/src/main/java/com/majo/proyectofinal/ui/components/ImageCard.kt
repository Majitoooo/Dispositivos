package com.majo.proyectofinal.ui.components

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.majo.proyectofinal.model.CatImage

@Composable
fun ImageCard(image: CatImage, title: String = "") {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFFFFE4E1)) // rosado claro
            .padding(16.dp)
    ) {
        if (title.isNotEmpty()) {
            Text(text = title, fontSize = 18.sp, color = Color(0xFF4A4A4A))
            Spacer(modifier = Modifier.height(8.dp))
        }

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(image.url)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { downloadImage(context, image.url) }) {
            Text("Descargar imagen")
        }
    }
}

fun downloadImage(context: Context, imageUrl: String) {
    val request = DownloadManager.Request(Uri.parse(imageUrl))
        .setTitle("Gato descargado")
        .setDescription("Imagen descargada desde la app")
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "gato.jpg")

    val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    manager.enqueue(request)
}

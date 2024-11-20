package com.example.jetpack_test_git_roberto

import android.content.ContentValues
import android.content.Context
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@Composable
fun saveToPdf(context: Context, history: List<String>): Boolean {
    val pdfDocument = PdfDocument()
    val paint = Paint().apply { textSize = 12f }
    val pageWidth = 300
    val pageHeight = 600
    var yOffset = 25f
    var pageCount = 1

    try {
        var pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageCount).create()
        var page = pdfDocument.startPage(pageInfo)
        var canvas = page.canvas

        history.forEach { line ->
            if (yOffset > pageHeight - 50) {
                pdfDocument.finishPage(page)
                pageCount++
                pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageCount).create()
                page = pdfDocument.startPage(pageInfo)
                canvas = page.canvas
                yOffset = 25f
            }
            canvas.drawText(line, 10f, yOffset, paint)
            yOffset += 20f
        }
        pdfDocument.finishPage(page)

        val resolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "training_plan.pdf")
            put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
        }

        val uri = resolver.insert(MediaStore.Files.getContentUri("external"), contentValues)
        uri?.let {
            resolver.openOutputStream(it).use { outputStream ->
                pdfDocument.writeTo(outputStream)
            }
        }

        pdfDocument.close()
        return true
    } catch (e: IOException) {
        e.printStackTrace()
        return false
    }
}


package com.example.jetpack_test_git_roberto

import android.content.Context
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@Composable
fun SavePdfScreen(history: List<String>) {
    val context = LocalContext.current
    Button(onClick = {
        val isSuccess = saveToPdf(context, history)
        val message = if (isSuccess) {
            "Le PDF a bel et bien été sauvegarder dans l'onglet downloads!"
        } else {
            "Erreur rencontré lors de la sauvegarde du PDF."
        }
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }) {
        Text("Sauvegarder le workout en PDF")
    }
}

fun saveToPdf(context: Context, history: List<String>): Boolean {
    val pdfDocument = PdfDocument()
    val paint = Paint().apply {
        textSize = 12f
    }

    val pageWidth = 300
    val pageHeight = 600
    var yOffset = 25f
    var pageCount = 1

    try {
        var pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageCount).create()
        var page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas

        history.forEach { line ->
            if (yOffset > pageHeight - 50) {
                pdfDocument.finishPage(page)
                pageCount++
                pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageCount).create()
                page = pdfDocument.startPage(pageInfo)
                canvas.drawText(line, 10f, yOffset, paint)
                yOffset = 25f
            } else {
                canvas.drawText(line, 10f, yOffset, paint)
                yOffset += 20f
            }
        }


        pdfDocument.finishPage(page)

        val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        if (!downloadsDir.exists()) {
            downloadsDir.mkdirs()
        }

        val file = File(downloadsDir, "training_plan.pdf")
        pdfDocument.writeTo(FileOutputStream(file))
        pdfDocument.close()

        return true
    } catch (e: IOException) {
        e.printStackTrace()
        return false
    }
}

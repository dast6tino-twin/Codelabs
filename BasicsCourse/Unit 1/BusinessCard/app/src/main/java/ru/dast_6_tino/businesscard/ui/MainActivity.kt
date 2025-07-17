package ru.dast_6_tino.businesscard.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.net.toUri
import ru.dast_6_tino.businesscard.ui.screen.main.MainScreen
import ru.dast_6_tino.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                MainScreen(::call, ::share, ::email)
            }
        }
    }

    private fun call(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = (TELEPHONE_PLACEHOLDER + phone).toUri()
        }
        startActivity(intent)
    }

    private fun share(share: String) {
        (getSystemService(CLIPBOARD_SERVICE) as? ClipboardManager)?.apply {
            val clip = ClipData.newPlainText(share, share)
            setPrimaryClip(clip)
        }
    }

    private fun email(email: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = "mailto:$email".toUri()
        }
        startActivity(intent)
    }

    private companion object {

        const val TELEPHONE_PLACEHOLDER = "tel:+"

    }

}

package com.aldi.dicoding.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Base64
import com.aldi.dicoding.R
import com.geniusforapp.fancydialog.FancyAlertDialog
import java.io.UnsupportedEncodingException

class CheckInternetConnection(internal var ctx: Context) {

    private val isInternetConnected: Boolean
        get() {
            val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnectedOrConnecting

        }


    fun ready(message: String?): String? {
        val data: ByteArray = Base64.decode(message, Base64.DEFAULT)
        try {
            return String(data, charset("UTF-8"))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return null
    }

    fun checkConnection() {

        if (!isInternetConnected) {

            val alert = FancyAlertDialog.Builder(ctx)
                .setBackgroundColor(R.color.white)
                .setimageResource(R.drawable.internetconnection)
                .setTextTitle(ready("Tm8gSW50ZXJuZXQ="))
                .setTextSubTitle(ready("Q2Fubm90IGNvbm5lY3QgdG8gYSBzZXJ2ZXJz"))
                .setBody(R.string.noconnection)
                .setPositiveButtonText(ready("Q29ubmVjdCBOb3c="))
                .setPositiveColor(R.color.colorPrimaryDark)
                .setOnPositiveClicked { view, dialog ->
                    if (isInternetConnected) {
                        dialog.dismiss()

                    } else {

                        val dialogIntent = Intent(android.provider.Settings.ACTION_SETTINGS)
                        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        ctx.startActivity(dialogIntent)
                    }
                }
                .setBodyGravity(FancyAlertDialog.TextGravity.CENTER)
                .setTitleGravity(FancyAlertDialog.TextGravity.CENTER)
                .setSubtitleGravity(FancyAlertDialog.TextGravity.CENTER)
                .setCancelable(false)
                .build()
            alert.show()
        }
    }
}

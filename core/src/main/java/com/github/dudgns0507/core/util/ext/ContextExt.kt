package com.github.dudgns0507.core.util.ext

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Point
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Parcelable
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.github.dudgns0507.core.base.BaseActivity
import com.google.android.material.snackbar.Snackbar

fun Context.openBrowser(url: String) {
    val uri = Uri.parse(url)
    Intent(Intent.ACTION_VIEW, uri).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(this)
    }
}

fun Context.isNetworkAvailable(): Boolean {
    var result = false
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
            val networkCapabilities = cm.activeNetwork ?: return false
            val actNw =
                cm.getNetworkCapabilities(networkCapabilities) ?: return false

            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
        else -> {
            cm.run {
                cm.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }
    }

    return result
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

inline fun <reified T> Context.moveTo(bundle: Parcelable? = null): Intent {
    val intent = Intent(this, T::class.java)
    intent.putExtra(BaseActivity.BUNDLE_KEY, bundle)
    return intent
}

fun Context.alert(
    title: String = "",
    message: String = "",
    titleId: Int = 0,
    messageId: Int = 0
): AlertDialog.Builder {
    return AlertDialog.Builder(this).apply {
        if (titleId != 0) {
            setTitle(str(titleId))
        } else if (title.isNotBlank()) {
            setTitle(title)
        }

        if (messageId != 0) {
            setTitle(str(messageId))
        } else if (message.isNotBlank()) {
            setMessage(message)
        }
    }
}

fun Context.str(id: Int): String {
    return this.getString(id)
}

fun Context.color(id: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        getColor(id)
    } else {
        resources.getColor(id)
    }
}

val Context.versionName: String?
    get() = try {
        val pInfo = packageManager.getPackageInfo(packageName, 0);
        pInfo?.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        "Unknown"
    }

val Context.versionCode: Long
    get() = try {
        val pInfo = packageManager.getPackageInfo(packageName, 0)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            pInfo?.longVersionCode ?: 0L
        } else {
            pInfo?.versionCode?.toLong() ?: 0L
        }
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        0L
    }
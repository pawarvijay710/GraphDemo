package com.example.graphsdemo.utils

import android.content.res.Resources

class MiscUtils {
    companion object {
        fun dpToPx(dp: Int): Int {
            return (dp * Resources.getSystem().displayMetrics.density).toInt()
        }
    }
}
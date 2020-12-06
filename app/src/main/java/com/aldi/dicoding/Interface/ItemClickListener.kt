package com.aldi.dicoding.Interface

import android.view.View

interface ItemClickListener {
    fun onClick(view: View, position: Int, isLongClik: Boolean)
}

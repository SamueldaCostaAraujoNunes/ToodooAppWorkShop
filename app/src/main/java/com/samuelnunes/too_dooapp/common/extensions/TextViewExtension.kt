package com.samuelnunes.too_dooapp.common.extensions

import android.graphics.Paint
import android.widget.TextView

fun TextView.fineshed(fineshed: Boolean){
    paintFlags =
        if(fineshed){
            paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
}

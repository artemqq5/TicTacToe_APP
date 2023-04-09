package com.sgjedo.tiktaktoe

import android.graphics.drawable.Drawable
import android.util.Log

class Win {

    fun checkFight(
        vararg listImages: String,
        circle: String,
        cross: String
    ): Boolean {
        listImages.also {

            return (it[0] == it[1] && it[1] == it[2] && (it[0] == circle || it[0] == cross))
                    || (it[3] == it[4] && it[4] == it[5] && (it[3] == circle || it[3] == cross))
                    || (it[6] == it[7] && it[7] == it[8] && (it[6] == circle || it[6] == cross))

                    || (it[0] == it[3] && it[3] == it[6] && (it[0] == circle || it[0] == cross))
                    || (it[1] == it[4] && it[4] == it[7] && (it[1] == circle || it[1] == cross))
                    || (it[2] == it[5] && it[5] == it[8] && (it[2] == circle || it[2] == cross))

                    || (it[0] == it[4] && it[4] == it[8] && (it[0] == circle || it[0] == cross))
                    || (it[2] == it[4] && it[4] == it[6] && (it[2] == circle || it[2] == cross))
        }

    }


}
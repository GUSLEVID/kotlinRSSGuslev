package com.example.kotlinrssguslev.model

import android.content.ClipData

data class RSSObject (val status:String, val feed: Feed, val items:List<ClipData.Item>) {

}
package com.example.kotlinrssguslev.model

import java.util.*

data class item(val title:String, val pubDate:String, val link:String, val guid:String, val author:String, val thumbnail:String, val description:String, val content:String, val enclosure: Objects, val categories:List<String>)

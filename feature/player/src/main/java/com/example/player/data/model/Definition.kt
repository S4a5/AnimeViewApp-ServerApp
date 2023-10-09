package com.example.player.data.model

sealed class Definition (val url: String){
    class SD(url:String):Definition(url)
    class HD(url:String):Definition(url)
    class FHD(url:String):Definition(url)

}
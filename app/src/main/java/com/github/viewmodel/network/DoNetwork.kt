package com.github.viewmodel.network

// 用来简化网络操作
object DoNetwork {
    private val photoService = ServiceCreator.create<PhotoService>()
    suspend fun getPhotos(q: String, page: Int) = photoService.getPhotos(q = q, page = page)
}
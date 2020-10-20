package com.github.viewmodel.network

// 仓库类：用来管理来自网络或者本地的数据操作
object PhotoRepository {
    suspend fun getPhotos(q: String, page: Int) = DoNetwork.getPhotos(q, page)
}
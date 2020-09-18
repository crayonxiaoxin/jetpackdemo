package com.github.viewmodel.model

data class Photos(
    var hits: List<PhotoItem>?,
    var total: Int?,
    var totalHits: Int?
)

data class PhotoItem(
    var comments: Int?,
    var downloads: Int?,
    var favorites: Int?,
    var id: Int?,
    var imageHeight: Int?,
    var imageSize: Int?,
    var imageWidth: Int?,
    var largeImageURL: String?,
    var likes: Int?,
    var pageURL: String?,
    var previewHeight: Int?,
    var previewURL: String?,
    var previewWidth: Int?,
    var tags: String?,
    var type: String?,
    var user: String?,
    var userImageURL: String?,
    var user_id: Int?,
    var views: Int?,
    var webformatHeight: Int?,
    var webformatURL: String?,
    var webformatWidth: Int?
)
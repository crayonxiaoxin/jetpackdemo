package com.github.viewmodel.model

import androidx.databinding.ObservableField
import java.util.*

class User {
    var name: ObservableField<String> = ObservableField("")
    var password: ObservableField<String> = ObservableField("")
    var avatar: ObservableField<String> = ObservableField("")
}
package com.example.complanschool.data

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Laporan(
    val idStudent: String? = null,
    val suspect: String? = null,
    val description: String? = null,
    val timestamp: Long? = null
){
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}

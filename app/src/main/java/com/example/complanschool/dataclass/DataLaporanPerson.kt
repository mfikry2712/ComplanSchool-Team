package com.example.complan.dataclass

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class DataLaporanPerson(
    val suspect: String? = null,
    val violationType: String? = null,
    val description: String? = null,
    val timestamp: Long? = null
){
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}
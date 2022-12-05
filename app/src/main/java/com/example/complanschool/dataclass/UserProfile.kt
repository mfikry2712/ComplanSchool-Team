package com.example.complan.dataclass

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserProfile(
    val userName: String? = null,
    val userPosition: String? = null,
    val schoolName: String? = null,
    val principal: String? = null,
    val domicile: String? = null,
    val schoolCode: String? = null
){
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}
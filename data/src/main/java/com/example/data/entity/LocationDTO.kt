package com.example.data.entity


import com.google.gson.annotations.SerializedName

data class LocationDTO(
    @SerializedName("DataSets")
    val dataSets: List<String?>? = null,
    @SerializedName("EnglishName")
    val englishName: String? = "",
    @SerializedName("IsAlias")
    val isAlias: Boolean? = false,
    @SerializedName("Key")
    val key: String? = "",
    @SerializedName("LocalizedName")
    val localizedName: String? = "",
    @SerializedName("PrimaryPostalCode")
    val primaryPostalCode: String? = "",
    @SerializedName("Rank")
    val rank: Int? = -1,
    @SerializedName("SupplementalAdminAreas")
    val supplementalAdminAreas: List<Any?>? = null,
    @SerializedName("Type")
    val type: String? = "",
    @SerializedName("Version")
    val version: Int? = -1
)


package com.example.mdindiatask

data class HospitalResponse(
    val response: ResponseData
)

data class ResponseData(
    val Status: String,
    val data: List<Hospital>
)

data class Hospital(
    val Provider_Code: String,
    val HospitalName: String,
    val HospitalAddress: String,
    val PinCode: String,
    val Contact_Mobile_No: String?,
    val Latitude: String,
    val Longitude: String
)

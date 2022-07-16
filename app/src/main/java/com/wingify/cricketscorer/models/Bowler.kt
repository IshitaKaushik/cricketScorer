package com.wingify.cricketscorer.models

data class Bowler(
    val bowlerName: String = "",
    var overs: Float = 0f,
    var wickets: Int = 0,
    val maidenOvers: Int = 0,
    val extras: Int = 0
)

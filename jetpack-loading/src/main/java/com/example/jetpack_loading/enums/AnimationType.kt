package com.example.jetpackloading.enums

enum class AnimationType(val animDuration: Int, val circleDelay: Long) {
    CIRCULAR(500, 100L),
    SKIP_AND_REPEAT(250, 250L);
}
package com.hero.ataa.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hero.ataa.R

val AtaaFont = FontFamily(
    Font(R.font.ataa_regular, FontWeight.Normal),
    Font(R.font.ataa_bold, FontWeight.Bold),
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = AtaaFont,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
    body2 = TextStyle(
        fontFamily = AtaaFont,
        fontWeight = FontWeight.Normal,
        fontSize = 13.6.sp
    ),
    h1 = TextStyle(
        fontFamily = AtaaFont,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp
    ),
    h2 = TextStyle(
        fontFamily = AtaaFont,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontFamily = AtaaFont,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    h4 = TextStyle(
        fontFamily = AtaaFont,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
    ),
    h5 = TextStyle(
        fontFamily = AtaaFont,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = AtaaFont,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
    caption = TextStyle(
        fontFamily = AtaaFont,
        fontWeight = FontWeight.Bold,
        fontSize = 11.5.sp
    ),
    overline = TextStyle(
        fontFamily = AtaaFont,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = AtaaFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = AtaaFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.3.sp
    ),
)
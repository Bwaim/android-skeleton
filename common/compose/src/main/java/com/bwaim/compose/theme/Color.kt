package com.bwaim.compose.theme

import androidx.compose.material.Colors
import androidx.compose.material.LocalElevationOverlay
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.Dp

// Light color palette
private val PrimaryLight = Color(0xff3a34bc)
private val PrimaryVariantLight = Color(0xff322da3)
private val PrimaryLightLight = Color(0xff4d47bc)

private val SecondaryLight = Color(0xff924c8b)
private val SecondaryVariantLight = Color(0xff822796)
private val SecondaryLightLight = Color(0xffac4ec1)

private val SurfaceLight = Color(0xfffcfcfe)

private val ErrorLight = Color(0xffaf2d2d)

private val OnPrimaryLight = Color(0xfffcfcfe)
private val OnSecondaryLight = Color(0xfffcfcfe)
private val OnSurfaceLight = Color(0xff0c0b28)

// Dark color palette
private val PrimaryDark = Color(0xff7b78d9)
private val PrimaryVariantDark = Color(0xff8f8dd9)
private val PrimaryLightDark = Color(0xff6c69bf)

private val SecondaryDark = Color(0xffc161d6)
private val SecondaryVariantDark = Color(0xffc576d6)
private val SecondaryLightDark = Color(0xffaa55bd)

private val SurfaceDark = Color(0xff0c0b28)

private val ErrorDark = Color(0xffa93232)

private val OnPrimaryDark = Color(0xfffcfcfe)
private val OnSecondaryDark = Color(0xfffcfcfe)
private val OnSurfaceDark = Color(0xfffcfcfe)

public val SkeletonLightColors: Colors = lightColors(
    primary = PrimaryLight,
    primaryVariant = PrimaryVariantLight,
    secondary = SecondaryLight,
    secondaryVariant = SecondaryVariantLight,
    background = SurfaceLight,
    surface = SurfaceLight,
    error = ErrorLight,
    onPrimary = OnPrimaryLight,
    onSecondary = OnSecondaryLight,
    onBackground = OnSurfaceLight,
    onSurface = OnSurfaceLight,
    onError = OnSurfaceLight
)

public val SkeletonDarkColors: Colors = darkColors(
    primary = PrimaryDark,
    primaryVariant = PrimaryVariantDark,
    secondary = SecondaryDark,
    secondaryVariant = SecondaryVariantDark,
    background = SurfaceDark,
    surface = SurfaceDark,
    error = ErrorDark,
    onPrimary = OnPrimaryDark,
    onSecondary = OnSecondaryDark,
    onBackground = OnSurfaceDark,
    onSurface = OnSurfaceDark,
    onError = OnSurfaceDark
)

public val Colors.primaryLight: Color
    @Composable
    get() = if (isLight) PrimaryLightLight else PrimaryLightDark

public val Colors.secondaryLight: Color
    @Composable
    get() = if (isLight) SecondaryLightLight else SecondaryLightDark

/**
 * Return the fully opaque color that results from compositing [onSurface] atop [surface] with the
 * given [alpha]. Useful for situations where semi-transparent colors are undesirable.
 */
public fun Colors.compositedOnSurface(alpha: Float): Color {
    return onSurface.copy(alpha = alpha).compositeOver(surface)
}

@Composable
public fun Colors.elevatedSurface(elevation: Dp): Color {
    return LocalElevationOverlay.current?.apply(
        color = this.surface,
        elevation = elevation
    ) ?: this.surface
}

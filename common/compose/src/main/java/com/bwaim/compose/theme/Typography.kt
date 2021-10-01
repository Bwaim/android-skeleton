package com.bwaim.compose.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Immutable
public class SkeletonTypography internal constructor(
    public val headline1: TextStyle,
    public val title1: TextStyle,
    public val title2: TextStyle,
    public val button: TextStyle,
) {
    public constructor(
        defaultFontFamily: FontFamily = FontFamily.Default,
        headline1: TextStyle = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.sp,
            lineHeight = 40.sp
        ),
        title1: TextStyle = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.sp,
            lineHeight = 32.sp
        ),
        title2: TextStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.sp,
            lineHeight = 28.sp
        ),
        button: TextStyle = TextStyle(
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.02.em,
            lineHeight = 24.sp
        ),
    ) : this(
        headline1 = headline1.withDefaultFontFamily(defaultFontFamily),
        title1 = title1.withDefaultFontFamily(defaultFontFamily),
        title2 = title2.withDefaultFontFamily(defaultFontFamily),
        button = button.withDefaultFontFamily(defaultFontFamily),
    )

    public fun copy(
        headline1: TextStyle = this.headline1,
        title1: TextStyle = this.title1,
        title2: TextStyle = this.title2,
        button: TextStyle = this.button,
    ): SkeletonTypography = SkeletonTypography(
        headline1 = headline1,
        title1 = title1,
        title2 = title2,
        button = button,
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SkeletonTypography) return false

        if (headline1 != other.headline1) return false
        if (title1 != other.title1) return false
        if (title2 != other.title2) return false
        if (button != other.button) return false

        return true
    }

    override fun hashCode(): Int {
        var result = headline1.hashCode()
        result = 31 * result + title1.hashCode()
        result = 31 * result + title2.hashCode()
        result = 31 * result + button.hashCode()
        return result
    }

    override fun toString(): String {
        return "Typography(headline1=$headline1, title1=$title1, title2=$title2, button=$button)"
    }
}

private fun TextStyle.withDefaultFontFamily(default: FontFamily): TextStyle {
    return if (fontFamily != null) this else copy(fontFamily = default)
}

internal val LocalSkeletonTypography = staticCompositionLocalOf { SkeletonTypography() }

/**
 * A Material [Typography] implementation which sets all text styles to a [TextStyle] with a
 * 1sp font size and a transparent color to discourage usage of
 * [MaterialTheme.typography] in preference to [SkeletonTheme.typography].
 */
internal fun debugTypography(): Typography {
    val debugTextStyle = TextStyle(fontSize = 100.sp)
    return Typography(
        h1 = debugTextStyle,
        h2 = debugTextStyle,
        h3 = debugTextStyle,
        h4 = debugTextStyle,
        h5 = debugTextStyle,
        h6 = debugTextStyle,
        subtitle1 = debugTextStyle,
        subtitle2 = debugTextStyle,
        body1 = debugTextStyle,
        body2 = debugTextStyle,
        button = debugTextStyle,
        caption = debugTextStyle,
        overline = debugTextStyle,
    )
}

@Preview(name = "Typography")
@Composable
private fun PreviewTypography() {
    SkeletonTheme(useDarkColors = false) {
        Surface {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Headline1", style = SkeletonTheme.typography.headline1)
                Text(text = "Title 1", style = SkeletonTheme.typography.title1)
                Text(text = "Title 2", style = SkeletonTheme.typography.title2)
                Text(text = "Button", style = SkeletonTheme.typography.button)
            }
        }
    }
}

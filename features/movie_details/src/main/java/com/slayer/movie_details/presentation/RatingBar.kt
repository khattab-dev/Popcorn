import androidx.annotation.FloatRange
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.round

object RatingBarDefaults {

    /**
     * Composable function providing the default content for unrated stars.
     *
     * @param color The color of the unrated star icon.
     */
    @Composable
    fun UnratedContent(color: Color = Color.LightGray) {
        Icon(
            tint = color,
            imageVector = Icons.Rounded.Star,
            modifier = Modifier.fillMaxSize(),
            contentDescription = "Unrated Star"
        )
    }

    /**
     * Composable function providing the default content for rated stars.
     *
     * @param color The color of the rated star icon.
     */
    @Composable
    fun RatedContent(color: Color = Color(0xFFFFC107)) {
        Icon(
            tint = color,
            imageVector = Icons.Rounded.Star,
            modifier = Modifier.fillMaxSize(),
            contentDescription = "Rated Star"
        )
    }

}

/**
 * Composable function that displays a RatingBar
 *
 * @param modifier The modifier for the RatingBar
 * @param rating The current rating value of the RatingBar
 * @param onRatingChanged Callback that is invoked when the rating changes
 * @param ratingStep The step increment for rating changes
 * @param starsCount The number of stars in the RatingBar
 * @param starSize The size of each individual star
 * @param starSpacing The spacing between stars
 * @param unratedContent Lambda function to provide custom content for unrated stars
 * @param ratedContent Lambda function to provide custom content for rated stars
 * @param enableDragging Enables or disables dragging to change rating.
 * @param enableTapping Enables or disables tapping to change rating.
 */
@Composable
fun RatingBar(
    rating: Float,
    modifier: Modifier = Modifier,
    starsCount: Int = 5,
    starSize: Dp = 32.dp,
    starSpacing: Dp = 0.dp,
    unratedContent: @Composable BoxScope.(starIndex: Int) -> Unit = {
        RatingBarDefaults.UnratedContent()
    },
    ratedContent: @Composable BoxScope.(starIndex: Int) -> Unit = {
        RatingBarDefaults.RatedContent()
    },
) {
    val bounds = remember { mutableMapOf<Int, Rect>() }

    Row(
        horizontalArrangement = Arrangement.spacedBy(starSpacing),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (index in 1..starsCount) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(starSize)
                    .onGloballyPositioned { layoutCoordinates ->
                        bounds[index] = layoutCoordinates.boundsInParent()
                    }
            ) {
                unratedContent(index)

                val fillWidthFraction = when {
                    (rating >= index) -> 1f
                    (rating > index - 1) && (rating <= index) -> rating - (index - 1)
                    else -> 0f
                }
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clip(ClippingRectShape(fillWidthFraction)),
                    contentAlignment = Alignment.Center,
                    content = {
                        ratedContent(index)
                    }
                )
            }
        }
    }
}

private class ClippingRectShape(private val fillWidthFraction: Float) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val clippingRect = Rect(Offset.Zero, Size(size.width * fillWidthFraction, size.height))
        return Outline.Rectangle(clippingRect)
    }
}
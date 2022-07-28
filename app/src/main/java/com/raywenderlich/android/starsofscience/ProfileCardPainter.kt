package com.raywenderlich.android.starsofscience

import androidx.annotation.ColorInt
import android.graphics.*
import com.raywenderlich.android.starsofscience.utils.*

//1

class ProfileCardPainter(
    //2
    @ColorInt private val color: Int,
    private val avatarRadius: Float,
    private val avatarMargin: Float
) : Painter {
    //3
    override fun paint(canvas: Canvas) {
        //1
        val width = canvas.width.toFloat()
        val height = canvas.height.toFloat()
        //2
        val shapeBounds = RectFFactory.fromLTWH(0f, 0f, width, height - avatarRadius)
        //3
        //1
        val centerAvatar = PointF(shapeBounds.centerX(), shapeBounds.bottom)

        val avatarBounds = RectFFactory.fromCircle(center = centerAvatar, radius = avatarRadius).inflate(avatarMargin)


    }

    private fun drawBackground(canvas: Canvas, bounds: RectF, avatarBounds: RectF) {
        //1
        val paint = Paint()
        paint.color = color

        //2
        val backgroundPath = Path().apply {
            // 3
            moveTo(bounds.left, bounds.top)
            // 4
            lineTo(bounds.bottomLeft.x, bounds.bottomLeft.y)
            // 5
            lineTo(avatarBounds.centerLeft.x, avatarBounds.centerLeft.y)
            // 6
            arcTo(avatarBounds, -180f, 180f, false)
            // 7
            lineTo(bounds.bottomRight.x, bounds.bottomRight.y)
            // 8
            lineTo(bounds.topRight.x, bounds.topRight.y)
            // 9
            close()
        }

        //10
        canvas.drawPath(backgroundPath, paint);
    }

    private fun drawCurvedShape(canvas: Canvas, bounds: RectF, avatarBounds: RectF) {
        //1
        val paint = Paint()
        paint.color = color.darkerShade()

        //2
        val handlePoint = PointF(bounds.left + (bounds.width() * 0.25f), bounds.top)

        //3
        val curvePath = Path().apply {
            //4
            moveTo(bounds.bottomLeft.x, bounds.bottomLeft.y)
            //5
            lineTo(avatarBounds.centerLeft.x, avatarBounds.centerLeft.y)
            //6
            arcTo(avatarBounds, -180f, 180f, false)
            //7
            lineTo(bounds.bottomRight.x, bounds.bottomRight.y)
            //8
            lineTo(bounds.topRight.x, bounds.topRight.y)
            //9
            quadTo(handlePoint.x, handlePoint.y, bounds.bottomLeft.x, bounds.bottomLeft.y)
            //10
            close()
        }

        //11
        canvas.drawPath(curvePath, paint)
    }



}
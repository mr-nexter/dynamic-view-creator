package com.dynamicviewcreator

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.indices
import java.util.logging.Logger

/**
 * [ViewCreator] class is used to create simple views for provided POJO objects.
 * As a POJO object you can use any database model classes.
 *
 * @author Andrii Dubovyk
 *
 * @param context Represents [Context] of the app
 * @param viewGroup Represents parent view for the created views.
 * If not provided, [LinearLayout] will be used by default.
 *
 * @param layoutParams Must be provided if custom layout is used.
 * E.g. if you passed a [ConstraintLayout] as a [viewGroup] param,
 * you have to provide [ConstraintLayout.LayoutParams] as a [layoutParams], otherwise you might get an error
 *
 * @param standardMargin Represents standard margin in density pixels, that will be used for all margins during view creation
 */
class ViewCreator(
        val context: Context,
        val viewGroup: ViewGroup?,
        val layoutParams: ViewGroup.LayoutParams? = null,
        var standardMargin: Int = 16
) {
    private val layout = viewGroup ?: LinearLayout(context)
    private val params = layoutParams?: layout.layoutParams
    private var gridParams: List<String>? = null

    /**
     * Creates [Parser] to parse the provided object
     * @return [Parser] object
     */
    private fun getParser(): Parser {
        return Parser(context, standardMargin, gridParams)
    }

    fun <T: Any> create(obj: T): ViewGroup {
        val view = getParser().parse(obj)

        layout.addView(view, layout.indices.last+1, params)
        return layout
    }

    /**
     * Allows to display properties as a grid
     * @param names of the the properties to display as a grid
     */
    fun toGrid(vararg names: String) {
        gridParams = names.toList()
    }
}
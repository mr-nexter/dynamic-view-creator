package com.dynamicviewcreator

import android.content.Context
import android.graphics.Typeface
import android.net.Uri
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Parser(private val context: Context,
             private val standardMargin: Int,
             private val gridFields: List<String>? = null) {

    private val tag = "Dynamic View Creator Parser"
    private val isGridNecessary = gridFields != null

    /**
     * Allows to parse provided object and to convert it to a [View]
     * @param obj POJO object, usually a database model object
     * @return Created [View] based on the provided POJO object
     */
    fun <T: Any> parse(obj: T): View {
        val params = LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        val px = context.dpToPx(20)
        val layout = LinearLayoutCompat(context)
        layout.layoutParams = params
        layout.setPadding(px)

        var gridView: RecyclerView? = null
        // TODO: here you should add a RecyclerView

        for (field in obj.javaClass.declaredFields) {
            field.isAccessible = true
            try {
                val view: View? = when (field.type) {
                    is CharSequence -> {
                        val text = (field.get(obj) as CharSequence).toString()
                        if (isImageUrl(text)) {
                            createImageView(text)
                        } else {
                            createTextView(field.name, text)
                        }
                    }
                    is Number -> {
                        val number = field.get(obj) as Number
                        createTextView(field.name, number.toString())
                    }
                    is Collection<*> -> {
                        null
                    }
                    else -> null
                }

                view?.let {
                    if (isGridNecessary && gridFields!!.contains(field.name)) {
                        TODO()
                    } else {
                        TODO()
                    }
                }
            } catch (e: Exception) {
                Log.e(tag, "Cannot parse $field of $obj")
            }
        }
        return layout
    }

    private fun createCollectionRepresentation(name: String, value: Collection<*>) {
        TODO()
    }

    /**
     * Creates text views for a provided pair of the property name and its value
     * @param name [String] name of the property
     * @param text [String] value of the property
     * @return Created [LinearLayoutCompat] representation of the pair
     */
    private fun createTextView(name: String, text: CharSequence): LinearLayoutCompat {
        val layout = LinearLayoutCompat(context)
        layout.gravity = Gravity.CENTER_VERTICAL

        val mx = context.dpToPx(standardMargin)
        val my = (mx / 2)

        val params = LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.apply {
            marginStart = mx
            marginEnd = mx
            topMargin = my
            bottomMargin = my
        }

        val textSize = context.spToPx(16f)

        val variableNameTextView = AppCompatTextView(context)
        variableNameTextView.apply {
            layoutParams = params
            setTextSize(textSize)
            typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            setText(name)
        }

        layout.addView(variableNameTextView)

        params.weight = 1.0f
        val variableValue = AppCompatTextView(context)
        variableValue.apply {
            layoutParams = params
            setText(text)
            setTextSize(textSize)
        }

        layout.addView(variableValue)

        return layout
    }

    /**
     * Allows to create an [ImageView] for a provided [String] url
     * @param text [String] url to load an image into an [ImageView]
     * @return Created [ImageView] or null if url couldn't parse the provided url [String]
     */
    private fun createImageView(text: String): ImageView? {
        return try {
            val uri = Uri.parse(text)

            val view = ImageView(context)
            val params = LinearLayoutCompat.LayoutParams(context.dpToPx(80), context.dpToPx(100))
            val mx = context.dpToPx(16)
            val my = context.dpToPx(8)

            params.setMargins(mx, my, mx, my)
            view.layoutParams = params
            view.scaleType = ImageView.ScaleType.CENTER_CROP

            Picasso.get().load(uri).into(view)

            view
        } catch (e: Exception) {
            Log.e(tag, "Cannot create an ImageView for a provided url: $text", e)
            null
        }
    }

    /**
     * Checks if the provided text is an image url
     * @param text A text to be checked
     * @return Checking result
     */
    private fun isImageUrl(text: String): Boolean {
        val types = listOf("jpg", "jpeg", "bmp", "png", "webp")
        var endsWith = false
        for (type in types) {
            endsWith = text.endsWith(type, ignoreCase = true)
            if (endsWith) break
        }
        return endsWith
    }
}
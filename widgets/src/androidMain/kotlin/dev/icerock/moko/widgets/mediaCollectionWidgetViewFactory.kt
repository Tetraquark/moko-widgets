package com.icerockdev.mpp.widgets.forms

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.icerockdev.mpp.core.media.Media
import com.icerockdev.mpp.mvvm.livedata.map
import com.icerockdev.mpp.utils.getResizedBitmap
import com.icerockdev.mpp.widgets.VFC
import com.icerockdev.mpp.widgets.ViewFactoryContext
import com.icerockdev.mpp.widgets.forms.databinding.WidgetMediaCollectionBinding

actual var mediaCollectionWidgetViewFactory: VFC<MediaCollectionWidget> =
    { context: ViewFactoryContext,
      widget: MediaCollectionWidget ->

        val ctx = context.context
        val parent = context.parent
        val layoutInflater = LayoutInflater.from(ctx)
        val binding: WidgetMediaCollectionBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.widget_media_collection, parent, false)

        binding.bindList(widget)
        binding.recyclerView.addItemDecoration(MediaDecoration())
        binding.lifecycleOwner = context.lifecycleOwner
        binding.root
    }


private class MediaDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        parent.adapter?.run {

            when (parent.getChildAdapterPosition(view)) {
                0 -> {
                    val leftToCenter = parent.measuredWidth / 2
                    val itemToCenter = view.layoutParams.width / 2

                    val paddingLeft = leftToCenter - itemToCenter
                    outRect.left = paddingLeft
                }
                itemCount - 1 -> {
                    outRect.right =
                        (view.context.resources.displayMetrics.density * RIGHT_PADDING).toInt()
                }
            }
        }
    }

    private companion object {
        const val RIGHT_PADDING = 16
    }
}

private fun WidgetMediaCollectionBinding.bindList(mediaWidget: MediaCollectionWidget) {
    widget = mediaWidget
    photos = mediaWidget.field.data.map { mediaList ->
        mediaList.map { media ->
            ItemPhoto()
                .setIsPlayVisible(media.type == Media.MediaType.VIDEO)
                .setImage(media.preview.platformBitmap.getResizedBitmap())
                .setOnItemClick({
                    mediaWidget.itemClickListener(media)
                } as? kotlin.jvm.functions.Function0<Unit>)
                .setOnDeleteClick({
                    mediaWidget.deleteListener(media)
                } as? kotlin.jvm.functions.Function0<Unit>)
        }.plus(
            ItemPhotoAdd()
                .setOnClick({
                    mediaWidget.addListener()
                } as? kotlin.jvm.functions.Function0<Unit>)
        )
    }.ld()
}
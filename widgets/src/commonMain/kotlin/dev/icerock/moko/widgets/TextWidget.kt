/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.widgets

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.widgets.core.OptionalId
import dev.icerock.moko.widgets.core.Styled
import dev.icerock.moko.widgets.core.VFC
import dev.icerock.moko.widgets.core.Widget
import dev.icerock.moko.widgets.core.WidgetDef
import dev.icerock.moko.widgets.core.WidgetScope
import dev.icerock.moko.widgets.style.background.Background
import dev.icerock.moko.widgets.style.view.Backgrounded
import dev.icerock.moko.widgets.style.view.MarginValues
import dev.icerock.moko.widgets.style.view.Margined
import dev.icerock.moko.widgets.style.view.Padded
import dev.icerock.moko.widgets.style.view.PaddingValues
import dev.icerock.moko.widgets.style.view.Sized
import dev.icerock.moko.widgets.style.view.TextStyle
import dev.icerock.moko.widgets.style.view.WidgetSize

expect var textWidgetViewFactory: VFC<TextWidget>

@WidgetDef
class TextWidget(
    override val factory: VFC<TextWidget>,
    override val style: Style,
    override val id: Id?,
    val text: LiveData<StringDesc>
) : Widget<TextWidget>(),
    Styled<TextWidget.Style>,
    OptionalId<TextWidget.Id> {

    data class Style(
        override val size: WidgetSize = WidgetSize(),
        override val background: Background? = null,
        override val padding: PaddingValues? = null,
        override val margins: MarginValues? = null,
        val textStyle: TextStyle = TextStyle()
    ) : Widget.Style, Padded, Margined, Sized, Backgrounded

    interface Id : WidgetScope.Id
}
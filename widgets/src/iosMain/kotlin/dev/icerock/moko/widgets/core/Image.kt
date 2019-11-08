/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.widgets.core

import dev.icerock.moko.media.Bitmap
import dev.icerock.moko.resources.DrawableResource


actual abstract class Image {

    actual companion object {
        actual fun resource(drawableResource: DrawableResource): Image {
            TODO()
        }

        actual fun network(url: String): Image {
            TODO()
        }

        actual fun bitmap(bitmap: Bitmap): Image {
            TODO()
        }
    }
}
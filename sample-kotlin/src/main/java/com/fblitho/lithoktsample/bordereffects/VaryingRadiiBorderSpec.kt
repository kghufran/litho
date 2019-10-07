/*
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fblitho.lithoktsample.bordereffects

import android.graphics.Color
import com.facebook.litho.Border
import com.facebook.litho.Border.Corner
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.Row
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaEdge

@LayoutSpec
object VaryingRadiiBorderSpec {

  @OnCreateLayout
  fun onCreateLayout(c: ComponentContext): Component =
      Row.create(c)
          .child(Text.create(c).textSizeSp(20f).text("This component has varying corner radii"))
          .border(
              Border.create(c)
                  .widthDip(YogaEdge.ALL, 3f)
                  .color(YogaEdge.LEFT, Color.BLACK)
                  .color(YogaEdge.TOP, NiceColor.GREEN)
                  .color(YogaEdge.BOTTOM, NiceColor.BLUE)
                  .color(YogaEdge.RIGHT, NiceColor.RED)
                  .radiusDip(Corner.TOP_LEFT, 10f)
                  .radiusDip(Corner.TOP_RIGHT, 5f)
                  .radiusDip(Corner.BOTTOM_RIGHT, 20f)
                  .radiusDip(Corner.BOTTOM_LEFT, 30f)
                  .build())
          .build()

}

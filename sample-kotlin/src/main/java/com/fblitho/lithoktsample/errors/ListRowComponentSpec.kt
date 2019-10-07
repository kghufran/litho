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

package com.fblitho.lithoktsample.errors

import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.Card
import com.facebook.yoga.YogaEdge

@LayoutSpec
object ListRowComponentSpec {

  @OnCreateLayout
  fun onCreateLayout(c: ComponentContext, @Prop row: ListRow): Component =
      Column.create(c)
          .paddingDip(YogaEdge.VERTICAL, 8f)
          .paddingDip(YogaEdge.HORIZONTAL, 32f)
          .child(
              Card.create(c)
                  .content(
                      Column.create(c)
                          .marginDip(YogaEdge.ALL, 32f)
                          .child(TitleComponent.create(c).title(row.title))
                          .child(PossiblyCrashingSubTitleComponent.create(c).subtitle(row.subtitle))
                          .build())
                  .build())
          .build()
}

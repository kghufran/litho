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

package com.fblitho.lithoktsample.animations.animationcomposition

import android.graphics.Color
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import com.facebook.litho.ClickEvent
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.Row
import com.facebook.litho.StateValue
import com.facebook.litho.Transition
import com.facebook.litho.Transition.TransitionUnitsBuilder
import com.facebook.litho.animation.AnimatedProperties
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.OnCreateTransition
import com.facebook.litho.annotations.OnEvent
import com.facebook.litho.annotations.OnUpdateState
import com.facebook.litho.annotations.State
import com.facebook.yoga.YogaAlign

@LayoutSpec
object LeftRightBlocksComponentSpec {

  @OnCreateLayout
  fun onCreateLayout(c: ComponentContext, @State left: Boolean): Component =
      Column.create(c)
          .alignItems(if (left) YogaAlign.FLEX_START else YogaAlign.FLEX_END)
          .child(
              Row.create(c)
                  .heightDip(40f)
                  .widthDip(40f)
                  .backgroundColor(Color.parseColor("#ee1111"))
                  .transitionKey("red")
                  .transitionKeyType(Transition.TransitionKeyType.GLOBAL)
                  .build())
          .child(
              Row.create(c)
                  .heightDip(40f)
                  .widthDip(40f)
                  .backgroundColor(Color.parseColor("#1111ee"))
                  .transitionKey("blue")
                  .transitionKeyType(Transition.TransitionKeyType.GLOBAL)
                  .build())
          .child(
              Row.create(c)
                  .heightDip(40f)
                  .widthDip(40f)
                  .backgroundColor(Color.parseColor("#11ee11"))
                  .transitionKey("green")
                  .transitionKeyType(Transition.TransitionKeyType.GLOBAL)
                  .build())
          .clickHandler(LeftRightBlocksComponent.onClick(c))
          .build()

  @OnEvent(ClickEvent::class)
  fun onClick(c: ComponentContext) {
    LeftRightBlocksComponent.updateState(c)
  }

  @OnUpdateState
  fun updateState(left: StateValue<Boolean>) {
    left.set(left.get() != true)
  }

  @OnCreateTransition
  fun onCreateTransition(c: ComponentContext): Transition =
      Transition.parallel<TransitionUnitsBuilder>(
          Transition.create(Transition.TransitionKeyType.GLOBAL, "red")
              .animate(AnimatedProperties.X)
              .animator(Transition.timing(1000, AccelerateDecelerateInterpolator())),
          Transition.create(Transition.TransitionKeyType.GLOBAL, "blue").animate(AnimatedProperties.X).animator(Transition.timing(1000)),
          Transition.create(Transition.TransitionKeyType.GLOBAL, "green")
              .animate(AnimatedProperties.X)
              .animator(Transition.timing(1000, BounceInterpolator())))
}

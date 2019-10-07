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
import com.facebook.litho.StateValue
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateInitialState
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.OnError
import com.facebook.litho.annotations.OnUpdateState
import com.facebook.litho.annotations.Param
import com.facebook.litho.annotations.Prop
import com.facebook.litho.annotations.State
import com.facebook.yoga.YogaEdge
import java.util.Optional

@LayoutSpec
object ErrorBoundarySpec {

  @OnCreateLayout
  fun onCreateLayout(
      c: ComponentContext,
      @Prop child: Component,
      @State error: Optional<Exception>
  ): Component = if (error.isPresent) {
    Column.create(c)
        .marginDip(YogaEdge.ALL, 16f)
        .child(
            DebugErrorComponent.create(c)
                .message("Error Boundary")
                .throwable(error.get())
                .build())
        .build()
  } else {
    child
  }

  @OnCreateInitialState
  fun createInitialState(c: ComponentContext, error: StateValue<Optional<Exception>>) {
    error.set(Optional.empty())
  }

  @OnUpdateState
  fun updateError(error: StateValue<Optional<Exception>>, @Param e: Exception) {
    error.set(Optional.of(e))
  }

  @OnError
  fun onError(c: ComponentContext, error: Exception) {
    ErrorBoundary.updateError(c, error)
  }
}

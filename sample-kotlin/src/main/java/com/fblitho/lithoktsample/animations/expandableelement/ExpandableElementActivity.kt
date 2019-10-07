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

package com.fblitho.lithoktsample.animations.expandableelement

import android.os.Bundle
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.fblitho.lithoktsample.NavigatableDemoActivity
import com.fblitho.lithoktsample.animations.messages.Message.Companion.MESSAGES

class ExpandableElementActivity : NavigatableDemoActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val lithoView = LithoView.create(
        this,
        ExpandableElementRootComponent.create(ComponentContext(this))
            .initialMessages(MESSAGES)
            .build())
    setContentView(lithoView)
  }
}

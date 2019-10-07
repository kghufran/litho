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

package com.facebook.samples.litho.animations.expandableelement;

import android.graphics.Color;
import com.facebook.litho.ClickEvent;
import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.Row;
import com.facebook.litho.StateValue;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateInitialState;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.OnUpdateState;
import com.facebook.litho.annotations.Param;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.State;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.common.DataDiffSection;
import com.facebook.litho.sections.common.RenderEvent;
import com.facebook.litho.sections.widget.NotAnimatedItemAnimator;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;
import com.facebook.litho.widget.RenderInfo;
import com.facebook.litho.widget.Text;
import com.facebook.litho.widget.TextAlignment;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;
import java.util.ArrayList;
import java.util.List;

@LayoutSpec
public class ExpandableElementRootComponentSpec {

  @OnCreateInitialState
  static void onCreateInitialState(
      ComponentContext c,
      StateValue<List<Message>> messages,
      StateValue<Integer> counter,
      @Prop List<Message> initialMessages) {
    messages.set(initialMessages);
    counter.set(1);
  }

  @OnCreateLayout
  static Component onCreateLayout(
      ComponentContext c, @State List<Message> messages, @State int counter) {
    return Column.create(c)
        .child(
            Row.create(c)
                .backgroundColor(Color.LTGRAY)
                .child(
                    Text.create(c)
                        .paddingDip(YogaEdge.ALL, 10)
                        .text("INSERT")
                        .textSizeSp(20)
                        .flexGrow(1)
                        .alignSelf(YogaAlign.CENTER)
                        .testKey("INSERT")
                        .alignment(TextAlignment.CENTER)
                        .clickHandler(ExpandableElementRootComponent.onClick(c, true)))
                .child(
                    Text.create(c)
                        .paddingDip(YogaEdge.ALL, 10)
                        .text("DELETE")
                        .textSizeSp(20)
                        .flexGrow(1)
                        .alignSelf(YogaAlign.CENTER)
                        .alignment(TextAlignment.CENTER)
                        .clickHandler(ExpandableElementRootComponent.onClick(c, false))))
        .child(
            RecyclerCollectionComponent.create(c)
                .flexGrow(1)
                .disablePTR(true)
                .itemAnimator(new NotAnimatedItemAnimator())
                .section(
                    DataDiffSection.<Message>create(new SectionContext(c))
                        .data(messages)
                        .renderEventHandler(ExpandableElementRootComponent.onRender(c))
                        .build())
                .paddingDip(YogaEdge.TOP, 8))
        .build();
  }

  @OnEvent(ClickEvent.class)
  static void onClick(
      ComponentContext c, @Prop List<Message> initialMessages, @Param boolean adding) {
    ExpandableElementRootComponent.onUpdateListSync(c, adding, initialMessages.size());
  }

  @OnUpdateState
  static void onUpdateList(
      StateValue<List<Message>> messages,
      StateValue<Integer> counter,
      @Param boolean adding,
      @Param int initialMessagesSize) {
    final ArrayList<Message> updatedMessageList = new ArrayList<>(messages.get());

    int counterValue = counter.get();
    if (adding) {
      updatedMessageList.add(
          1, new Message(true, "Just Added #" + counterValue, true, "Recently", true));
      counter.set(counterValue + 1);
    } else if (initialMessagesSize < updatedMessageList.size()) {
      updatedMessageList.remove(1);
    }
    messages.set(updatedMessageList);
  }

  @OnEvent(RenderEvent.class)
  static RenderInfo onRender(ComponentContext c, @FromEvent Message model) {
    return model.createComponent(c);
  }
}

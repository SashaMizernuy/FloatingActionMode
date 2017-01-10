# FloatingActionMode

Floating Action Mode (FAM) is the custom view for context actions on Android.

## [Demo Video](https://www.youtube.com/watch?v=PbQ8N7pWGt4)

FAM has following XML-attributes (they also may be changed programmatically):

* ***opened*** defines if FAM opened when created.

* ***content_res*** is LayoutRes that represents content of FAM (e.g. some buttons)

* ***can_close*** defines if FAM will have button for closing itself.

* ***close_icon*** is DrawableRes for closing button. (has default value)

* ***can_drag*** defines if FAM will have button for gragging itself.

* ***drag_icon*** is DrawableRes for dragging button. (has default value)

* ***can_dismiss*** defines if FAM may be dismissed (and closed) if transtationX while dragging is big enough.

* ***dismiss_threshold*** is fraction that used to solve threshold translationX for dismissing.

* ***minimize_direction*** defines minimize direction of FAM.This attribute may have following values:
 * *none* - FAM will not be translated while minimizing.
 * *top* - FAM will be translated to the top border of parent (excluding offsets) while minimizing.
 * *bottom* - FAM will be translated to the bottom border of parent (excluding offsets) while minimizing.
 * *nearest* - FAM will be translated to the nearest (top or bottom) border of parent (excluding offsets) while minimizing.

* ***animation_duration*** defines duration of  minimize/maximize animations.

## Using in CoordinatorLayout

FAM has its special CoordinatorLayout.Behavior and can be used in CoordinatorLayout.
FloatingActionModeBehavior allows to offset FAM of AppBarLayout and Snackbar.SnackbarLayout.
Also it allows to minimize/maximize FAM on scroll.

```
<android.support.design.widget.CoordinatorLayout>

    <android.support.design.widget.AppBarLayout>
        ...
    </android.support.design.widget.AppBarLayout>

    <android.support.v7.widget.RecyclerView
        app:layout_behavior="@string/appbar_scrolling_view_behavior"/>

    <com.qwert2603.floating_action_mode.FloatingActionMode
        android:id="@+id/floating_action_mode"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="@dimen/action_mode_margin"
        android:animateLayoutChanges="true"
        android:background="@drawable/action_mode_background"
        android:translationZ="8dp"
        app:animation_duration="@integer/action_mode_animation_duration"
        app:can_dismiss="true"
        app:can_drag="true"
        app:content_res="@layout/user_list_action_mode_2"
        app:dismiss_threshold="0.35"
        app:drag_icon="@drawable/ic_drag_white_24dp"
        app:minimize_direction="nearest"/>

</android.support.design.widget.CoordinatorLayout>
```

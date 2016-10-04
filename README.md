# Button3D

[![](https://jitpack.io/v/inlacou/Button3D.svg)](https://jitpack.io/#inlacou/Button3D)

In your layout.xml:
```xml
<ThreeDimensionalButton
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:theme="@style/YourButtonTheme"
        app:textSize="@dimen/text_size_big"
        app:textColor="@color/colorAccent"
        app:text="Click me!"/>
```

There is no `wrap_content` for width. It was `wrap_content` always, or `match_parent` always. I am working on it, but not having any success.

In styles.xml:
```xml
<style name="YourButtonTheme">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">#222222</item>
    </style>
```

If you want to change colors, otherwise it takes `colorPrimary` and `colorPrimaryDark`, as you can see.

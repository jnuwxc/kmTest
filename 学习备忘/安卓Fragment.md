# 安卓Fragment

`Fragment`表示应用界面中可重复使用的一部分。Fragment 定义和管理自己的布局，具有自己的生命周期，并且可以处理自己的输入事件。Fragment 不能独立存在，而是必须由 Activity 或另一个 Fragment 托管。Fragment 的视图层次结构会成为宿主的视图层次结构的一部分，或附加到宿主的视图层次结构。

Fragment 允许您将界面划分为离散的区块，从而将模块化和可重用性引入 Activity 的界面，Fragment本意为碎片、分段等，适合用于部分屏幕的界面，全局请使用activity。

### 生命周期

先上官方图，与activity类似，也可以理解fragment有运行、暂停、停止、销毁四个状态，具体到回调方法来说，fragment比activity多出一些。

<img src="https://developer.android.google.cn/images/guide/fragments/fragment-view-lifecycle.png" alt="fragment lifecycle states and their relation both the fragment's             lifecycle callbacks and the fragment's view lifecycle" style="zoom:50%;" />

不同于activity的几个回调：

* `onAttach()`：当Fragment和Activity建立连接时使用，此回调在`onCreate()`之前，图中未标出，当然，还有一个对应的回调`onDetach()`，在`onDestroy`后调用。

* `onCreateView()`：为Fragment创建视图（加载布局）时调用，即布局和Frgment的绑定就在此回调中实现。
* `onDestroyView()`：当与Fragment关联的视图被移除时调用，这和上个回调是对应的。

### Fragment与Activity的交互

Fragment必须依赖Activity进行工作，在Activity中可以可以使用 `findFragmentById()` 获取对布局容器中当前 Fragment 的引用，再利用Fragment管理器来对关联的Fragment进行操作，同时在Fragment中，也可以通过`getActivity()`方法获取到绑定的Activity实例。

下面来具体聊聊Fragment管理器。每个 `FragmentActivity` 及其子类（如 `AppCompatActivity`）都可以通过 `getSupportFragmentManager()` 方法访问 `FragmentManager`，然后继续后续操作。

> 突然联想到，activity默认继承的AppCompatActivity是继承自FragmentActivity，继续往上会到Activity类，最后会到Context抽象类，明白为啥activity可以作为context使用了吧。但是Fragment可不是Context的子类，Fragment没用父类。

`FragmentManager` 管理 Fragment 返回堆栈。在运行时，`FragmentManager` 可以执行添加或移除 Fragment 等返回堆栈操作来响应用户互动。每一组更改作为一个事务（称为 `FragmentTransaction`）一起提交。

例如，一个简单的 `FragmentTransaction` 可能如下所示：

```java
FragmentManager fragmentManager = getSupportFragmentManager();
fragmentManager.beginTransaction()
    .replace(R.id.fragment_container, ExampleFragment.class, null)
    .setReorderingAllowed(true)
    .addToBackStack("name") // name can be null
    .commit();
```

在本例中，`ExampleFragment` 会替换当前在由 `R.id.fragment_container` ID 标识的布局容器中的 Fragment（如果有）；`setReorderingAllowed(true)`可优化事务中涉及的 Fragment 的状态变化，以使动画和过渡正常运行；调用 `addToBackStack()`会将事务提交到返回堆栈，如果您在执行移除 Fragment 的事务时未调用 `addToBackStack()`，则提交事务时会销毁已移除的 Fragment，用户无法返回到该 Fragment。如果您在移除某个 Fragment 时调用了 `addToBackStack()`，则该 Fragment 只会 `STOPPED`，稍后当用户返回时它会 `RESUMED`。

### 在Fragment使用应用栏

有时需要仅在某个Fragment中使用应用栏，而不是在整个Activity中通用的应用栏，可以在Fragment对应的布局中使用`toolbar`。使用步骤如下：

1. 在XML添加toolbar

```xml
<androidx.appcompat.widget.Toolbar
    android:id="@+id/myToolbar"
    app:title="title"
    ... />
```

2. `Toolbar` 便捷方法 `inflateMenu(int)` 将菜单资源的 ID 作为参数。如需将 XML 菜单资源膨胀到工具栏中，请将 `resId` 传递给此方法，如以下示例所示：

```java
public class ExampleFragment extends Fragment {
    ...

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ...
        viewBinding.myToolbar.inflateMenu(R.menu.sample_menu);
    }
}
```

3. 处理点击事件：

```java
viewBinding.myToolbar.setOnMenuItemClickListener(item -> {
    switch (item.getItemId()) {
        case R.id.action_settings:
            // Navigate to settings screen
            return true;
        case R.id.action_done:
            // Save profile changes
            return true;
        default:
            return false;
    }
});
```




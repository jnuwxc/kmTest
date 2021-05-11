# 安卓Activity

[toc]

## 生命周期

Activity 类提供六个核心回调：`onCreate()`、`onStart()`、`onResume()`、`onPause()`、`onStop()` 和 `onDestroy()`。当 Activity 进入新状态时，系统会调用其中每个回调。

![img](https://developer.android.google.cn/guide/components/images/activity_lifecycle.png)

* **onCreate()**

创建。此时activity会进入创建的状态。

必须实现的回调，它会在系统首次创建 Activity 时触发。Activity 会在创建后进入“已创建”状态。应该在`onCreate()`中执行基本应用启动逻辑，该逻辑在 Activity 的整个生命周期中只应发生一次。常见的执行操作如：声明界面（在 XML 布局文件中定义）、定义成员变量，以及配置某些界面。具体到日常开发中，XML中view的绑定、点击事件以及`onSaveInstanceState`数据的处理都应在此处理。

已创建状态并不会持续，完成后会立刻进入已开始状态。

* **onStart()**

开始。此时activity会进入可见状态。

当 Activity 进入“已开始”状态时，系统会调用此回调。`onStart()` 调用使 Activity 对用户可见，因为应用会为 Activity 进入前台并支持互动做准备。例如，应用通过此方法来初始化维护界面的代码。该方法会非常快速地完成，并且与“已创建”状态一样，Activity 不会一直处于“已开始”状态。

什么代码应该在`onStart()`中编写呢？好像一直在`onCreate()`中写初始化的逻辑。

* **onResume()**

恢复。此时activity会进入与用户交互的状态。

这是应用与用户互动的状态。应用会一直保持这种状态，直到某些事件发生，让焦点远离应用。当发生中断事件时，Activity 进入“已暂停”状态，系统调用 `onPause()` 回调。

如果 Activity 从“已暂停”状态返回“已恢复”状态，系统将再次调用 `onResume()` 方法。因此，您应实现 `onResume()`，以初始化在 `onPause() `期间释放的组件，并执行每次 Activity 进入“已恢复”状态时必须完成的任何其他初始化操作。

* **onPause()**

暂停。此时activity会进入暂停状态，不再位于前台，不可与用户进行交互，但仍可见。

`onPause()` 执行非常简单，而且不一定要有足够的时间来执行保存操作。因此，您**不**应使用 `onPause()` 来保存应用或用户数据、进行网络调用或执行数据库事务。因为在该方法完成之前，此类工作可能无法完成。相反，您应在 `onStop()` 期间执行高负载的关闭操作。

`onPause()` 方法的完成并不意味着 Activity 离开“已暂停”状态。相反，Activity 会保持此状态，直到其恢复或变成对用户完全不可见，即调用`onResume`或者`onStop`。

* **onStop()**

停止。此时 Activity 不再对用户可见。

在 `onStop()` 方法中，应用应释放或调整在应用对用户不可见时的无用资源。例如，应用可以暂停动画效果，或从精确位置更新切换到粗略位置更新。还应使用 `onStop()` 执行 CPU 相对密集的关闭操作。例如，如果无法找到更合适的时机来将信息保存到数据库，可以在 `onStop()` 期间执行此操作。

* **onDestory()**

销毁。在activity彻底关闭之前调用。

进入销毁状态的原因可能有两个：activity即将结束(finish()或者用户关闭)，或由于配置变更（设备旋转或多窗口）

如果 Activity 即将结束，onDestroy() 是 Activity 收到的最后一个生命周期回调。如果由于配置变更而调用 onDestroy()，系统会立即新建 Activity 实例，然后在新配置中为新实例调用 `onCreate()`

`onDestroy()` 回调应释放先前的回调（例如 `onStop()`）尚未释放的所有资源。

**协调 Activity**

生命周期回调的顺序已有明确定义，特别是当两个 Activity 在同一个进程（应用）中，并且其中一个要启动另一个时。以下是 Activity A 启动 Activity B 时的操作发生顺序：

1. Activity A 的 `onPause()` 方法执行。
2. Activity B 的 `onCreate()`、`onStart()` 和 `onResume()` 方法依次执行（Activity B 现在具有用户焦点）。
3. 然后，如果 Activity A 在屏幕上不再显示，其 `onStop()` 方法执行。

## 保存和恢复界面状态

用户期望 Activity 的界面状态在整个配置变更（例如旋转或切换到多窗口模式）期间保持不变。但是，默认情况下，系统会在发生此类配置更改时销毁 Activity，从而清除存储在 Activity 实例中的任何界面状态。。当您的 Activity 因用户按下返回按钮或因其自行结束而被销毁时，系统和用户对该 `Activity`实例的概念将永远消失。在这些情况下，用户的期望与系统行为相匹配，您无需完成任何额外工作。

当 Activity 因系统限制而被销毁时，您应组合使用 [`ViewModel`](https://developer.android.google.cn/reference/androidx/lifecycle/ViewModel)、[`onSaveInstanceState()`](https://developer.android.google.cn/reference/android/app/Activity#onSaveInstanceState(android.os.Bundle)) 和/或本地存储来保留用户的瞬时界面状态。如果界面数据简单且轻量，例如原始数据类型或简单对象，您可以单独使用 `onSaveInstanceState()` 使界面状态在配置更改和系统启动的进程被终止时保持不变。

### onSaveInstanceState()

当您的 Activity 开始停止时，系统会调用 `onSaveInstanceState()` 方法，此方法会在`onStop()`之后调用。可以在`protected void onCreate(Bundle savedInstanceState) `的参数中获取该数据。按我的理解，好像只有横竖屏幕切换用得到这个？

**注意**：当用户显式关闭 Activity 时（如点击返回按钮，或者直接关闭等），或者在其他情况下调用 `finish()` 时，系统不会调用 `onSaveInstanceState()`。

```java
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("test", 19);
        Log.d(TAG, "onSaveInstanceState: 正在被销毁，保存数据ing");
        super.onSaveInstanceState(outState);
    }
```

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            Log.d(TAG, "onCreate: " + savedInstanceState.getInt("test"));
        }
    }
```

### ViewModel

这部分估计要重开一篇了，内容有点多。

## 跳转

根据Activity 是否希望从即将启动的新 Activity 中获取返回结果，您可以使用 `startActivity()` 或 `startActivityForResult()` 方法启动新 Activity。这两种方法都需要传入一个 `Intent` 对象，Intent的更多用法，后面再总结。

* `startActivity()`

直接显式启动一个activity

```java
Intent intent = new Intent(this, SignInActivity.class);
startActivity(intent);
```

隐式启动一个activity

```java
Intent intent = new Intent(Intent.ACTION_SEND);
intent.putExtra(Intent.EXTRA_EMAIL, recipientArray);
startActivity(intent);
```

* `startActivityForResult()`

有时希望在 Activity 结束时从 Activity 中获取返回结果，可以调用 `startActivityForResult(Intent, int)` 方法，其中整数参数会标识该调用（只是标识同一activity的不同调用）。结果通过重写`onActivityResult(int, int, Intent)` 方法处理。

当子级 Activity 退出时，它可以调用 `setResult(int)` 将数据返回到其父级。

**注意** 如果子级 Activity 由于任何原因（例如崩溃）而失败，父级 Activity 将收到代码为 `RESULT_CANCELED` 的结果。即不应该将setResutl方法放在`onStop()`或`onDestory()`等回调方法内，而应自己去处理，如按下返回键可以使用`onBackPressed()`回调来处理。

## 任务和返回栈

### 任务

任务是一个整体单元，当用户开始一个新任务或通过主屏幕按钮进入主屏幕时，任务可移至“后台”。在后台时，任务中的所有 Activity 都会停止，但任务的返回堆栈会保持不变，当其他任务启动时，当前任务只是失去了焦点。如果用户稍后通过点按该任务的启动器图标来恢复该任务，该任务会进入前台并恢复堆栈顶部的 Activity。简单来说，可以认为任务是activity的一个集合，每个activity可以有多个实例，可以存在于不同任务里，一个任务里面也有可能存在一个activity的多个实例。

任务中的activity会按照启动模式中的默认模式运行，如果想以另外的模式运行，可以借助`<activity>` 清单元素中的属性以及您传递给 `startActivity()` 的 intent 中的标记来实现上述目的。

在这方面，您可以使用的主要 `<activity>`属性包括：

- [`taskAffinity`](https://developer.android.google.cn/guide/topics/manifest/activity-element#aff)
- [`launchMode`](https://developer.android.google.cn/guide/topics/manifest/activity-element#lmode)
- [`allowTaskReparenting`](https://developer.android.google.cn/guide/topics/manifest/activity-element#reparent)
- [`clearTaskOnLaunch`](https://developer.android.google.cn/guide/topics/manifest/activity-element#clear)
- [`alwaysRetainTaskState`](https://developer.android.google.cn/guide/topics/manifest/activity-element#always)
- [`finishOnTaskLaunch`](https://developer.android.google.cn/guide/topics/manifest/activity-element#finish)

您可以使用的主要 intent 标记包括：

- `FLAG_ACTIVITY_NEW_TASK`
- `FLAG_ACTIVITY_CLEAR_TOP`
- `FLAG_ACTIVITY_SINGLE_TOP`

### 启动模式(launchMode)

在清单文件中声明 Activity 时，可以使用`launchMode` 属性指定 Activity 应该如何与任务关联。`launchMode`属性说明了 Activity 应如何启动到任务中。您可以为 `launchMode` 属性指定 4 种不同的启动模式：

* `"standard"`（默认模式）

默认值。系统在启动该 Activity 的任务中创建 Activity 的新实例，并将 intent 传送给该实例。Activity 可以多次实例化，每个实例可以属于不同的任务，一个任务可以拥有多个实例。

在当前 Activity 启动另一个 Activity 时，新的 Activity 将被推送到堆栈顶部并获得焦点。上一个 Activity 仍保留在堆栈中，但会停止。当 Activity 停止时，系统会保留其界面的当前状态。当用户按**返回**按钮时，当前 Activity 会从堆栈顶部退出（该 Activity 销毁），上一个 Activity 会恢复（界面会恢复到上一个状态）。堆栈中的 Activity 永远不会重新排列，只会被送入和退出，在当前 Activity 启动时被送入堆栈，在用户使用**返回**按钮离开时从堆栈中退出。因此，返回堆栈按照“后进先出”的对象结构运作

![img](https://developer.android.google.cn/images/fundamentals/diagram_backstack.png)

* `"singleTop"`（栈顶模式）

如果当前任务的顶部已存在 Activity 的实例，则系统会通过调用其 `onNewIntent()` 方法来将 intent 转送给该实例，而不是创建 Activity 的新实例。

* `"singleTask"`（栈内模式）

系统会创建新任务，并实例化新任务的根 Activity。但是，如果另外的任务中已存在该 Activity 的实例，则系统会通过调用其 `onNewIntent()` 方法将 intent 转送到该现有实例，而不是创建新实例。Activity 一次只能有一个实例存在。

* `"singleInstance"`

与 "singleTask" 相似，唯一不同的是系统不会将任何其他 Activity 启动到包含该实例的任务中。该 Activity 始终是其任务唯一的成员；由该 Activity 启动的任何 Activity 都会在其他的任务中打开。

standard和singleTop为一类，在这两种模式中，activity可以多次进行实例化，实例可以归属于任何任务。二者唯一的不同是启动新的activity在栈顶时是否创建新的实例。在大多数情况下，应该使用这两种模式。

singleTask和singleInstance为一类，指定为这两种模式的Activity 只能启动任务。它们始终位于 Activity 堆栈的根位置。指定为`singleTask`的Activity 允许其他 Activity 成为其任务的一部分，但singleInstance则只允许自己是任务中唯一的activity。

### 使用Intent标记

除了在XML中配置启动模式外，还可以使用intent标记来修改activity和任务的关系。

* `FLAG_ACTIVITY_NEW_TASK`

在新任务中启动 Activity。如果您现在启动的 Activity 已经有任务在运行，则系统会将该任务转到前台并恢复其最后的状态，而 Activity 将在 `onNewIntent()` 中收到新的 intent。

这与上一节中介绍的 `"singleTask"` `launchMode`值产生的行为相同。


* `FLAG_ACTIVITY_SINGLE_TOP`


如果要启动的 Activity 是当前 Activity（即位于返回堆栈顶部的 Activity），则现有实例会收到对 `onNewIntent()` 的调用，而不会创建 Activity 的新实例。

这与上一节中介绍的 `"singleTop"` `launchMode`值产生的行为相同。

* `FLAG_ACTIVITY_CLEAR_TOP`

如果要启动的activity已经在当前任务中运行，则不会启动该activity的新实例，而是会销毁位于它之上的所有其它activity，并通过 `onNewIntent()` 将此 intent 传送给它的已恢复实例（现在位于堆栈顶部）。

没有对应的的launchMode

把我看懵了。。。。。这三个+启动模式的四个，有点难理解。
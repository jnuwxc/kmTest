# Android UI学习

[TOC]

## Layout(布局)

### 简介

布局定义了应用中的界面结构（例如 Activity的界面结构）。布局中的所有元素均使用 `View` 和 `ViewGroup` 对象的层次结构进行构建。`View` 通常用于绘制用户可看到并与之交互的内容。`ViewGroup` 则是不可见的容器，用于定义 `View` 和其他 `ViewGroup` 对象的布局结构，如图所示：


![img](https://developer.android.google.cn/images/viewgroup_2x.png)

官方参考，见安卓开发社区的指南—界面—布局部分。[布局  | Android 开发者  | Android Developers (google.cn)](https://developer.android.google.cn/guide/topics/ui/declaring-layout)

开始之前：推荐使用`start`来代替`left`等，主要是为了照顾有些国家文字是从右边开始的，下面代码可能混用..

### 通用属性

#### 内外边距

另外约束布局还会有`gongMarigin`

```xml
<!--  外边距  -->
android:layout_margin="0dp"
android:layout_marginStart="0dp"
android:layout_marginLeft="0dp"
android:layout_marginTop="0dp"
android:layout_marginEnd="0dp"
android:layout_marginRight="0dp"
android:layout_marginBottom="0dp"

<!--  内边距  -->
android:padding="0dp"
android:paddingStart="0dp"
android:paddingLeft="0dp"
android:paddingTop="0dp"
android:paddingEnd="0dp"
android:paddingRight="0dp"
android:paddingBottom="0dp" 
```

#### 尺寸限制

设置`view`的大小主要有`wrap_content`、`指定尺寸`、`match_parent`、`0dp`等。

`0dp`的效果要在具体布局中体现了。

当宽高设置为`wrap_content`时，可以设置最大、最小宽高

```xml
android:minWidth=""   设置view的最小宽度
android:minHeight=""  设置view的最小高度
android:maxWidth=""   设置view的最大宽度
android:maxHeight=""  设置view的最大高度
```

### LinearLayout（线性布局）

#### 简介

线性布局，可以水平（horizontal）或竖直（vertical）的排列元素，默认为水平。要注意尽量减少线性布局的嵌套使用，嵌套会严重影响UI绘制性能。

注意在水平排列时就不应该把内部元素的宽度设置为`math_parent`，竖直时也要注意高度设置。

#### 常用属性

1. `android: orientation`，设定布局的排列方向，可选值为水平（horizontal）或竖直（vertical）。

2. `android: layout_gravity`，指定布局内控件的对齐方式，可选值较多，但是根据效果很好记忆。

要注意的是当为水平排列时，只能指定垂直方向上的排列，如`top`,`bottom`,`center_vertical`等，竖直排列同理。

| Constant          | Value  | Description                                                  |
| :---------------- | :----- | :----------------------------------------------------------- |
| bottom            | 50     | Push object to the bottom of its container, not changing its size. |
| center            | 11     | Place the object in the center of its container in both the vertical and horizontal axis, not changing its size. |
| center_horizontal | 1      | Place object in the horizontal center of its container, not changing its size. |
| center_vertical   | 10     | Place object in the vertical center of its container, not changing its size. |
| clip_horizontal   | 8      | Additional option that can be set to have the left and/or right edges of the child clipped to its container's bounds. The clip will be based on the horizontal gravity: a left gravity will clip the right edge, a right gravity will clip the left edge, and neither will clip both edges. |
| clip_vertical     | 80     | Additional option that can be set to have the top and/or bottom edges of the child clipped to its container's bounds. The clip will be based on the vertical gravity: a top gravity will clip the bottom edge, a bottom gravity will clip the top edge, and neither will clip both edges. |
| end               | 800005 | Push object to the end of its container, not changing its size. |
| fill              | 77     | Grow the horizontal and vertical size of the object if needed so it completely fills its container. |
| fill_horizontal   | 7      | Grow the horizontal size of the object if needed so it completely fills its container. |
| fill_vertical     | 70     | Grow the vertical size of the object if needed so it completely fills its container. |
| left              | 3      | Push object to the left of its container, not changing its size. |
| right             | 5      | Push object to the right of its container, not changing its size. |
| start             | 800003 | Push object to the beginning of its container, not changing its size. |
| top               | 30     | Push object to the top of its container, not changing its size. |

3. `android:layout_weight`，使用比例的方式来指定控件的大小。属性值设置为整形数值，如控件A为1，控件B为2，则会按照1:2的比列平分布局。若在水平方向使用，可以设置控件宽度为`0dp`，竖直则设置高度为`0dp`。

#### 代码示例

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:background="@color/teal_200">
        <Button
            android:layout_gravity="top"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="top" />
        <Button
            android:layout_gravity="center_vertical"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="center_vertical" />
        <Button
            android:layout_gravity="bottom"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Bottom" />
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:orientation="vertical"
        android:background="@color/purple_200">
        <Button
            android:layout_gravity="left"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="left" />
        <Button
            android:layout_gravity="center_horizontal"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="center_horizontal" />
        <Button
            android:layout_gravity="right"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="right" />
    </LinearLayout>
</LinearLayout>
```

### RelativeLayout（相对布局）

#### 简介

相对布局，与线性布局不同，相对布局会通过相对于其他控件或父布局的位置进行定位。

> RelativeLayout是用于设计用户界面的非常强大的实用程序，因为它可以消除嵌套视图组并保持布局层次结构平坦，从而提高性能。如果发现自己使用了几个嵌套的LinearLayout组，则可以用一个RelativeLayout替换它们。

#### 常用属性

线性布局的属性比较多，常用的有以下两类：

1. 相对于父布局定位。如`android: layout_alignParentLeft`、`android: layout_alignParentTop`、`android: layout_centerInparent`等。取值都为`true`/`false`。

2. 相对于其它控件定位。比如`android: layout_above`,`android: layout_below`表示在其它控件的上方或下方，值为控件的id，同理还有`android: layout_toLeftOf`等。还有一类是`android: alignLeft`等，表示该控件与取值的控件左边缘对齐。

注意：

RelativeLayout布局可能会出现view重叠现象，一般是后添加的View是位于顶层的（button在5.0之后好像不遵守这个规则，都将位于顶层）。

#### 代码示例

google官方示例：

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingLeft="16dp"
    android:paddingRight="16dp" >
    <EditText
        android:id="@+id/name"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="@string/reminder" />
    <Spinner
        android:id="@+id/dates"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_below="@id/name"
        android:layout_alignParentLeft="true"
        android:layout_toLeftOf="@+id/times" />
    <Spinner
        android:id="@id/times"
        android:layout_width="96dp"
        android:layout_height="wrap_content"
        android:layout_below="@id/name"
        android:layout_alignParentRight="true" />
    <Button
        android:layout_width="96dp"
        android:layout_height="wrap_content"
        android:layout_below="@id/times"
        android:layout_alignParentRight="true"
        android:text="@string/done" />
</RelativeLayout>
```

### FrameLayout（帧布局）

#### 简介

又叫做帧布局，该布局的控件会默认放到布局的左上角，后放置的控件会放在上层（上面也说过了，button除外），没有丰富的定位方式，一般会和`Fragment`结合起来使用。

也可以使用`android: layout_gravity`设置定位，这点和线性布局是一样的。

#### 常用属性

`android: layout_gravity`，不再赘述。

#### 代码示例

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <TextView
        android:layout_marginTop="13dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="先放置的文字"
        android:background="@color/teal_200"/>
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="后放置的文字"
        android:background="@color/purple_200"/>
    <Button
        android:layout_gravity="center"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="居中按钮" />
</FrameLayout>
```

### ConstraintLayout（约束布局）

#### 简介

约束布局，可让您使用扁平视图层次结构（无嵌套视图组）创建复杂的大型布局，与RelativeLayout类似，都是通过控件之间或者控件与父布局之间的关系进行定位，但约束布局更加灵活，也更容易通过可视化操作。这也是谷歌推荐也是默认使用的布局类型。

官方介绍（可视化操作较多）：[使用 ConstraintLayout 构建自适应界面  | Android 开发者  | Android Developers (google.cn)](https://developer.android.google.cn/training/constraint-layout)

布局编辑器：[使用布局编辑器构建界面  | Android 开发者  | Android Developers (google.cn)](https://developer.android.google.cn/studio/write/layout-editor)

详细介绍（代码操作较多）：https://juejin.cn/user/78820569533070/posts

#### 使用方法

`ConstraintLayout`采用方向约束的方式对控件进行定位，至少要保证水平和垂直方向都至少有一个约束才能确定控件的位置

**基本方向约束**

常用属性如下，注意是app开头，不是android开头。取值可以是`"parent"`，也可以是其它控件的id。

```xml
<!-- 基本方向约束 -->
<!-- 我的什么位置在谁的什么位置 -->
app:layout_constraintTop_toTopOf=""           我的顶部和谁的顶部对齐
app:layout_constraintBottom_toBottomOf=""     我的底部和谁的底部对齐
app:layout_constraintLeft_toLeftOf=""         我的左边和谁的左边对齐
app:layout_constraintRight_toRightOf=""       我的右边和谁的右边对齐
app:layout_constraintStart_toStartOf=""       我的开始位置和谁的开始位置对齐
app:layout_constraintEnd_toEndOf=""           我的结束位置和谁的结束位置对齐

app:layout_constraintTop_toBottomOf=""        我的顶部位置在谁的底部位置
app:layout_constraintStart_toEndOf=""         我的开始位置在谁的结束为止
<!-- ...以此类推 -->
```

可视化操作可以直接点按拖动控件上下左右的四个点到父布局或者其它控件，还可以输入距离数值（代码中通过margin来实现）。

**基线约束**

我们有时候需要写这样的需求：两个文本是基线对齐的，那就可以用到我们的一个属性`layout_constraintBaseline_toBaselineOf`来实现，它的意思就是这个控件的基线与谁的基线对齐。

```xml
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="20"
        android:textColor="@color/black"
        android:textSize="50sp"
        android:textStyle="bold"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="¥"
        android:textColor="@color/black"
        android:textSize="20sp"
        app:layout_constraintBaseline_toBaselineOf="@id/tv1"
        app:layout_constraintStart_toEndOf="@id/tv1" />
```

可视化操作：右键控件，选择“show Baseline”，然后拖动到其它控件的基线上。

**角度约束**

有些时候我们需要一个控件在某个控件的某个角度的位置，那么通过其他的布局其实是不太好实现的，但是`ConstraintLayout`为我们提供了角度位置相关的属性

```xml
app:layout_constraintCircle=""         目标控件id
app:layout_constraintCircleAngle=""    对于目标的角度(0-360)
app:layout_constraintCircleRadius=""   到目标中心的距离
```

**百分比约束**

有的时候我们需要让控件在父布局的水平方向或垂直方向的百分之多少的位置，可以使用如下属性：

```xml
app:layout_constraintHorizontal_bias=""   水平偏移 取值范围是0-1的小数
app:layout_constraintVertical_bias=""     垂直偏移 取值范围是0-1的小数
```

注意：在使用百分比偏移时，需要指定对应位置的约束条件。

```xml
        <TextView
            android:layout_width="110dp"
            android:layout_height="60dp"
            android:background="@color/purple_200"
            android:gravity="center"
            android:text="水平约束：0.3  竖直约束：0.8"
            android:textColor="@color/black"
            android:textSize="14sp"
            android:textStyle="bold"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHorizontal_bias="0.3"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />
```

**宽高0dp**

当宽高设置为0dp的时候，进行设置类型的属性是`layout_constraintWidth_default`和`layout_constraintHeight_default`，取值可为`spread、percent、wrap`

* `spread`：占用所有符合约束的空间
* `percent`：按照父布局的百分比设置，该模式需要配合`layout_constraintWidth_percent`使用，但是写了`layout_constraintWidth_percent`后，`layout_constraintWidth_default="percent"`其实就可以省略掉了。
* `wrap`：匹配内容大小但不超过约束限制

当宽高中的一个为0dp时，可以对宽高进行比例设置，`app:layout_constraintDimensionRatio=""` ，取值为：

* 浮点值
* 宽度:高度

**链**

`Chains(链)`也是一个非常好用的特性，它是将许多个控件在水平或者垂直方向，形成一条链，用于平衡这些控件的位置，那么如何形成一条链呢？形成一条链要求链中的控件在水平或者垂直方向，首尾互相约束，这样就可以形成一条链，水平方向互相约束形成的就是一条水平链，反之则是垂直链。

默认的模式是`spread`，均分剩余空间，我们可以使用`layout_constraintHorizontal_chainStyle`和`layout_constraintVertical_chainStyle`分别对水平和垂直链设置模式，模式可选的值有：`spread、packed、spread_inside`

- `spread（默认`：均分剩余空间

- `spread_inside`：两侧的控件贴近两边，剩余的控件均分剩余空间
- `packed`：所有控件贴紧居

注意链要加两边的约束，上下/左右，单侧是不行的。

`Chains(链)`还支持`weight（权重）`的配置，使用`layout_constraintHorizontal_weight`和`layout_constraintVertical_weight`进行设置链元素的权重。`layout_weight`直呼内行。

可视化可以通过将成链的控件全部圈起来，然后右键选择“chains"。

**参考线**

您可以添加垂直或水平的引导线来约束视图，并且应用用户看不到该引导线。 您可以根据相对于布局边缘的 dp 单位或百分比在布局中定位引导线。

```xml
android:orientation="horizontal|vertical"  辅助线的对齐方式
app:layout_constraintGuide_percent="0-1"   距离父级宽度或高度的百分比(小数形式)
app:layout_constraintGuide_begin=""        距离父级起始位置的距离(左侧或顶部)
app:layout_constraintGuide_end=""          距离父级结束位置的距离(右侧或底部)
```

**屏障约束**

与引导线类似，屏障是一条隐藏的线，您可以用它来约束视图。屏障不会定义自己的位置；相反，屏障的位置会随着其中所含视图的位置而移动。如果您希望将视图限制到一组视图而不是某个特定视图，这就非常有用。

```xml
<!--  用于控制Barrier相对于给定的View的位置  --> 
app:barrierDirection="top|bottom|left|right|start|end"   
<!--  取值是要依赖的控件的id，Barrier将会使用ids中最大的一个的宽/高作为自己的位置  --> app:constraint_referenced_ids="id,id"
```

**Group**

  工作当中常常会有很多个控件同时隐藏或者显示的场景，传统做法要么是进行嵌套，对父布局进行隐藏或显示，要么就是一个一个设置，这显然都不是很好的办法，`ConstraintLayout`中的`Group`就是来解决这个问题的。`Group`的作用就是可以对一组控件同时隐藏或显示，没有其他的作用，它的属性如下：

```xml
app:constraint_referenced_ids="id,id"  加入组的控件id
```

## 基础View

### Text

#### TextView

向用户显示文本的界面元素。

一些常用属性：

`android: gravity`，指定控件中文字的对齐方式，取值和效果与布局中用到的`android: layout_gravity`几乎一样。

文本的颜色、大小等不再赘述。

#### EditText

TextView的子类，允许用户在控件里面输入和编辑内容

一些常用的属性：

`android: hint`，指定一段提示文本，当输入时自动消失。

`android: maxLines`，指定EditText的最大行数。

`android: inputType`，指定预估输入的文本类型，为输入法和其它功能提供支持，应该尽可能的明确该属性。取值常见的有` number,phone,textPersonName,date  `等。

### shape

一些常用的图形

[android shape绘制常用的形状 - 简书 (jianshu.com)](https://www.jianshu.com/p/545fb65d6761?from=timeline)

#### shape的属性

```xml
android:shape=["rectangle" | "oval" | "line" | "ring"]   
shape的形状，默认为矩形，可以设置为矩形（rectangle）、椭圆形(oval)、线性形状(line)、环形(ring)   
下面的属性只有在android:shape="ring时可用：   
android:innerRadius         尺寸，内环的半径。   
android:innerRadiusRatio    浮点型，以环的宽度比率来表示内环的半径，   
android:thickness           尺寸，环的厚度   
android:thicknessRatio      浮点型，以环的宽度比率来表示环的厚度，例如，如果android:thicknessRatio="2"，   
android:useLevel            boolean值，如果当做是LevelListDrawable使用时值为true，否则为false.  
```

#### shape的子标签

1、Corners

```xml
<corners    //定义圆角   
    android:radius="dimension"      //全部的圆角半径   
    android:topLeftRadius="dimension"   //左上角的圆角半径   
    android:topRightRadius="dimension"  //右上角的圆角半径   
    android:bottomLeftRadius="dimension"    //左下角的圆角半径   
    android:bottomRightRadius="dimension" />    //右下角的圆角半径   
```

2、solid

solid用以指定内部填充色

只有一个属性：

`<solid android:color="color" /> ` 

3、stroke

这是描边属性，可以定义描边的宽度，颜色，虚实线等

```xml
<stroke        
    android:width="dimension"   //描边的宽度   
    android:color="color"   //描边的颜色   
    // 以下两个属性设置虚线   
    android:dashWidth="dimension"   //虚线的宽度，值为0时是实线   
    android:dashGap="dimension" />      //虚线的间隔  
```

4、size和padding

```xml
<size   
    android:width="dimension"   
    android:height="dimension" /> 
<padding    
    android:left="dimension"   
    android:top="dimension"   
    android:right="dimension"   
    android:bottom="dimension" /> 
```

5、渐变色

```xml
<gradient  
    android:type=["linear" | "radial" | "sweep"]    //共有3中渐变类型，线性渐变（默认）/放射渐变/扫描式渐变   
    android:angle="integer"     //渐变角度，必须为45的倍数，0为从左到右，90为从上到下   
    android:centerX="float"     //渐变中心X的相当位置，范围为0～1   
    android:centerY="float"     //渐变中心Y的相当位置，范围为0～1   
    android:startColor="color"   //渐变开始点的颜色   
    android:centerColor="color"  //渐变中间点的颜色，在开始与结束点之间   
    android:endColor="color"    //渐变结束点的颜色   
    android:gradientRadius="float"  //渐变的半径，只有当渐变类型为radial时才能使用   
    android:useLevel=["true" | "false"] />  //使用LevelListDrawable时就要设置为true。设为false时才有渐变效果   
```

### selector

selector就是状态列表（StateList）， 它分为两种，一种Color-Selector 和Drawable-Selector。

#### color_selector

颜色状态列表，可以跟color一样使用，颜色会随着组件的状态而改变，文件的存储在color目录下。

常用语法：

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android" >
    <item
        android:color="hex_color"               //颜色值，#RGB,$ARGB,#RRGGBB,#AARRGGBB
        android:state_pressed=["true" | "false"]//是否触摸 
        android:state_focused=["true" | "false"]//是否获得焦点
        android:state_selected=["true" | "false"]//是否被状态
        android:state_checkable=["true" | "false"]//是否可选
        android:state_checked=["true" | "false"]//是否选中
        android:state_enabled=["true" | "false"]//是否可用
        android:state_window_focused=["true" | "false"] />//是否窗口聚焦
</selector>
```

注意default属性放在最后一个item里面。

#### drawable_selector

背景图状态列表，可以跟图片一样使用，背景会根据组件的状态变化而变化。文件存储于drawable目录下。

常用语法：

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android"
    android:constantSize=["true" | "false"]//drawable的大小是否当中状态变化，true表示是变化，false表示不变换，默认为false
    android:dither=["true" | "false"]//当位图与屏幕的像素配置不一样时(例如，一个ARGB为8888的位图与RGB为555的屏幕)会自行递色(dither)。设置为false时不可递色。默认true
    android:variablePadding=["true" | "false"] >//内边距是否变化，默认false
    <item
        android:drawable="@[package:]drawable/drawable_resource"//图片资源
        android:state_pressed=["true" | "false"]//是否触摸
        android:state_focused=["true" | "false"]//是否获取到焦点
        android:state_hovered=["true" | "false"]//光标是否经过
        android:state_selected=["true" | "false"]//是否选中
        android:state_checkable=["true" | "false"]//是否可勾选
        android:state_checked=["true" | "false"]//是否勾选
        android:state_enabled=["true" | "false"]//是否可用
        android:state_activated=["true" | "false"]//是否激活
        android:state_window_focused=["true" | "false"] />//所在窗口是否获取焦点
</selector>
```

#### 示例

结合shape和selector自定义switch。

```xml
    <Switch
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:switchMinWidth="20dp"
        android:thumb="@drawable/thumb"
        android:track="@drawable/track"
        app:layout_constraintTop_toBottomOf="@id/divide3"
        app:layout_constraintBottom_toTopOf="@id/divide4"
        app:layout_constraintEnd_toStartOf="@id/guideRight"
        android:layout_marginRight="4dp"/>
```

`thumb`,`track`分别代表switch的圆形图片和整体图片（此处应该有图示。。。。

以track.xml 为例：

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_checked="true" android:drawable="@drawable/shape_checked_track"/>
    <item android:drawable="@drawable/shape_track"/>
</selector>
```

`shape_track.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle" >
    <!-- 高度25   此处设置宽度无效-->
    <size android:height="25dp"/>
    <!-- 圆角弧度 15 -->
    <corners android:radius="15dp"/>
    <!-- 变化率  定义从左到右的颜色不变 -->
    <gradient
        android:endColor="#eeeeee"
        android:startColor="#eeeeee" />
</shape>
```

`shape_checked_track.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle" >
    <!-- 高度25   此处设置宽度无效-->
    <size android:height="25dp"/>
    <!-- 圆角弧度 15 -->
    <corners android:radius="15dp"/>
    <!-- 变化率  定义从左到右的颜色不变 -->
    <gradient
        android:endColor="#F6D849"
        android:startColor="#F6D849" />
</shape>
```

### Dialog

对话框，提示用户做出决定或输入更多信息的小窗口。对话框不会填充屏幕，通常用于需要用户进行操作才能继续执行的模态框事件。

`Dialog` 类是对话框的基类，但您应避免直接实例化 `Dialog`，而是使用下列子类之一：

- `AlertDialog`

    此对话框可显示标题、最多三个按钮、可选择项列表或自定义布局。

- `DatePickerDialog` 或 `TimePickerDialog`

    此对话框带有允许用户选择日期或时间的预定义界面。

#### AlertDialog

1.简单使用

您可以使用 `AlertDialog` 类构建各种对话框设计，并且该类通常是您需要的唯一对话框类。主要包括三个部分：

* 标题：可选
* 内容区域，消息，列表，或其它自定义布局
* 操作按钮，最多三个

使用AlertDialog.Builder()构建简单的提醒对话框：

```java
AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
builder.setMessage(R.string.dialog_message)
       .setTitle(R.string.dialog_title);
builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
               // User clicked OK button
           }
       });
builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
               // User cancelled the dialog
           }
       });
AlertDialog dialog = builder.create();
```

除了ok和cancel外还有一个中性的的按钮，可以从中选择1-3个使用。

2.添加列表

可通过 `AlertDialog` API 提供三种列表：

- 传统的单选列表， 使用 `setItems()` 方法
- 永久性单选列表（单选按钮），使用  `setSingleChoiceItems()` 方法。
- 永久性多选列表（复选框）， 使用`setMultiChoiceItems()` 方法。

一个简单永久性单选列表示例

```java
alertDialogBtn.setOnClickListener(v -> {
    new AlertDialog.Builder(this)
        .setTitle("标题")
        .setSingleChoiceItems(new String[]{"选项1", "选项2", "选项3"}, 2, (dialog, which) -> {
            Toast.makeText(BasicViewActivity.this, "you clicked " + which, Toast.LENGTH_SHORT).show();
        })
        .setCancelable(false)
        .setPositiveButton("ok", (dialog, which) -> {
            //
        })
        .setNegativeButton("cancel", (dialog ,which)->{
            //
        })
        .show();
});
```

3.自定义布局

如果您想在对话框中使用自定义布局，请创建一个布局，然后通过对 `AlertDialog.Builder` 对象调用 `setView()`，将该布局添加至 `AlertDialog`。

> 如果您要自定义对话框，可以改用对话框的形式显示 `Activity`，而非使用 `Dialog` API。您只需创建一个 Activity，并在清单元素中将其主题背景设置为 `Theme.Holo.Dialog`：

### Menu(菜单)

#### 类型

- **选项菜单和应用栏**

    选项菜单是 Activity 的主菜单项集合，供您放置对应用产生全局影响的操作，如“搜索”、“撰写电子邮件”和“设置”。

- **上下文菜单和关联操作模式**

    上下文菜单是用户长按某元素时出现的悬浮菜单。该菜单提供的操作会影响所选内容或上下文框架。

    关联操作模式在屏幕顶部栏显示影响所选内容的操作项，并允许用户选择多项内容。

- **弹出式菜单**

    弹出式菜单以垂直列表形式显示一系列菜单项，并且该列表会锚定到调用该菜单的视图中。它特别适用于提供与特定内容相关的大量操作，或者为命令的后续部分提供选项。弹出式菜单中的操作**不会**直接影响对应的内容，而关联操作则会对其产生影响。相反，弹出式菜单适用于与 Activity 中的内容区域相关的扩展操作。

#### 在布局中定义

在xml中定义菜单内容，与逻辑分开。在项目的 `res/menu/` 目录内创建 XML 文件，并使用以下元素构建菜单：

- `<menu>`

    定义 `Menu`，即菜单项的容器。`<menu>` 元素必须是该文件的根节点，并可包含一个或多个 `<item>` 和 `<group>` 元素。

- `<item>`

    创建 `MenuItem`，此元素表示菜单中的一个菜单项。此元素可能包含嵌套的 `<menu>` 元素，以便创建子菜单。`<item>` 元素支持多个属性，您可使用这些属性定义菜单项的外观和行为。上述菜单中的菜单项包括以下属性：

    - `android:id`

        菜单项独有的资源 ID，让应用能够在用户选择菜单项时识别该菜单项。

    - `android:icon`

        引用一个要用作菜单项图标的可绘制对象。

    - `android:title`

        引用一个要用作菜单项标题的字符串。

    - `android:showAsAction`

        指定此菜单项在应用栏中作为操作项显示的时间和方式。

- `<group>`

    `<item>` 元素的不可见容器（可选）。利用该元素，您可以对菜单项进行分类，使同类元素共享活动状态和可见性等属性

#### 创建选项菜单

定义菜单布局：

```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <item
        android:id="@+id/backup"
        android:icon="@drawable/ic_backup"
        android:title="Backup"
        app:showAsAction="always" />

    <item
        android:id="@+id/delete"
        android:icon="@drawable/ic_delete"
        android:title="Delete"
        app:showAsAction="ifRoom" />

    <item
        android:id="@+id/setting"
        android:icon="@drawable/ic_settings"
        android:title="Setting"
        app:showAsAction="never" />

</menu>
```

在activity中膨胀布局：

```java
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.game_menu, menu);
    return true;
}
```

处理点击事件

```java
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    // Handle item selection
    switch (item.getItemId()) {
        case R.id.new_game:
            newGame();
            return true;
        case R.id.help:
            showHelp();
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
}
```

Android 3.0 新增一项功能，可让您在 XML 中使用 `android:onClick` 属性为菜单项定义点击行为。该属性的值必须是使用该菜单的 Activity 所定义的方法的名称。该方法必须是公用的，且接受单个 `MenuItem` 参数；当系统调用此方法时，它会传递所选的菜单项。

#### 上下文菜单

上下文菜单提供许多操作，这些操作会影响界面中的特定菜单项或上下文框架。您可以为任何视图提供上下文菜单，但这些菜单通常用于 `ListView`、`GridView` 或用户可直接操作其中每个菜单项的其他视图集合中的菜单项。

提供关联操作的方式有两种：

- 使用悬浮上下文菜单。当用户长按（按住）某个声明支持上下文菜单的视图时，菜单会显示为菜单项的悬浮列表（类似对话框）。用户一次可对一个菜单项执行关联操作。
- 使用关联操作模式。此模式是 `ActionMode` 的系统实现，它会在屏幕顶部显示“关联操作栏”，其中包含影响所选菜单项的操作项。当此模式处于活动状态时，用户可以同时对多个菜单项执行操作（如果应用允许）。

悬浮上下文，通过调用 `registerForContextMenu()`，注册应与上下文菜单关联的 `View` 并将其传递给 `View`。

```java
registerForContextMenu(view);
```

然后重写

```java
@Override
public void onCreateContextMenu(ContextMenu menu, View v,
                                ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.context_menu, menu);
}
```

处理点击事件

```java
@Override
public boolean onContextItemSelected(MenuItem item) {
    AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    switch (item.getItemId()) {
        case R.id.edit:
            editNote(info.id);
            return true;
        case R.id.delete:
            deleteNote(info.id);
            return true;
        default:
            return super.onContextItemSelected(item);
    }
}
```



#### 弹出式菜单

`PopupMenu` 是锚定在 `View` 中的模态菜单。如果空间足够，它会显示在锚定视图下方，否则显示在其上方。

```java
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.context_menu, popup.getMenu());
        popup.show();
    }
```

处理点击事件

```java
@Override
public boolean onMenuItemClick(MenuItem item) {
    switch (item.getItemId()) {
        case R.id.archive:
            archive(item);
            return true;
        case R.id.delete:
            delete(item);
            return true;
        default:
            return false;
    }
}
```







## 条目控件

### ListView

#### 使用例子

创建一个显示水果图片和名字的列表。

实体类Fruit，定义了水果实体，包括两个属性：名字和图片。

```java
public class Fruit {
    private String name;
    private int imageId;

    public Fruit(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }
    public String getName() {
        return name;
    }
    public int getImageId() {
        return imageId;
    }
}

```

适配器FruitAdapter，继承自ArrayAdapter，指定为Fruit泛型。定义主构造函数，将context、子项布局id和数据源传入，重写getView()方法，为该子项传入布局，并获取传入图片和名字。

```java
public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;

    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = getItem(position); // 获取当前项的Fruit实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent,
            false);
        ImageView fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
        TextView fruitName = (TextView) view.findViewById(R.id.fruit_name);
        fruitImage.setImageResource(fruit.getImageId());
        fruitName.setText(fruit.getName());
        return view;
    }
}
```

列表子布局fruit_item.xml

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal" android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <ImageView
        android:id="@+id/fruitImage"
        android:layout_width="40dp"
        android:layout_height="40dp"
        android:layout_gravity="center_vertical"
        android:layout_marginLeft="10dp"/>

    <TextView
        android:id="@+id/fruitName"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_vertical"
        android:layout_marginLeft="10dp"/>
</LinearLayout>
```

测试类ListViewActivity，构造数据源，设置适配器，处理点击事件。

```java

public class ListViewActivity extends AppCompatActivity {


    private ArrayList<Fruit> fruitArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initFruits();
        FruitAdapter adapter = new FruitAdapter(ListViewActivity.this, R.layout.fruit_item, fruitArrayList);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Fruit fruit = fruitArrayList.get(position);
            Toast.makeText(this, fruit.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    private void initFruits(){
        for(int i = 0; i < 7; i++){
            fruitArrayList.add(new Fruit("apple", R.drawable.apple_pic));
            fruitArrayList.add(new Fruit("banana", R.drawable.banana_pic));
            fruitArrayList.add(new Fruit("orange", R.drawable.orange_pic));
        }
    }
}
```

#### 优化

> 目前我们ListView的运行效率是很低的，因为在`FruitAdapter`的`getView()`方法中，每次都将布局重新加载了一遍，当ListView快速滚动的时候，这就会成为性能的瓶颈。
仔细观察会发现，`getView()`方法中还有一个`convertView`参数，这个参数用于将之前加载好的布局进行缓存，以便之后可以进行重用。      ——《第一行代码3》

> 不过，目前我们的这份代码还是可以继续优化的，虽然现在已经不会再重复去加载布局，但是每次在`getView()`方法中还是会调用`View`的`findViewById()`方法来获取一次控件的实例。我们可以借助一个`ViewHolder`来对这部分性能进行优化

修改后的Adapter中的getView()方法为：

```java
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        // 获取当前项的Fruit实例
        Fruit fruit = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = view.findViewById(R.id.fruitImage);
            viewHolder.fruitName = view.findViewById(R.id.fruitName);
            // 将ViewHolder存储到view中。
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if(fruit != null){
            viewHolder.fruitImage.setImageResource(fruit.getImageId());
            viewHolder.fruitName.setText(fruit.getName());
        }
        return view;
    }

    class ViewHolder{
        ImageView fruitImage;
        TextView fruitName;
    }
```

### RecyclerView

#### 使用例子

使用前记得添加RecyclerView的库依赖

和ListView使用同一个例子，只列出不同部分的代码：

适配器FruitAdapterR，继承自RecyclerView.Adapte，并将泛型指定为一个内部类ViewHolder。先看ViewHolder的代码，该代码继承自RecyclerView.ViewHolder，构造器传入子项的view，通过view获取到内部的控件。适配器必须重写三个函数，onCreateViewHolder为子项创建，将view传入到viewHolder中，点击事件也在这里面处理。onBindViewHolder主要用来对子项的数据进行赋值，getItemCount()是获取数据的个数。

另外点击事件与ListView略有不同，是对view中具体的控件进行点击事件的处理，实现代码也放入到了Adapter中。

```java
public class FruitAdapterR extends RecyclerView.Adapter<FruitAdapterR.ViewHolder> {

    private List<Fruit> fruitList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view){
            super(view);
            fruitImage = view.findViewById(R.id.fruitImage);
            fruitName = view.findViewById(R.id.fruitName);
        }
    }

    public FruitAdapterR(List<Fruit> fruitList){
        this.fruitList = fruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
       
        holder.fruitName.setOnClickListener(v -> {
            int position = holder.getAbsoluteAdapterPosition();
            Fruit fruit = fruitList.get(position);
            Toast.makeText(v.getContext(), "you clicked text " + fruit.getName(), Toast.LENGTH_SHORT).show();
        });
        return holder;
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = fruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }
    
    @Override
    public int getItemCount() {
        return fruitList.size();
    }
}
```

测试类，只列出了不同的部分。RecyclerView的布局排列不是由本身完成，而是由Manager去完成，共有三种：

`LinearLayoutManager`,	`GridLayoutManager`, 	`StaggereLayoutManager`。

```java
RecyclerView recyclerView = findViewById(R.id.recyclerView);
LinearLayoutManager layoutManager = new LinearLayoutManager(this);
recyclerView.setLayoutManager(layoutManager);
FruitAdapterR adapter = new FruitAdapterR(fruitList);
recyclerView.setAdapter(adapter);
```

#### 横向和瀑布流布局

通过三种LayoutManager，可以将列表设置为水平、竖直、方格、瀑布流布局，同时要记得更改子项布局的代码以适应不同的需求。

举例如横向布局：

修改子项布局

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="100dp"
    android:layout_height="wrap_content" >

    <ImageView
        android:id="@+id/fruit_image"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal" />

    <TextView
        android:id="@+id/fruit_name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:layout_marginTop="10dp" />

</LinearLayout>
```

设置LayoutManager

```java
LinearLayoutManager layoutManager = new LinearLayoutManager(this);
layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
recyclerView.setLayoutManager(layoutManager);
```

### ViewPager2

滑动视图允许您通过水平手指手势或滑动在同级屏幕（例如标签页）之间进行导航。此导航模式也称为“水平分页”。推荐使用ViewPager2来代替ViewPager1，下面所说的ViewPager均指ViewPager2。

#### 简单使用

ViewPager来自于RecyclerView，简单使用和使用RecyclerView并无多大差别。

在xml中使用`androidx.viewpager2.widget.ViewPager2`控件来添加分页，建立相应的适配器Adapter，然后在测试模块中使用给viewPager配置好适配器和数据即可，和RecyclerView的过程很像。如果只是简单使用，可以看这篇文章的2.1部分：[(8条消息) 官方 Viewpager 升级版 - ViewPager2 实战_willwaywang6-CSDN博客_viewpager2](https://blog.csdn.net/willway_wang/article/details/88725392)

![img](https://img-blog.csdnimg.cn/20190330134253124.gif)



#### ViewPager+TabLayout+Fragment

上面简单使用的代码并不推荐。官方推荐使用ViewPager+TabLayout（可选）+Fragment的组合来更好的使用分页

首先在ViewPagerFragment中构建界面中要显示的数据data，并将其传给Adapter。



```java
public class ViewPagerFragment extends Fragment {

    private String[] data ={"内容1","内容2","内容3","内容4","内容5","内容6","内容7"};
    ViewPager2 viewPager;
    ViewPagerAdapter viewPagerAdapter;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_pager_container, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewPagerAdapter = new ViewPagerAdapter(this, data);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(viewPagerAdapter);
        
        // tablayout与viewPager2的联动
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager,(tab, position) -> {
            tab.setText("" + (position+1));
        } ).attach();

    }
}
```

这是该Fragment对应的布局：

`TabLayout`。当与 `ViewPager2` 结合使用时，`TabLayout` 可以提供一种熟悉的界面，让用户在滑动视图中浏览各个页面。

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <com.google.android.material.tabs.TabLayout
        android:id="@+id/tab_layout"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:background="@color/teal_200"/>

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/pager"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1" />

</LinearLayout>
```

在适配器ViewPagerAdapter中，继承自FragmentStateAdapter，注意构造函数有3种，都是传入Fragment相关的变量，所以子项布局也需要是一个Fragment，而不像RecyclerView中可以直接传入View。

关于数据问题，我这里是将数据放置在了ViewPagerFragment中，然后一层层传入到子项中利用，实际使用中可能是子项本身的数据，这里只做演示。

```java
public class ViewPagerAdapter extends FragmentStateAdapter {

    private String[] data;
    public ViewPagerAdapter(@NonNull Fragment fragment, String[] data) {
        super(fragment);
        this.data = data;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new ViewPagerItemFragment(data[position]);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}
```

子项ViewPagerItemFragment，布局文件就简单放置了一个TextView。

```java
public class ViewPagerItemFragment extends Fragment {

    private String content;
    public ViewPagerItemFragment(String content){
        this.content = content;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_pager_item, container, false);
    }

    // 设置内容
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView textView = view.findViewById(R.id.viewPagerItemText);
        textView.setText("" + content);
    }
}
```

### ScrollView

一个视图组，允许滚动其中放置的视图层次结构。滚动视图中可能只有一个直接子级。要在滚动视图中添加多个视图，请使直接子级添加一个视图组，例如LinearLayout，然后在该LinearLayou中放置其他视图。

滚动视图仅支持垂直滚动。对于水平滚动，请改用HorizontalScrollView。

切勿将RecyclerView或ListView添加到滚动视图。这样做会导致不良的用户界面性能和不良的用户体验。

> 对于垂直滚动，请考虑使用NestedScrollView而不是滚动视图，它可以提供更大的用户界面灵活性并支持材质设计滚动模式。

### NestedSrcollView

当需要在另一个滚动视图中滚动视图时,使用名称建议的NestedScrollView.通常这很难实现,因为系统无法决定滚动哪个视图。这是NestedScrollView的用武之地.

这里要补充很多嵌套滑动的知识点了。。。。。

这里举一个简单的例子：

如果外层使用`SrcollView`，那在滑动时的体验会很差，因为不支持嵌套滑动，会有一顿一顿的感觉。

而`NestedScrollView`则原生支持嵌套滑动。

```xml
    <androidx.core.widget.NestedScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <TextView
                android:layout_width="match_parent"
                android:layout_height="300dp"
                android:text="测试"
                android:background="@color/teal_200"/>

            <androidx.recyclerview.widget.RecyclerView
                android:id="@+id/recyclerView1"
                android:layout_width="match_parent"
                android:layout_height="wrap_content" />
        </LinearLayout>
    </androidx.core.widget.NestedScrollView>
```

## 动画

### 帧动画(Frame)

帧动画非常容易理解，其实就是简单的由N张静态图片收集起来，然后我们通过控制依次显示这些图片来达到动画的效果。

#### 实现方法

1.在XML中配置相关图片和属性。然后在java中调用start()或者stop()方法控制开始和结束。

2.也可以在Java代码中创建逐帧动画，创建AnimationDrawable对象，然后调用 addFrame(Drawable frame,int duration)向动画中添加帧，接着调用start()和stop()而已

#### 例子

在res下创建一个目录存放帧动画用到的图片，然后编写动画文件test_gif.xml。`oneshot`代表是否只播放一次。

```xml
<?xml version="1.0" encoding="utf-8"?>
<animation-list xmlns:android="http://schemas.android.com/apk/res/android"
    android:oneshot="false">
    <item
        android:drawable="@mipmap/img_miao1"
        android:duration="80" />
    <item
        android:drawable="@mipmap/img_miao2"
        android:duration="80" />
    <item
        android:drawable="@mipmap/img_miao3"
        android:duration="80" />
    <!--限于篇幅，省略其他item，自己补上-->
    ...
</animation-list>
```

然后在要使用的地方配置动画文件即可，如在`ImageView`中使用：

```xml
    <ImageView
        android:id="@+id/img_show"
        android:layout_width="120dp"
        android:layout_height="120dp"
        android:layout_gravity="center"
        android:background="@anim/test_gif" />
```

然后在Java中逻辑控制

```java
img_show = (ImageView) findViewById(R.id.img_show);
anim = (AnimationDrawable) img_show.getBackground();
anim.start();
// anim.stop();
```

### 补间动画(Tween)

谷歌更喜欢叫这个为视图动画，可以实现一些常用的效果，但是只能应用于view中的部分属性。

> 视图动画系统的设置需要的时间较短，需要编写的代码也较少。如果视图动画可以完成您需要执行的所有操作，或者现有代码已按照您需要的方式运行，则无需使用属性动画系统。

#### 效果

- `AlphaAnimation：`透明度渐变效果，创建时许指定开始以及结束透明度，还有动画的持续 时间，透明度的变化范围(0,1)，0是完全透明，1是完全不透明；对应`<alpha/>`标签
- `ScaleAnimation`：缩放渐变效果，创建时需指定开始以及结束的缩放比，以及缩放参考点， 还有动画的持续时间；对应`<scale/>`标签！
- `TranslateAnimation`：位移渐变效果，创建时指定起始以及结束位置，并指定动画的持续 时间即可；对应`<translate/>`标签！
- `RotateAnimation`：旋转渐变效果，创建时指定动画起始以及结束的旋转角度，以及动画 持续时间和旋转的轴心；对应`<rotate/>`标签
- `AnimationSet`：组合渐变，就是前面多种渐变的组合，对应`<set/>`标签

#### 插值器

用来控制动画的变化速度，自带了一些常用的速度方法，然后还可以自己实现`Interpolator`接口。

* `LinearInterpolator`：动画以均匀的速度改变

* `AccelerateInterpolator`：在动画开始的地方改变速度较慢，然后开始加速

* `AccelerateDecelerateInterpolator`：在动画开始、结束的地方改变速度较慢，中间时加速

* `CycleInterpolator`：动画循环播放特定次数，变化速度按正弦曲线改变： Math.sin(2 * mCycles * Math.PI * input)

* `DecelerateInterpolator`：在动画开始的地方改变速度较快，然后开始减速

* `AnticipateInterpolator`：反向，先向相反方向改变一段再加速播放

* `AnticipateOvershootInterpolator`：开始的时候向后然后向前甩一定值后返回最后的值

* `BounceInterpolator`： 跳跃，快到目的值时值会跳跃，如目的值100，后面的值可能依次为85，77，70，80，90，100

* `OvershottInterpolator`：回弹，最后超出目的值然后缓慢改变到目的值

而这个东东，我们一般是在写动画xml文件时会用到，属性是：android:interpolator， 而上面对应的值是：@android:anim/linear_interpolator，其实就是驼峰命名法变下划线而已 AccelerateDecelerateInterpolator对应：@android:anim/accelerate_decelerate_interpolator

#### 动画状态的监听

调用动画对象的`setAnimationListener(new AnimationListener())方法`，然后重写3个方法，分别对应动画的开始，结束和重复。

```java
animation.setAnimationListener(new Animation.AnimationListener() {
    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }
});
```

#### 例子

以组合渐变为例：

`anim_set.xml`

```xml
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:interpolator="@android:anim/decelerate_interpolator"
    android:shareInterpolator="true" >

    <scale
        android:duration="2000"
        android:fromXScale="0.2"
        android:fromYScale="0.2"
        android:pivotX="50%"
        android:pivotY="50%"
        android:toXScale="1.5"
        android:toYScale="1.5" />

    <rotate
        android:duration="1000"
        android:fromDegrees="0"
        android:repeatCount="1"
        android:repeatMode="reverse"
        android:toDegrees="360" />

    <translate
        android:duration="2000"
        android:fromXDelta="0"
        android:fromYDelta="0"
        android:toXDelta="320"
        android:toYDelta="0" />

    <alpha
        android:duration="2000"
        android:fromAlpha="1.0"
        android:toAlpha="0.1" />

</set>
```

```java
animation = AnimationUtils.loadAnimation(this, R.anim.anim_set);
img_show.startAnimation(animation);
```

### 属性动画(Property)

视图动画系统仅提供为 `View` 对象添加动画效果的功能，因此，如果您想为非 对象添加动画效果，则必须实现自己的代码才能做到。视图动画系统也存在一些限制，因为它仅公开 对象的部分方面来供您添加动画效果；例如，您可以对视图的缩放和旋转添加动画效果，但无法对背景颜色这样做。

视图动画系统的另一个缺点是它只会在绘制视图的位置进行修改，而不会修改实际的视图本身。例如，如果您为某个按钮添加了动画效果，使其可以在屏幕上移动，该按钮会正确绘制，但能够点击按钮的实际位置并不会更改，因此您必须通过实现自己的逻辑来处理此事件。

有了属性动画系统，您就可以完全摆脱这些束缚，还可以为任何对象（视图和非视图）的任何属性添加动画效果，并且实际修改的是对象本身。属性动画系统在执行动画方面也更为强健。概括地讲，您可以为要添加动画效果的属性（例如颜色、位置或大小）分配 Animator，还可以定义动画的各个方面，例如多个 Animator 的插值和同步。

#### API概览

主要包括三个类，其中比较常用的是`ObjectAnimator`。

| 类               | 说明                                                         |
| :--------------- | :----------------------------------------------------------- |
| `ValueAnimator`  | 属性动画的主计时引擎，它也可计算要添加动画效果的属性的值。它具有计算动画值所需的所有核心功能，同时包含每个动画的计时详情、有关动画是否重复播放的信息、用于接收更新事件的监听器以及设置待评估自定义类型的功能。为属性添加动画效果分为两个步骤：计算添加动画效果之后的值，以及对要添加动画效果的对象和属性设置这些值。`ValueAnimator` 不会执行第二个步骤，因此，您必须监听由 `ValueAnimator` 计算的值的更新情况，并使用您自己的逻辑修改要添加动画效果的对象。如需了解详情，请参阅[使用 ValueAnimator 添加动画效果](https://developer.android.google.cn/guide/topics/graphics/prop-animation#value-animator)部分。 |
| `ObjectAnimator` | `ValueAnimator` 的子类，用于设置目标对象和对象属性以添加动画效果。此类会在计算出动画的新值后相应地更新属性。在大多数情况下，您不妨使用 `ObjectAnimator`，因为它可以极大地简化对目标对象的值添加动画效果这一过程。不过，有时您需要直接使用 `ValueAnimator`，因为 `ObjectAnimator` 存在其他一些限制，例如要求目标对象具有特定的访问器方法。 |
| `AnimatorSet`    | 此类提供一种将动画分组在一起的机制，以使它们彼此相对运行。您可以将动画设置为一起播放、按顺序播放或者在指定的延迟时间后播放。如需了解详情，请参阅[使用 AnimatorSet 编排多个动画](https://developer.android.google.cn/guide/topics/graphics/prop-animation#choreography)部分。 |

评估程序，负责告知属性动画系统如何计算指定属性的值

| 类/接口          | 说明                                                         |
| :--------------- | :----------------------------------------------------------- |
| `IntEvaluator`   | 这是用于计算 `int` 属性的值的默认评估程序。                  |
| `FloatEvaluator` | 这是用于计算 `float` 属性的值的默认评估程序。                |
| `ArgbEvaluator`  | 这是用于计算颜色属性的值（用十六进制值表示）的默认评估程序。 |
| `TypeEvaluator`  | 此接口用于创建您自己的评估程序。如果您要添加动画效果的对象属性不是 `int`、`float` 或颜色，那么您必须实现 `TypeEvaluator` 接口，才能指定如何计算对象属性添加动画效果之后的值。如果您想以不同于默认行为的方式处理 `int`、`float`和颜色，您还可以为这些类型的值指定自定义 `TypeEvaluator`。如需详细了解如何编写自定义评估程序，请参阅[使用 TypeEvaluator](https://developer.android.google.cn/guide/topics/graphics/prop-animation#type-evaluator) 部分。 |

插值器，这个和补间动画类似。

#### 动画监听

谷歌官方难得文档写的不错的一部分，直接看官方介绍吧：

[属性动画概览  | Android 开发者  | Android Developers (google.cn)](https://developer.android.google.cn/guide/topics/graphics/prop-animation#layout)

## Material Design

### SwipeRefreshLayout(下拉刷新)

#### 简介

为应用添加下拉刷新功能，只需将需要下拉刷新的控件放到SwipeRefreshLayout中即可，在逻辑中再处理刷新逻辑即可。一些常用方法如下：

| 方法                                                         | 解释                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| setColorSchemeResources(int…colorReslds)                     | 设置下拉进度条的颜色主题，参数可变，并且是资源id，最多设置四种不同的颜色。 |
| setProgressBackgroundSchemeResource(int coloRes)             | 设置下拉进度条的背景颜色,默认白色。                          |
| isRefreshing()                                               | 判断当前的状态是否是刷新状态。                               |
| setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) | 设置监听，需要重写onRefresh()方法，顶部下拉时会调用这个方法，在里面实现请求数据的逻辑，设置下拉进度条消失等等。 |
| setRefreshing(boolean refreshing)                            | 设置刷新状态，true表示正在刷新，false表示取消刷新。          |

#### 使用

导入依赖

```json
implementation "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
```

在布局中使用

```xml
    <androidx.swiperefreshlayout.widget.SwipeRefreshLayout
        android:id="@+id/swipeRefresh"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:id="@+id/swipeText"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="下拉刷新"
            android:textSize="32sp"
            />
    </androidx.swiperefreshlayout.widget.SwipeRefreshLayout>
```

在逻辑代码中处理刷新

```java
        textView = findViewById(R.id.swipeText);
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        // 设置刷新进度条的颜色
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                count = count+1;
                textView.setText("下拉刷新第 " + count + " 次");
                // 设置刷新完成，否则会一直处于刷新状态
                swipeRefreshLayout.setRefreshing(false);
            }
        });
```

### Toolbar(工具栏)

#### 简介

ActionBar的上位替代品，在应用程序内容中使用的标准工具栏。

工具栏是在应用程序布局中使用的操作栏的概括。传统上，操作栏是由框架控制的Activity不透明窗口装饰的一部分，而工具栏则可以放置在视图层次结构中的任意嵌套级别。应用程序可以选择使用`setSupportActionBar（）`方法将工具栏指定为Activity的操作栏。

与ActionBar相比，工具栏支持更集中的功能集。可以添加以下内容在工具栏中（从start到end）：

导航按钮、品牌logo、标题和副标题、一个或多个自定义view、动作菜单(action menu)。

> 动作菜单将钉在工具栏的末端，提供一些常用的、重要的或典型的动作，同时还有一个可选的溢出菜单，用于其他动作。行动按钮在工具栏的最小高度内垂直排列，如果设置了的话。

#### 例子

设置toolbar，并添加一个动作菜单。

主布局

```xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MaterialDesignActivity">
    
    <androidx.appcompat.widget.Toolbar
        android:id="@+id/toolBar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="@color/design_default_color_primary"
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
        app:popupTheme="@style/ThemeOverlay.AppCompat.Light" />

</FrameLayout>
```

动作菜单的布局，`showAsAction`属性用来指定menu的显示情况。

```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <item
        android:id="@+id/backup"
        android:icon="@drawable/ic_backup"
        android:title="Backup"
        app:showAsAction="always" />

    <item
        android:id="@+id/delete"
        android:icon="@drawable/ic_delete"
        android:title="Delete"
        app:showAsAction="ifRoom" />

    <item
        android:id="@+id/setting"
        android:icon="@drawable/ic_settings"
        android:title="Setting"
        app:showAsAction="never" />

</menu>
```

启用toolbar

```java
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
```

加载菜单和处理点击事件

```java
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup :
                Toast.makeText(this, "you clicked backup", Toast.LENGTH_SHORT).show();
                break;
            ...
            ...
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return true;
    }
```

### DrawerLayout(滑动菜单)

#### 简介

DrawerLayout充当窗口内容的顶级容器，允许从窗口的一个或两个垂直边缘拉出交互式“抽屉”视图。

抽屉的位置和布局是使用子视图上的android：layout_gravity属性控制的，子视图对应于您希望抽屉从哪一侧出现：左或右（或在支持布局方向的平台版本上开始/结束。）请注意，窗口的每个垂直边缘只能有一个抽屉视图。如果您的布局在窗口的每个垂直边缘配置了一个以上的抽屉视图，则将在运行时引发异常。

要使用DrawerLayout，请将您的主要内容视图定位为第一个孩子，其宽度和高度为match_parent，而没有layout_gravity>。在主要内容视图之后添加抽屉作为子视图，并适当设置layout_gravity。抽屉通常将match_parent用于具有固定宽度的高度。

DrawerLayout.DrawerListener可用于监视抽屉视图的状态和运动。避免在动画过程中执行昂贵的操作，例如布局，因为这样会导致结结；尝试在STATE_IDLE状态期间执行昂贵的操作。 DrawerLayout.SimpleDrawerListener提供每个回调方法的默认/无操作实现。

根据Android设计指南，位于左侧/开始位置的所有抽屉都应始终包含用于浏览应用程序的内容，而位于右侧/末端的任何抽屉均应始终包含对当前内容采取的操作。这将保留与“操作栏”和其他位置相同的左导航，右操作结构。

#### 例子

布局文件

```xml
<androidx.drawerlayout.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/drawerLayout">
    
    <FrameLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">
		....
        ....
    </FrameLayout>

    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="this is menu"
        android:textSize="32sp"
        android:background="#fff"
        android:layout_gravity="start"
        />

</androidx.drawerlayout.widget.DrawerLayout>

```

可以在逻辑文件中设置启动滑动布局，一般用于点击按钮/图标来打开滑动布局，参数为打卡的方向。

```java
drawerLayout.openDrawer(GravityCompat.START);
```

滑动布局中的界面，可以自己定义，更好的是使用`Navigation View`。

### CoordinatorLayout

加强版的`FrameLayout`，可以监听所有子控件的各种事件，并自动帮助我们做出最为合理的响应。

`CoordinatorLayout`的使用核心是`Behavior`。

没太看懂，回头再看

```groovy
implementation "androidx.coordinatorlayout:coordinatorlayout:1.1.0"
```


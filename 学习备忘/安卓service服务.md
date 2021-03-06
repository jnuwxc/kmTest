# 安卓service服务

`Service` 是一种可在后台执行长时间运行操作而不提供界面的应用组件。服务可由其他应用组件启动，而且即使用户切换到其他应用，服务仍将在后台继续运行。此外，组件可通过绑定到服务与之进行交互，甚至是执行进程间通信 (IPC)。例如，服务可在后台处理网络事务、播放音乐，执行文件 I/O 或与内容提供程序进行交互。

服务主要分为三种：前台服务，后台（启动和绑定）

补充：关于服务与线程

简单的说，服务是一种即使用于与应用未进行交互也可以在后台运行的组件。而线程是在与应用交互时执行其它操作，如仅是在activity运行时播放一些音乐，可以考虑使用线程而无需使用服务。注意：服务并不会自动开启一个新线程，而是在其托管的主线程中运行，如果服务中要执行耗时工作，要自行创建一个线程。

### 后台Service

虽然分开概括讨论启动服务和绑定服务，但服务可同时以这两种方式运行，换言之，它既可以是启动服务（以无限期运行），亦支持绑定。唯一的问题在于您是否实现一组回调方法：`onStartCommand()`（让组件启动服务）和 `onBind()`（实现服务绑定）。

首先认识一下service的生命周期，左面是启动式的service，右边是绑定式的。启动服务在其它组件调用`startService`时创建，直到调用`stopService()`或者`stopSelf()`；而绑定服务是在其它组件调用`bindService()`方法时创建，并通过`IBinder`接口来与客户端通信，客户端可以使用`ubBindService()`来接触绑定，若服务的多个绑定都已经被解除，服务会自动停止。两种方式并不是独立分隔开的，而是交杂在一起的（虽然图中分开画的），比如第一次绑定和创建都会回调`onCreate`，销毁时都回调`onDestory`。

![img](https://developer.android.google.cn/images/service_lifecycle.png)

接着来具体的代码了解一下使用：

无论是启动服务还是绑定服务，都会使用Intent来指定服务，注意应该使用显示Intent，隐式已经不可使用。使用系统创建的服务都会在清单文件中自动声明，如果自定义记得声明，声明中必须声明的是name，常用的还有`android:exported`属性，若为false，则只有本应用可以使用此服务，还有`android:description`，用来描述服务。

启动服务：

```java
Intent intent = new Intent(this, HelloService.class);
startService(intent);
```

绑定服务：

其中connection为实现了ServiceConnection接口的对象，该接口有两个必须实现的方法`onServiceConnected()`和`onServiceDisconnected()`，分别用来处理连接和意外断开连接时应进行的操作。

```java
Intent intent = new Intent(getContext(), MyService.class);
bindService(intent, connection, Context.BIND_AUTO_CREATE);
// unbindService(connection); 断开连接
```

### IntentService

由于默认扩展service的服务不会自动创建线程，也无法自动停止，可以改为扩展IntentService，但这也意味着只能实现单线程的功能，若要求服务实现多线程的操作，还是扩展类service。

`IntentService` 类会执行以下操作：

- 创建默认的工作线程，用于在应用的主线程外执行传递给 `onStartCommand()` 的所有 Intent。
- 创建工作队列，用于将 Intent 逐一传递给 `onHandleIntent()` 实现，这样您就永远不必担心多线程问题。
- 在处理完所有启动请求后停止服务，因此您永远不必调用 `stopSelf()`。
- 提供 `onBind()` 的默认实现（返回 null）。
- 提供 `onStartCommand()` 的默认实现，可将 Intent 依次发送到工作队列和 `onHandleIntent()` 实现。

示例代码：

```java
public class HelloIntentService extends IntentService {

      //构造函数必需有，必须调用父类的构造，传入的参数为字符串，仅在调试时才会用到。
      public HelloIntentService() {
          super("HelloIntentService");
      }
	  //必须实现的方法，
      @Override
      protected void onHandleIntent(Intent intent) {
          //实现逻辑，方法结束会自动停止服务
          ...
      }
}
```

当然，也可以实现其它回调方法，但要确保超类实现（onCreate, onStartCommand, onDestroy），唯一无需实现的是onBind。

### 前台service

后台服务容易在资源不足时被安卓系统回收，所以当程序执行一些需用用户注意到的服务时，考虑使用前台服务，如在通知栏显示播放音乐的相关信息。前台服务会显示在通知栏中，所以和通知一样，也需要优先级，前台服务应使用`PRIORITY_LOW`或更高优先级的通知栏通知。

前台服务在28以上的版本必须请求（普通）权限

`<uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>`

创建前台service的代码示例：

```java
Intent notificationIntent = new Intent(this, ExampleActivity.class);
PendingIntent pendingIntent =
        PendingIntent.getActivity(this, 0, notificationIntent, 0);

Notification notification =
          new Notification.Builder(this, CHANNEL_DEFAULT_IMPORTANCE)
    .setContentTitle(getText(R.string.notification_title))
    .setContentText(getText(R.string.notification_message))
    .setSmallIcon(R.drawable.icon)
    .setContentIntent(pendingIntent)
    .setTicker(getText(R.string.ticker_text))
    .build();

// Notification ID cannot be 0.
startForeground(ONGOING_NOTIFICATION_ID, notification);
```

若想主动关闭前台service，直接使用stopService()即可。

谨慎使用前台服务。

### AIDL

Android接口定义语言，简称AIDL。主要用于实现进程间通信(IPC)，在安卓系统中，进程之间是无法直接进行通信的，安卓提供了很多种方法实现进程通信，适用于不同的情况，如使用Binder、Messager、文件、ContentProvider等。

**注意：**只有在需要不同应用的客户端通过 IPC 方式访问服务，并且希望在服务中进行多线程处理时，您才有必要使用 AIDL。如果您无需跨不同应用执行并发 IPC，则应通过实现 Binder 来创建接口；或者，如果您想执行 IPC，但*不*需要处理多线程，请使用 Messenger 来实现接口。

#### 定义AIDL接口

在src目录下新建.aidl文件，然后使用Java语言构建，但与Java语法有些不同，只支持以下基本类型

```java
int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString
```

以及以这些基本类型组合的list和map。

构建应用时，SDK 工具便会在项目的 `gen/` 目录中生成 `IBinder` 接口文件。生成文件的名称与 `.aidl` 文件的名称保持一致，区别在于其使用 `.java` 扩展名。

```java
interface IMyAidlInterface {
    int getPid();
    
    // 这个不用管，默认就有，提醒你只能用这几个基本类型
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
```

#### 实现接口

当您构建应用时，Android SDK 工具会生成以 `.aidl` 文件命名的 `.java` 接口文件。生成的接口包含一个名为 `Stub` 的子类，该子类是其父接口的抽象实现，并且会声明 `.aidl` 文件中的所有方法。如要实现 `.aidl` 生成的接口，请扩展生成的 `Binder` 接口，并实现继承自 `.aidl` 文件的方法。

```java
public class AIDLService extends Service {
    public AIDLService() {
    }
    @Override
    public IBinder onBind(Intent intent) {
        MyToast.toast("绑定成功");
        return new MyBinder();
    }
    class MyBinder extends IMyAidlInterface.Stub{
        @Override
        public int getPid() throws RemoteException {
            return Process.myPid();
        }
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        }
    }
}
```

#### 客户端使用

在为服务实现接口后，您需要向客户端公开该接口，以便客户端进行绑定。如要为您的服务公开该接口，请扩展 `Service` 并实现 `onBind()`，从而返回实现生成的 `Stub` 的类实例（如前文所述）

```java
class AIDLConnection implements ServiceConnection{

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        myBinder = (AIDLService.MyBinder) IMyAidlInterface.Stub.asInterface(service);
        isBindAIDLService = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
```


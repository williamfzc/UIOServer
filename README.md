# simhand2

> UI OPERATOR SERVER ON ANDROID

## 原理

- 基于uiautomator
- 通过提供的restful API，直接通过http请求操作设备

## 安装

### 使用安装脚本

- 连接设备
- 运行`install.py`
- 确保python3已安装，并安装相关依赖包

### 手动

Open this project with android studio, and run `StubTestCase`.

Or follow steps below:

```
// Push Main Apk
$ adb push F:\simhand2\app\build\outputs\apk\debug\app-debug.apk /data/local/tmp/com.github.williamfzc.simhand2
$ adb shell pm install -t -r "/data/local/tmp/com.github.williamfzc.simhand2"

// Push TestCase Apk
$ adb push F:\simhand2\app\build\outputs\apk\androidTest\debug\app-debug-androidTest.apk /data/local/tmp/com.github.williamfzc.simhand2.test
$ adb shell pm install -t -r "/data/local/tmp/com.github.williamfzc.simhand2.test"

// Run Case
$ adb shell am instrument -w -r   -e debug false -e class 'com.github.williamfzc.simhand2.StubTestCase' com.github.williamfzc.simhand2.test/android.support.test.runner.AndroidJUnitRunner
```

## 使用

When need UI communication, Just send a http request. 

Click widget which named 'camera':

```
http://192.168.0.10:8080/api/action/click?widgetName=camera
```

Also, request from android inside would work well:

```
http://127.0.0.1:8080/api/action/click?widgetName=camera
```

## 相关项目

[simhand2 manager](https://github.com/williamfzc/simhand2_manager)

## API 文档

Still building. Offer what we actually need only.

### Screen Shot

screenshot (get image/png)

```bash
http://127.0.0.1:8080/api/screenshot
```

### Action

#### click

click element which text == 'camera'

```bash
http://127.0.0.1:8080/api/action/click?widgetName=camera
```

#### exist

check if element which text == 'camera' existed

```bash
http://127.0.0.1:8080/api/action/exist?widgetName=camera
```

#### system

```bash
http://127.0.0.1:8080/api/action/system?actionName=turnOnAirplaneMode
```

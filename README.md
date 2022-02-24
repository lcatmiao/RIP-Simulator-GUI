# RIP-Simulator-GUI

一个简单的RIP协议图形化界面模拟器

## 界面

<img src="https://img-host-lcatmiao.oss-cn-hangzhou.aliyuncs.com/202202241137312.jpg" style="zoom: 50%;" />

## 说明

网络拓扑图是写死的，程序以界面中出现的网络拓扑图为情景，模拟RIP协议的执行。

双击快捷方式RIP-Simulator-GUI即可打开程序。

点击Start按钮后，各路由表开始交换更新，等待一定的时间后，各路由表趋于稳定，各个路由器就得到了到达任何一个网络的最短距离和下一跳路由器的地址。

## 距离向量算法

![](https://img-host-lcatmiao.oss-cn-hangzhou.aliyuncs.com/202202241135782.png)

## Bug

若在点击Start按钮后不点击Stop，而是直接关闭程序窗口，则程序仍在后台运行，需打开任务管理器结束进程。

所以在点击Start按钮后，要再点击Stop按钮，而后可以正常关闭程序窗口。

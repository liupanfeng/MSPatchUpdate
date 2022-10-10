###实现增量更新测试流程：
##### 5.1.先生成一个apk文件，取名字为old.apk

##### 5.2.修改文案生成一个新的apk，取名字为new.apk

##### 5.3.在本地执行命令生成patch.apk 补丁包

##### 5.4.在手机上部署old.apk，并将补丁包手动放置到手机：

```shell
/sdcard/Android/data/com.meishe.patchupdate/files/apk/patch.apk
```

##### 5.5.点击按钮即可触发补丁合并操作，并进行安装操作


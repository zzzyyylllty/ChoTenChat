# ChoTenChat

<div align="center">

[ENGLISH README HERE](README-EN.md)

ChoTenChat 是一个在 Minecraft Bukkit 平台下运行的聊天插件。

强大的 Minecraft 聊天插件，可在 Taboolib 的架构下的多服务器中创建群和好友。

我们的目标是在MC中实现与国内最大社交软件近乎相同的功能。

基于 <a href = "https://tabooproject.org">Taboolib 6.x</a> 和 Kotlin.

目前我们仅支持 Bukkit (可以Redis跨服)，但在本体插件完工后 Bungee 和 Velocity 的支持在计划。

**本项目仍然在施工中，请耐心等待或提交 PR**

这个项目的名字来源于：
<div style="text-align: center;">游戏 <a href = "https://needystreameroverload.wiki.gg/wiki/">主播女孩重度依赖</a> 中的角色 超天酱(ChoTen Chan)。</div>
</div>


## 服务

ChoTenChat 是免费的，但我们不提供开发版本的构建文件。

## TODO
- [ ] 发送消息
- [ ] 群聊活跃头衔
- [ ] 群聊特殊头衔
- [ ] 创建群聊
- [ ] 回复消息
- [x] 占位符替换
- [ ] 群聊升级
- [x] API 命令
- [x] Debug 命令
- [ ] 加入群聊
- [ ] 添加好友
- [ ] 权限管理
- [ ] ChatImage 消息
- [ ] AllMusic/ZMusic 音乐卡片分享
- [x] MySQL 数据存储
- [ ] 举报功能
- [x] Kether:执行
- [x] Kether:发送Mini Message

## 不会支持的功能

* 现实金钱相关
* 某国内最大社交软件与MC进行转发

> _不支持某国内最大软件转发只是因为我被恶心坏了，喜欢瞎封号。
封号完还需要手持身份证扫脸填写身份信息签保证书然后人工复核还不一定解，
某公司就这么喜欢收集你( )隐私信息那我就不用了。
你( )充给你的SVIP10和大会7就当是( )( )了，我去买5000块钱的( )喂( )都比这强，
打电话给客服违规属实不予解封一哭二闹三上吊之后要我提交户口本和身份证照片才给解封，
( )( )( )( )( )( )的软件。
( )( )东西还在那里说我违规属实我违规你( )了，( )( )( )我真的要( )( )你的( )。_

Discord转发在计划支持。

Telegram转发 **_可能_** 支持但不能保证。

## 友情链接
- [TabooLib 非官方文档](https://taboolib.feishu.cn/)
- [Adyeshach](https://github.com/TabooLib/adyeshach)
- [Mirai](https://github.com/mamoe/mirai/blob/dev/README.md)
- [精神支柱](https://needystreameroverload.wiki.gg/wiki/Ame-chan)

## 构建

当前插件尚未完工，请勿构建使用。

**Windows 平台**
```shell
gradlew.bat clean build
```

**macOS/Linux 平台**
```shell
./gradlew clean build
```

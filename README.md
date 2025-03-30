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

> _不支持某社交软件转发的原因是该平台令人作呕的关键隐私信息收集以及混乱无规则的封号系统以及不修复任何人都可以轻易的封禁其他人的帐户的漏洞，收集的关键信息包括手持身份证扫脸的照片，填写私密身份信息，甚至包括签保证书才可视情况解封，以及极差服务的人工态度，不要坚持，我们不会再次破例。_

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

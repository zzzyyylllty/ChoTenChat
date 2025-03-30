# ChoTenChat

<div align="center">

[中文 README 在这里](README.md)

ChoTenChat is a chat plugin that runs on the Minecraft Bukkit platform.

A powerful Minecraft chat plugin that can create groups and friends on multiple servers under the Taboolib architecture.

Our goal is to achieve almost the same functionality as "the largest social media app in China" in MC.

Based on<a ref="https://tabooproject.org"> Taboolib 6.x </a>and Kotlin

At present, we only support Bukkit (Redis cross server), but support for Bungee and Velocity is planned after the completion of the ontology plugin.

**This project is still under construction, please be patient or submit a PR**

The name of this project comes from：
<div style="text-align: center;">Character named ChoTen-Chan In Game <a href = "https://needystreameroverload.wiki.gg/wiki/">Needy Girl Overdose</a> </div>
</div>


## Services

ChoTenChat is free, but we do not provide build files for the development version.

## TODO
- [ ] Send message
- [ ] Temperature title
- [ ] Special title
- [ ] Create Group
- [ ] Reply Message
- [x] Placeholders
- [ ] Group Upgrade
- [x] API Command
- [x] Debug Command
- [ ] Join Group
- [ ] Add Friend
- [ ] Internal Permission
- [ ] ChatImage Message
- [ ] AllMusic/ZMusic Music-Link Card Share
- [x] MySQL
- [ ] Report 
- [x] Kether: Eval
- [x] Kether: Send Mini Message

## Will Not Support

* Real money related
* (a largest social media app in China) message forwarding with MC

> _The reason for not supporting the forwarding of a certain social media application is the platform's disgusting collection of key privacy information, chaotic and irregular account blocking system, and failure to fix vulnerabilities that allow anyone to easily block other people's accounts. The collected key information includes photos scanned with ID cards, filling in private identity information, and even signing insurance certificates to be unblocked depending on the situation, as well as the extremely poor service attitude of manual personnel. Don't insist, we will not make an exception again._

Discord message forwarding is planned to support.

Telegram message forwarding **_MIGHT_** support but I can't promise.

## Friendship Link
- [TabooLib Dev Doc](https://taboolib.feishu.cn/)
- [Adyeshach](https://github.com/TabooLib/adyeshach)
- [Mirai](https://github.com/mamoe/mirai/blob/dev/README.md)
- [The Spiritual Pillar](https://needystreameroverload.wiki.gg/wiki/Ame-chan)

## Build

The current plugin is not yet completed, please do not build and use it.

**Windows**
```shell
gradlew.bat clean build
```

**macOS/Linux**
```shell
./gradlew clean build
```

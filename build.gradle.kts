import io.izzel.taboolib.gradle.*
import io.izzel.taboolib.gradle.Basic
import io.izzel.taboolib.gradle.DatabaseAlkaidRedis
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kotlinx.*
import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    java
    id("io.izzel.taboolib") version "2.0.18"
    id("org.jetbrains.kotlin.jvm") version "1.8.22"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.22"
}
taboolib {
    env {
        // 调试模式
        debug = false
        // 是否在开发模式下强制下载依赖
        forceDownloadInDev = true
        // 中央仓库地址
        repoCentral = "https://maven.aliyun.com/repository/central"
        // 依赖下载目录
        fileLibs = "libraries"
        // 资源下载目录
        fileAssets = "assets"
        // 是否启用隔离加载器（即完全隔离模式）
        enableIsolatedClassloader = false
        // 安装模块

        // install(BUKKIT_ALL, UNIVERSAL)
        install(Basic)
        install(Bukkit)
        install(BukkitUtil)
        install(BukkitUI)
        install(BukkitHook)
        install(BukkitFakeOp)
        install(BungeeCord)
        install(MinecraftChat)
        install(MinecraftEffect)
        install(CommandHelper)
        install(I18n)
        install(Metrics)
        install(Database)
        install(DatabasePlayer)
        install(DatabasePtc)
        install(DatabasePtcObject)
        install(Kether)

    }
    version {
        taboolib = "6.2.3"
        // Kotlinx Coroutines 版本（设为 null 表示禁用）
        coroutines = "1.7.3"
    }
    relocate("kotlinx.serialization", "kotlinx.serialization160")
}

repositories {
    maven { url = uri("https://repo.pcgamingfreaks.at/repository/maven-everything") }
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        name = "adyeshach"
        url = uri("https://repo.tabooproject.org/repository/releases/")
    }
    maven {
        name = "mythicmobs"
        url = uri("https://mvn.lumine.io/repository/maven-public/")
    }

    maven {
        url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }

    maven {
        url = uri("https://mvnrepository.com/")
    }

    maven {
        name = "citizens-repo"
        url = uri("https://maven.citizensnpcs.co/repo")
    }
    mavenCentral()
    //mavenLocal()
    maven { url = uri("https://jitpack.io") }
    maven("https://mvnrepository.com/artifact/")
    maven("https://mvnrepository.com/artifact/net.byteflux/libby-bukkit")
    maven(url = "https://mvn.lumine.io/repository/maven-public/")
}
dependencies {
    compileOnly("net.luckperms:api:5.4")
    implementation("me.clip:placeholderapi:2.11.5")
    compileOnly("io.lumine:Mythic-Dist:5.6.1")
    implementation("net.kyori:adventure-text-serializer-legacy:4.19.0")
    compileOnly("com.beust:klaxon:5.5")
    implementation("net.kyori:adventure-api:4.19.0")
    compileOnly("ink.ptms.adyeshach:api:2.0.24")
    compileOnly("ink.ptms.core:v12004:12004:mapped")
    compileOnly("ink.ptms.core:v12004:12004:universal")
    compileOnly(kotlin("stdlib"))
    compileOnly(fileTree("libs"))
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT") { isTransitive = false }
    implementation("net.kyori:adventure-text-minimessage:4.19.0")
    testImplementation(kotlin("test"))
    taboo("org.jetbrains.kotlinx:kotlinx-serialization-core-jvm:1.6.0") { isTransitive = false }
    taboo("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.6.0") { isTransitive = false }


}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
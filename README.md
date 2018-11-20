# tensquare
this is tensquare 
一个类似csdn的东西

# nexus3的坑
搭建好nexus3后，配置了~/.m2/settings.xml文件，和项目中的pom.xml文件。如下

- settings.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<settings>
    <servers>
        <!-- 这是公司的maven nexus 用户名密码 -->
        <server>
            <id>souban-nexus</id>
            <username>打码处理</username>
            <password>打码处理</password>
        </server>
        <!-- 这是我自己刚搭建的nexus仓库 用户密码 -->
        <server>
            <id>wooo-nexus</id> <!-- 这里的id和项目中pom配置的的id应该是一样的 -->
            <username>打码处理</username>
            <password>打码处理</password>
        </server>
    </servers>
    <mirrors>
         <mirror>
            <!-- aliyun 加速器 -->
            <id>aliyun-repository-public</id>
            <name>Maven Repository Manager running on aliyun</name>
            <url>http://maven.aliyun.com/nexus/content/groups/public</url>
            <mirrorOf>central</mirrorOf>
        </mirror>
        <mirror>
            <!-- aliyun 加速器 -->
            <id>aliyun-repository-central</id>
            <name>Maven Repository Manager running on aliyun</name>
            <url>http://maven.aliyun.com/nexus/content/repositories/central</url>
            <mirrorOf>central</mirrorOf>
        </mirror>
        <mirror>
            <id>wooo-nexus</id>
            <name>snapshots repository</name>
            <mirrorOf>*</mirrorOf>
            <url>http://打码处理/repository/snapshots/</url>
        </mirror>
        <mirror>
            <id>wooo-nexus</id>
            <name>release repository</name>
            <url>http://打码处理/repository/release</url>
            <mirrorOf>*</mirrorOf>
        </mirror>
    </mirrors>
</settings>

```
- pom.xml
 这个pom文件是需要做打包上传的项目 tensquare_common
 
 ````xml
 ....
    <!-- 从哪里去拉jar包 -->
    <repositories>
        <repository>
            <id>wooo-nexus-snapshots</id>
            <name>release repository</name>
            <url>http://打码处理/repository/release/</url>
        </repository>
        <repository>
            <id>wooo-nexus-release</id>
            <name>snapshots repository</name>
            <url>http://打码处理/repository/snapshots/</url>
        </repository>
    </repositories>
    <!-- 需要打包上传到的目的地 -->
    <distributionManagement>
        <repository>
            <id>wooo-nexus</id>
            <name>release</name>
            <url>http://打码处理/repository/release/</url>
        </repository>
        <snapshotRepository>
            <id>wooo-nexus</id>
            <name>snapshots</name>
            <url>http://打码处理/repository/snapshots/</url>
        </snapshotRepository>
    </distributionManagement>
 ....
````
新建了两个format为maven(group)的仓库,如图:
![image](https://note.youdao.com/yws/api/personal/file/WEBa721009169578d8599f4807644376742?method=download&amp;shareKey=bdd9ce9e0571a462c675624df64c43af)

随后我在pom文件中做了如下配置

````xml
    <repositories>
        <repository>
            <id>wooo-nexus-snapshots</id>
            <name>release repository</name>
            <url>http://118.24.114.73:8081/repository/release/</url>
        </repository>
        <repository>
            <id>wooo-nexus-release</id>
            <name>snapshots repository</name>
            <url>http://118.24.114.73:8081/repository/snapshots/</url>
        </repository>
    </repositories>

    <distributionManagement>
        <repository>
            <id>wooo-nexus</id>
            <name>release</name>
            <url>http://手动打码/repository/release/</url> <!-- 更改了这一行-->
        </repository>
        <snapshotRepository>
            <id>wooo-nexus</id>
            <name>snapshots</name>
            <url>http://手动打码/repository/snapshots/</url>  <!-- 还有这一行-->
        </snapshotRepository>
    </distributionManagement>
````
然后再使用mvn deploy命令时, 报了如下的错:
````
Failed to execute goal org.apache.maven.plugins:maven-deploy-plugin:2.8.2:deploy (default-deploy) on project tensquare_common: Failed to deploy artifacts: Could not transfer artifact io.wooo:tensquare_common:jar:0.0.4-20181116.063410-1 from/to wooo-nexus (http://手动打码/repository/snapshots/): Failed to transfer file: http://118.24.114.73:8081/repository/snapshots/io/wooo/tensquare_common/0.0.4-SNAPSHOT/tensquare_common-0.0.4-20181116.063410-1.jar. Return code is: 405, ReasonPhrase: PUT. -> [Help 1]
````
解决过程太复杂，不过赘述。直接说解决方案吧。我把配置中的url的```/repository/snapshots```换为了```/repository/maven-snapshots```，maven-snapshots如图： 这个是nexus自带的仓库还有一个maven-release也是。
![image](https://note.youdao.com/yws/api/personal/file/WEB76e82e4c2a9512d1f4ca203814a51eca?method=download&amp;shareKey=9bac48b2b766423b5872f0e973643cf8)
按照网上的理解是，你建了一个group的仓库选择了maven-shapshots会指向该仓库，于是我就新建了上上个截图的两个仓库，然后在配置文件中配好就死活报错。
然后把配置改为了这样以后：
```xml
    <repositories>
        <repository>
            <id>wooo-nexus-snapshots</id>
            <name>release repository</name>
            <url>http://手动打码/repository/release/</url>
        </repository>
        <repository>
            <id>wooo-nexus-release</id>
            <name>snapshots repository</name>
            <url>http://手动打码/repository/snapshots/</url>
        </repository>
    </repositories>

    <distributionManagement>
        <repository>
            <id>wooo-nexus</id>
            <name>release</name>
            <url>http://手动打码/repository/maven-release/</url> <!-- 更改了这一行-->
        </repository>
        <snapshotRepository>
            <id>wooo-nexus</id>
            <name>snapshots</name>
            <url>http://手动打码/repository/maven-snapshots/</url>  <!-- 还有这一行-->
        </snapshotRepository>
    </distributionManagement>
```
就没有报错了，于是我知道问题所在了。 然后我删了自带的maven-snapshots和maven-release，自建了下面两个仓库:
![image](https://note.youdao.com/yws/api/personal/file/WEB0e53b3e2756fa7232487ee013608902b?method=download&amp;shareKey=6ebccba948ae58930a57a1a87afde601)
配置文件如下:
```xml
    <repositories>
        <repository>
            <id>wooo-nexus-snapshots</id>
            <name>release repository</name>
            <url>http://手动打码/repository/release/</url>
        </repository>
        <repository>
            <id>wooo-nexus-release</id>
            <name>snapshots repository</name>
            <url>http://手动打码/repository/snapshots/</url>
        </repository>
    </repositories>

    <distributionManagement>
        <repository>
            <id>wooo-nexus</id>
            <name>release</name>
            <url>http://手动打码/repository/release/</url> <!-- 更改了这一行-->
        </repository>
        <snapshotRepository>
            <id>wooo-nexus</id>
            <name>snapshots</name>
            <url>http://手动打码/repository/snapshots/</url>  <!-- 还有这一行-->
        </snapshotRepository>
    </distributionManagement>
```
nexus配置如下，两个仓库配置是一样的。
![image](https://note.youdao.com/yws/api/personal/file/WEBe1d99f271b24dd946a7ed31fa16383e5?method=download&amp;shareKey=b66fe8a5fce972be08bd475e837a890d)
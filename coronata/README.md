# coronata
A cross-platform and easy to use Java Wiimote library.

## License
[GPL v3](http://www.gnu.org/licenses/gpl-3.0.en.html)

## Usage
For your convenience, please use [Maven](https://maven.apache.org) or [Gradle](http://gradle.org/). There is a free service called [JitPack]( https://jitpack.io/) that allows adding a GitHub project as a dependency. It is able to download released JARs from GitHub repositories and, for non-released versions, it is able to download source code, compile it and package it as a JAR.

To use coronata on a Maven project, add this to your pom.xml:

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.github.awvalenti.bauhinia</groupId>
    <artifactId>coronata-lib</artifactId>

    <!--
      Here goes either a released version, a commit code or the
      string "-SNAPSHOT" (without the quotes, with the hyphen).
      The example below uses a commit code.
    -->
    <version>a4b00354a56aa</version>
  </dependency>
</dependencies>
```

### API
I strongly encourage you to take a look at [nitida](https://github.com/awvalenti/bauhinia/tree/master/nitida). It is real code and is guaranteed to be up-to-date. The following example might not be. But, for you to have an idea, coronata looks something like this:
```java
Coronata.guidedBuilder()
    .asynchronous()   // use either this one (for GUI applications)
//  .synchronous()    // ...or this one (for console applications)
    .oneWiimote()
    .wiimoteConnectionObserver(this)
    .buttonObserver(this)
    .build()
    .run();
```
Replace ```this``` with the object(s) you wish to observe the events. The interfaces to be implemented should make sense straightforwardly.

There are many types of observers that you can use. Each one is optimized for a particular use. Just examine the methods from the builder and you will find them.

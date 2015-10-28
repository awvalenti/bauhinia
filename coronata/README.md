# coronata
A Java Wiimote library that is cross-platform and easy to use.

## License
[GPL v3](http://www.gnu.org/licenses/gpl-3.0.en.html)

## Usage
For your convenience, please use [Maven](https://maven.apache.org) or [Gradle](http://gradle.org/). There is a free service called [JitPack]( https://jitpack.io/) that allows adding a GitHub project as a dependency. It is able to download a released JAR from a repository. For non-released versions, it is also able to download the code, compile it and package a JAR.

For Maven, to use coronata in your project, add this to your pom.xml:

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
    <version>ea17d78a24de</version>
  </dependency>
<dependencies>
```

### API
I strongly encourage you to take a look at [nitida](https://github.com/awvalenti/bauhinia/tree/master/nitida). It is real code and is guaranteed to be up-to-date. The following example might not be. But, for you to have an idea, coronata looks something like this:
```java
Coronata.guidedBuilder()
    .asynchronous()   // use this one for GUI applications
//  .synchronous()    // ...or this one for console applications
    .oneWiimote()
    .wiimoteConnectionObserver(this)
    .buttonObserver(this)
    .build()
    .run();
```
Replace ```this``` with the object(s) you wish to observe the events. The interfaces to be implemented should make sense straightforwardly.

There are many types of observers that you can use. Each one is optimized for a particular use. Just examine the methods from the builder and you will find them.

# coronata
A cross-platform and easy to use Java Wiimote library.

## License
[GPL v3](http://www.gnu.org/licenses/gpl-3.0.en.html)

```
coronata - A cross-platform and easy to use Java Wiimote library.
Copyright (C) 2015  Andre Willik Valenti

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
```

## Installation
You can either:
- download a [released JAR](../../../releases) and add it to the build path of your project (external dependencies included)
- use a dependency management tool like [Maven](https://maven.apache.org) or [Gradle](http://gradle.org/)

For the second option, there is a free service called [JitPack](https://jitpack.io/) that allows adding a project hosted on GitHub as a dependency. It downloads source code, builds JARs and serves them to you.

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
      Here goes either a git tag correponding to a released version,
      a commit hash or the string "-SNAPSHOT" (without the quotes,
      with the hyphen). The example below uses a git tag.
    -->
    <version>v0.1.0</version>
  </dependency>
</dependencies>
```

Maven plugin for Eclipse sometimes is unable to download JAR files properly from JitPack. If that happens, please try running ```mvn install``` from the command line and then updating Maven project on Eclipse (```right-click on the project > Maven > Update Project...```).

### API
To learn how to use coronata, I strongly encourage you to take a look at [nitida](https://github.com/awvalenti/bauhinia/tree/master/nitida). You will find real and up-to-date code. For you to have an idea, coronata looks something like this:
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

## Dependencies
- [awvalenti/wiiusej](https://github.com/awvalenti/wiiusej)
- [BlueCove](http://bluecove.org/)
- [BlueCove-gpl](http://bluecove.org/bluecove-gpl/)

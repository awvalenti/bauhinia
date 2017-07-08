# coronata
A cross-platform and easy to use Java Wii Remote library.

coronata aims to be a high-usability library:
* Clean API
* Selective event handling (subscribe only to events that interest you)
* No manuals, yes
[demos](coronata-demos/src/main/java/com/github/awvalenti/bauhinia/coronata)
(so learning is quickly and always up-to-date with latest coronata version)
* Evolve together with applications (driven by real usage as much as possible)

## License
[GPL v3](http://www.gnu.org/licenses/gpl-3.0.en.html)

```
coronata - A cross-platform and easy to use Java Wiimote library.
Copyright (C) 2015 - 2017  Andre Willik Valenti

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
- download a [released JAR](../../../releases) and add it to the build path of
  your project (coronata dependencies are already included)
- use a dependency management tool like [Maven](https://maven.apache.org)
  or [Gradle](http://gradle.org/)

For the second option, there is a free service called
[JitPack](https://jitpack.io/) that allows adding a project hosted on GitHub
as a dependency. It downloads source code, builds JARs and serves them to you.

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
    <version>v0.1.0</version>
  </dependency>
</dependencies>
```

On ```<version>```, put either a git tag correponding to a released version
(which is the case above) or a commit hash.

If you use Eclipse, sometimes the Maven plugin is unable to download JAR files
properly from JitPack. If that happens, please try running
```mvn install``` from the command line and then updating Maven project on
Eclipse (```right-click on the project > Maven > Update Project...```).

### Usage
To learn how to use coronata, take a look at source code from
[```coronata-demos```](coronata-demos/src/main/java/com/github/awvalenti/bauhinia/coronata)
module. A quick sample is
[CoronataDemo1](coronata-demos/src/main/java/com/github/awvalenti/bauhinia/coronata/demo1/CoronataDemo1.java).
For a more thorough example, please refer to the [nitida project](../nitida).

## Dependencies
- [awvalenti/wiiusej](https://github.com/awvalenti/wiiusej)
- [BlueCove](http://bluecove.org/)
- [BlueCove-gpl](http://bluecove.org/bluecove-gpl/)

## Development
You are welcome to submit suggestions and bugs! Please
[open an issue](../../../issues) if you have one.

I'd be especially grateful for Mac users that take their time to test coronata
on Mac systems and open issues reporting the result!
 
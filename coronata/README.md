# coronata – usable, cross-platform Wii Remote library for Java
* Clean code
* Clean API, based on [builder pattern](https://en.wikipedia.org/wiki/Builder_pattern)
* Selective event handling: subscribe only to events that interest you
* No manuals, yes
[demos](coronata-demos/src/main/java/com/github/awvalenti/bauhinia/coronata)
(quick learning and up-to-date with current API)
* Evolve together with applications: driven by real usage

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

## Currently supported Wii Remote features
- Buttons
- Vibration
- LEDs

## Installation
You can either:
- download a [released JAR](../../../releases) and add it to the build path of
  your project
- use a dependency management tool like [Maven](https://maven.apache.org)
  or [Gradle](http://gradle.org/)

For the Maven/Gradle option, you can use a free service called
[JitPack](https://jitpack.io/). It allows adding a project hosted on GitHub
as a dependency. It then downloads source code, builds binaries and serves them.
To use JitPack, add the following to your pom.xml. Inside ```<version>```
element, put a released coronata version.

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
    <version>0.2.x</version>
  </dependency>
</dependencies>
```

If errors occur when downloading dependencies, please do the following:
1. Erase partially downloaded files from your repository. Delete one of the
  directories below:
  - On Linux: ```~/.m2/repository/com/github/awvalenti```
  - On Windows: ```%HOMEPATH%\.m2\repository\com\github\awvalenti```
2. Rebuild your project, either via Maven (e.g., ```mvn clean compile```) or
   IDE (on Eclipse: Project > Clean > Clean all projects > OK).

### Usage
To learn how to use coronata, take a look at source code from module
[```coronata-demos```](coronata-demos/src/main/java/com/github/awvalenti/bauhinia/coronata).
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

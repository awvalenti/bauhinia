# nitida
<img alt="Screenshot of nitida window" src="doc/nitida-screenshot.gif" width="320" height="520">

A portable remote controller application, built on top of
[coronata](../coronata).

It currently works as a remote presenter.
Future use cases include game controller and media player controller.

## Highlights
- Small, portable app (single JAR file with less than 1MB)
- Cross-platform (tested on Windows and Linux)
- Easy to use (informative user interface)
- Few requirements:
  - Java Runtime Environment 1.6+
  - On Windows, pairing Bluetooth device
  - On Linux, installing a system package once

## How to use
1. Configure your computer to run nitida. The procedure varies according to your
   operating system:
    - Windows:
      - Pair your Wii Remote:
        1. Double-click the Bluetooth icon on the
           lower-right corner of the screen or via Control Panel.
        1. Look for Bluetooth devices already added. If the Wii Remote is there,
        remove it.
        1. Select the option to add a new device. Make the Wii Remote visible
           by pressing buttons 1 + 2 simultaneously.
        1. Add it as a paired device without using a code.         
        1. For more information, please check
           [this tutorial](http://www.dolphin-emulator.com/connect-wiimote.html)
           and/or [this video](https://www.youtube.com/watch?v=DIFARukwA5I).
           Skip the parts dealing with the Dolphin emulator.
    - Linux:
      - Install the appropriate system package as described on the
        Requirements section of [this page](http://bluecove.org/bluecove-gpl/).
        On Ubuntu or derivatives,
        this is accomplished by simply running ```sudo apt-get install libbluetooth-dev```.
    - Mac:
      - I don't have a Mac to test nitida, so I gladly accept your help to develop it!
        Try running the application and
        [open an issue](https://github.com/awvalenti/bauhinia/issues/new?title=nitida%20on%20Mac)
          reporting your experience.
1. From the [releases page](https://github.com/awvalenti/bauhinia/releases),
  download the latest nitida JAR file.
1. To run nitida...
   - ...on window mode, double-click the downloaded JAR file. A window should open. If it doesn't,
     try right-clicking the file and selecting ```Open With > Java Runtime```
     or similar. If it still doesn't work, try via terminal:
     ```java -jar nitida-x.y.z.jar```, where ```x.y.z``` is the app version.
   - ...on console mode, run on a terminal: ```java -jar nitida-x.y.z.jar --console```,
     where ```x.y.z``` is the app version.
1. A connection attempt should start automatically. On Linux, press buttons 1 + 2
  simultaneously to make the Wii Remote discoverable. On Windows, it should
  already be discoverable if previously paired as explained above.

## Development
You are welcome to submit suggestions and bugs! Please [open an issue](../../../issues)
if you have one.

## License
[GPL v3](http://www.gnu.org/licenses/gpl-3.0.en.html)

```
nitida - Portable Wii Remote controller application.
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

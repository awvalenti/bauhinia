# nitida
A portable remote controller application, built on top of
[coronata](../coronata).

![Screenshot of nitida window](doc/nitida-screenshot.png)

## License
[GPL v3](http://www.gnu.org/licenses/gpl-3.0.en.html)

## Possible use cases
- Remote presenter
- Game controller
- Media player controller
- ...

## Highlights
- Small, portable app (single JAR with less than 1MB)
- Cross-platform (tested on Windows and Linux)
- Easy to use (informative user interface)
- Few requirements:
  - Java Runtime Environment 1.6+
  - On Windows, pairing Bluetooth device
  - On Linux, installing a system package once (```libbluetooth-dev``` on
    Ubuntu or derivatives - details [here](http://bluecove.org/bluecove-gpl/))

## How to use it
On Windows, first step is to pair the Wii Remote. Open the Bluetooth
configurations. Make the Wii Remote visible
by pressing buttons 1 + 2 simultaneously. Add it as a paired device without
using a code.
If the device is already added, remove it and add it again.
For more information, please check
[this tutorial](http://www.dolphin-emulator.com/connect-wiimote.html)
and/or [this video](https://www.youtube.com/watch?v=DIFARukwA5I).

Download the JAR file and run it. A window should open and a connection
attempt will be started. If you're using Linux, press buttons 1 + 2
simultaneously to make the Wii Remote discoverable. On Windows, it is
discoverable if previously paired as explained above.

You can also run nitida as a console application: ```java -jar nitida-x.y.z.jar --console```

## WIP
I'm working mostly on improving the application usability. Suggestions are welcome!

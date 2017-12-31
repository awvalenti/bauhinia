# nitida usage
nitida has been tested on Windows and Linux Mint. I don't have a
Mac. If you do, you are welcome to help! Try running the application and
[report your experience](https://github.com/awvalenti/bauhinia/issues/85).

## Instructions

1. **Install prerequisites.** The procedure varies according to your
  operating system:
    - Windows:
      - Make sure you have Java Runtime Environment (JRE) version 1.6+
        installed. You can check that by opening a Command Prompt and
        running: ```java -version```. If you see something
        like ```java version "1.8.0_152"```, it means JRE is properly
        installed and configured.

      - If JRE is not installed, get it from [this website](https://java.com/).
        Version 9 has presented problems loading native libraries. Please try
        version 8 instead.

    - Linux:
      - Make sure you have Java Runtime Environment (JRE) version 1.6+
        installed. You can check that by opening a terminal and
        running: ```java -version```. If you see something
        like ```java version "1.8.0_152"```, it means JRE is properly
        installed and configured.

      - If JRE is not installed, install it from your package manager.
        On Ubuntu or derivatives, just open a terminal,
        run ```apt install openjdk-8-jre``` and follow the instructions.
        For other distributions of Linux, please check
        [this website](http://openjdk.java.net/install/).

      - Install the appropriate system package as described on the
        *Requirements* section of [this page](http://bluecove.org/bluecove-gpl/).
        On Ubuntu or derivatives, just open a terminal,
        run ```apt install libbluetooth-dev``` and follow the instructions.

2. **Download the executable.** From the
  [releases page](https://github.com/awvalenti/bauhinia/releases),
  download the latest nitida JAR file, if you haven't already.

3. **(Windows only) Pair Bluetooth device.** Pair the Wii Remote to the
  computer. If it has been previously paired, remove it from the list of paired
  Bluetooth devices then pair it again.

    [This tutorial](http://www.dolphin-emulator.com/connect-wiimote.html) explains
    how to do it on Windows 7.

    These videos show how to do it on various versions:
      - [Windows 10 Creators Edition](https://www.youtube.com/watch?v=d-DKK9RUsIE)
      - [Windows 8, 8.1 or older editions of 10](https://www.youtube.com/watch?v=DIFARukwA5I)
      - [Windows 7](https://www.youtube.com/watch?v=IBo2mNL24Zg)

4. **Run the JAR file.**

    - On console mode:
      - Run on a terminal: ```java -jar nitida-x.y.z.jar --console```
        (replace x.y.z by version number, e.g., 0.2.x).

    - On window mode (default):
      - Double-click the JAR file and it should work. If not, try right-clicking it
        and selecting ```Open With > Java Runtime``` or similar. On Linux, you may need to
        add execution permission to the file (```chmod +x nitida-x.y.z.jar```).

      - If none of the above worked, try opening a terminal/command prompt and
        executing: ```java -jar nitida-x.y.z.jar``` (replace x.y.z by version
        number, e.g., 0.2.x).

5. **(Linux only) Make Wii Remote discoverable.**
  Press red sync button under back lid of Wii Remote.
  Connection should happen automatically; if it doesn't,
  try clicking the ```Retry``` button.

6. **Choose presenter application.** From the drop-down menu, choose your
  presenter application (PowerPoint, Google Slides etc.). This will
  define the mappings between Wii Remote buttons and keyboard keys.

7. **Try it.** Go to your presentation application and
  try pressing Wii Remote buttons. You should be able to control
  your presentation as described on the section below.

## Standard actions
* __Directional__: Arrow keys
* __A__: Next slide/page
* __B__: Previous slide/page
* __-__: Stop presentation (on Google Slides, must be pressed twice)
* __Home__: Start presentation at first slide/page
* __+__: Start presentation at current slide/page
* __1__: Black out screen
* __2__: ALT + TAB (may not work on older Windows versions)

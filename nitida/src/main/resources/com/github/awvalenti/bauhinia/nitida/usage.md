# nitida usage

## Overview steps:
1. Download nitida JAR
2. Configure Bluetooth
3. Run JAR
4. Connect Wii Remote
5. Choose presenter application
6. Try it

## Standard actions (may vary according to application)
* __Directional__: Arrow keys (useful for text navigation)
* __A__: Next slide/page
* __B__: Previous slide/page
* __-__: Stop presentation
* __Home__: Start presentation at first slide
* __+__: Start presentation at current slide
* __1__: Black out screen
* __2__: ALT + TAB (may not work on Windows 8 - see
         https://stackoverflow.com/questions/14549526/alttab-using-java-robot)

## Known limitations
* Due to Google Slides limitations:
  * Start and stop presentation functions only work well on Chrome
  * Stop presentation button must be pressed twice in order to
    start presentation function work again
* Some applications only allow going fullscreen via mouse

## Detailed instructions
1. **Download nitida JAR.** From the
   [releases page](https://github.com/awvalenti/bauhinia/releases),
   download the latest nitida JAR file.

2. **Configure Bluetooth.** The procedure varies according to your
   operating system:
    - Windows:
      - Pair your Wii Remote:
        1. Double-click the Bluetooth icon on the
           lower-right corner of the screen or via Control Panel.
        2. Look for Bluetooth devices already added. If the Wii Remote is there,
        remove it.
        3. Select the option to add a new device. Make the Wii Remote visible
           by pressing buttons 1 + 2 simultaneously.
        4. Add it as a paired device without using a code.         
        5. For more information, please check
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

3. **Run JAR.** (replace x.y.z by version number, e.g., 0.2.x)
   - ...on console mode:
      - Run on a terminal: ```java -jar nitida-x.y.z.jar --console```.
   - ...on window mode (default):
     - Double-clicking the JAR file may be enough. On Linux, you might need to
       add execution permission to the file (```chmod +x nitida-x.y.z.jar```)
       and/or right-clicking the file and selecting
       ```Open With > Java Runtime``` or similar.
     - If none of the above worked, try opening a terminal/command prompt and
       executing:
       ```java -jar nitida-x.y.z.jar```.

4. **Connect Wii Remote.** On Linux, press buttons 1 + 2 simultaneously to make
   the Wii Remote discoverable. On Windows, Wii Remote should already be paired
   as explained above. Connection should happen automatically; if it doesn't,
   try clicking the ```Retry``` button.

5. **Choose presenter application.** From the drop-down menu, choose your
   presenter application (PowerPoint, Google Slides etc.). This will
   define button-key mappings.

6. **Try it.** Go to your presentation application and
   try pressing Wii Remote buttons. You should be able to control
   your presentation.

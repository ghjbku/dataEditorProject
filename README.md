# dataEditorProject

project to help with modifying save data in Xiuzhen idle (https://store.steampowered.com/app/1649730/xiuzhen_idle/)

runnable jar file can be found in the **target** folder, or you can compile it yourself if you feel like it :)
you will need to open the **"run editor.bat"** file (or use the command: **java -jar saveDataEditor-versionNumber-SNAPSHOT.jar**)

if you dont want to use the bat file, and it does not autolaunch the software when you doubleclick the jar file, follow these steps:
- press the key combination: win+R
- write "regedit" and press enter
- look for this path HKEY_CLASSES_ROOT\jarfile\shell\open\command (it should be the very first folder)
- right click on default and click modify
  ![image](https://github.com/ghjbku/dataEditorProject/assets/22707282/268ec3dd-2e51-4c27-a613-003e265df2be)
- modify the path so it is jre 1.8


current progress (V 1.0.5):
- users now able to use comma as decimal sign instead of dot (have to press the corresponding button in the main menu to switch)
- added initial tab for god realm
- Editing player inventory slots (adding new one is not possible yet, only by editing an existing item's id)

![demo gif of the progress](https://cdn.discordapp.com/attachments/1038509324634435657/1057292002405920778/demo1_0_3.gif)


### The project is being developed in jdk8 now
source: https://www.oracle.com/java/technologies/downloads/#java8-windows

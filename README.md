# UML to Source Code  - 
Submitted by: **Ricardo Elizondo, Alexandria Knox, Ben Underdahl**

## Project Description:
    
This project consists of a program that converts UML code into source code of the choosen language. User can either upload a previously made .txt file or start from stratch within the actualy program itself. The program serves as a tool to help save time on coverting UML into source code that can be both read and save within a computer file.

The program consists of two windows, one for the UML code and the other for the converted code result. The window on the left serves as the input for UML code and can be interacted with by the user to change any attributes or details of the code. The window on the right serves as an output window that displays the converted code for the user to see.

Under the left window will be the file directory to where where the uploaded code is located.

On the bottom, there are four buttons for the user to interact with. All are straight forward in their functionality and serve as the main functions to the program.
   
   
   Above these four buttons are 2 radio buttons that have the names "Java" and "Python". When either or are selected, the coverented UML code with be written in the chosen language. Presently, the only language available for this program will be Java.
   


---


## Required Features


The following **required** functionality is completed:

- [x] **Graphic User Interface**
- [x] **User can phyically interact and turn UML code into Java code** 
- [x] **Class, Attributes, Methods, Comments and Access Modifiers are allowed.**
- [x] **Executable File (UmlParser.exe)**
- [x] **User can save the convereted file into any file location they choose**
- [x] **User can upload a prewritten text(.txt) file to the program to save time on input** 

 
The following **optional** features are yet to be implemented:

- [ ] **Constructor and ArrayList Support**
- [ ] **Python Support**

## User Manual

1. Run Executable
2. Load UmlFile or input Text Manually:
Example UML Snippet (spacing or lack of must be followed)): 

```
roundCircle < This line will be ignored
@startuml
class TestClass {
''CommentExample
#attribute:int
+method(param1:float,param2:String):void
}

class anotherClass {

}


@enduml
```

3. Click the "Load" button if Load option was chosen.
4. Click the "Generate" button. 
5. Once clicked, the program with instanstly generate the Selected programming language code
6. When code is generated, click the "Save" button 


# UML to Source Code  - 
Submitted by: **Ricardo Elizondo, Alexandria Knox, Ben Underdahl**

Project Description: NEEDS TO BE FILLED

Time spent: **3** hours spent in total

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

class anotherClass


@enduml
```

3. Click the "Load" button if Load option was chosen.
4. Click the "Generate" button. 
5. Once clicked, the program with instanstly generate the Selected programming language code
6. When code is generated, click the "Save" button 



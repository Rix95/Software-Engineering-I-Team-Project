from tkinter import *
from tkinter import filedialog
#from tkinter.ttk import *

# open the file and display the path
def openFile():
    filename = filedialog.askopenfilename()
    if filename:
        path.set("File Path:  " + filename)

# Close the GUI 
def exitGUI():
    root.destroy()

# set option to dropdown value
def select(value):
    selected_option.set(value)
    print(value)

root = Tk()


root.geometry("600x200")
root.title("UML to Source Code")
# button = Button(root,text="button", command=openFile).pack(pady= 50)
path = StringVar(root, "File Path: ")
path_label = Label(root, textvariable=path)
button = Button(root,text="Browse", command=openFile)
path_label.grid(row=0,column=0)#row=0,column=0,padx=10,pady=10
button.grid(row=1,column=0,padx=10,pady=10)

# enter and exit buttons
enter = Button(root, text="Enter").grid(row=3,column=0,sticky=E, padx=10, pady=10)
close = Button(root, text="Exit", command=quit).grid(row=3,column=1, padx=10, pady=10)


# dropdown menu with list
options = ["Python",
           "Java"]
selected_option = StringVar()
selected_option.set("Python")
make_selection = Label(root,text="Choose a Language:  ").grid(row=2, column=0, pady=10)
menu = OptionMenu(root,selected_option, *options, command=select)
menu.grid(row=2,column=1,padx=10 ,pady=10)

# Program main loop
root.mainloop()
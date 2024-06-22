# Development Branch
This is the primary branch where new features will be integrated and tested before reaching main. My IDE of choice is Eclipse, hence this repo's file structure.

## Launcher.java
As the name implies, this is the launching point of the program. This is the first window which opens after the application is run, and it currently contains two JSpinners which let the user choose the resolution of the render canvas (Control.java) and the degree of the fractal to be rendered.

## Control.java
After clicking "Render", a Control.java window will open up, which calls renderTriangles() from Model.java to draw a fractal in accordance with the selected options passed from Launcher.java.
Control.java currently implements MouseListener, MouseWheelListener, and MouseMotionListener. Everytime a mouse event happens, it changes values such as zoomFactor, deflection, etc. and calls renderTriangles() again but it passes these changed variables to produce the desired results.

## Model.java
Contains definitions for the recursive methods used to draw fractals, which are then called by Control.java.
Currently only supports drawing Sierpinski Triangles.

## TesterClass.java
A command-line automated testing class with it's own main method. Implemented with java.awt.Robot.

## Documentation
All of the documentation for this project can be found in this README file and the Javadoc folder,

Face's Recognizer
=====================

This is a Java based **Face Recognizing Software**, it uses `JavaCV` and, of course, `OpenCV` version `2.4.5`.
That's a coursework for my Associate degree in Informatics (Computer Science).
In that time I haven't used any source control, just sharing what I done.
I'm still translating, but it is almost done.

Dependencies
---------

To run the project you'll need:

 1. **OpenCV** 2.4.5 installed, please refer to [OpenCV Docs][1]
 2. Either **MySQL** or JavaDB (**Derby** comes with Java JDK)
 3. A **WebCam** compatible with OpenCV. Don't worry, never found one that isn't.
 4. **Java JDK** 6 or later

Setting up the environment
---------

 1. First of all you'll need to create the database and the tables. (I'll do it in the project in the future). The scripts are in the src folder. `MySQL_DB.sql` and `derby.txt`, choose the one you like.
 2. In src folder you find a `Database.properties` file, that is the configuration of the database.

Running
---------

When you finally be able to run the project you'll need to register somebody's faces.
One shot is enough, but to be accurate I'll say at least 5 shots, from the front with little rotation in each one.
The light must be controlled, that said you should take photos and recognize the faces in the same place.

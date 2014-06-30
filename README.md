Face's Recognizer	{#welcome}
=====================

This is a Java based **Face Recognizing Software**, it uses `JavaCV` and, of course, `OpenCV` version `2.4.5`.
That's a coursework for my Associate degree in Informatics (Computer Science).
It is a worker shift control, it also has a backend in JavaEE and an Android app so the boss could see who is in and all the checkin and checkout times. Using WebSockets for real time control, I'll commit those later.
In that time I haven't used any source control.
I'm still translating, but it is almost done.
Besides of facial recognition, It also has facial detection.
I have worked some time to make it accurate, and I had great results.


Dependencies
---------

To run the project you'll need:

 1. **OpenCV** 2.4.5 installed, please refer to OpenCV Docs
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
The light must be controlled, that said you should take photos and recognize the faces in the same place, with same light conditions.



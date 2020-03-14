# Grey-Team-CPC-

======== PROJECT SHOWCASE/SCREENSHOTS ========
[Screen 1 Display](readme_pics/Screen1_Display.PNG)

Screen 1 is a File selection screen for selecting what files we want to compare for Plagiarism, with Java FileChoosers as the method of selection and a code section where a user could paste code directly. The Program also has options for saving to a "Database" (for this implementation we did not implement a full database, but saved files to the user's hard drive)

[Screen 2 Display](readme_pics/Screen2_Display.PNG)

Screen 2 is a "Database" Page where we can select a file to either remove or add to the database implementation, or to paste code for adding to the database without having to compare files together.

[Results Screen Display](readme_pics/Results_Display.PNG)

The results screen will show both files as text, and display a Percentage Report along with feedback over whether the files were added to our database


======== PROJECT BACKGROUND AND CONTEXT ========

For our software development team simulation class CSCI-495, our Grey Team was asked to create a Code Plagiarism Checker, where we will take two text files and compare them to one another and return a percentage of how those two files are similar to one another. Throughout this process we had created various documents to guide our design as if we were a true development team, keeping up with deadlines assigned from our "Customer/Project Overseer" Julie Henderson. We held weekly meetings with Prof. Henderson where we would check in on our progress through Google Hangouts Video Conferencing, raising concerns and sharing developments and next steps following the SDLC development process.

======== GUI DESIGN ========

The GUI design collaborators were Fred, Chris Bell, and Chris McClure. We had the idea of a simple "notecard" design, having 2 screens that the user would "flip" between with a single button, dividing both screens into quadrants and columns to have a universal design language that would intuitively tell the user that each quadrant or column had a different purpose.

======== PLAGIARISM PERCENTAGE DESIGN ========

Chris Bell and Chris McClure designed the percentage deterimination or the CodeComparison class, where a two-stage approach was taken, the "Stage1" function acting as an "light lookover" contributing to the total percentage by just seeing how many lines and variable names were in common between the two files and if they did not reach a certain percentage it moved the files onto stage2. The stage2 function would compare the code structure such as conditionals, the order of the conditionals being the subject being checked. 

======= PROJECT RESULT =======

Our program was a resounding success with Prof. Henderson being very impressed with our documentation and final product, as we were the only group to have a proper GUI with all of our main project proposal requests being fulfilled. By the end Prof. Henderson was very satisfied with the product and her suggestions were limited to polishing improvements and tightening up our method of percentage finding, with her final statement being "I think this team demonstrated the example to follow for this class in the future".

======== SOFTWARE DEVELOPMENT TEAM & ROLES =========

Within our software development team, we had the following members with specific roles
Project Manager/Trainer/QA Tester- Joy Hamby
Technical Communicator/Trainer/QA Tester- Fred Jeffers
JavaFX GUI Developer/Git Repo Manager & Quality Assurance- Chris McClure
Backend Developer/Analyst/Technical Communicator- Chris Bell
Additional Functions Developer/Tester- Kyle Addey
Additional Functions Developer//Tester- Taylor Perry

Throughout this process we had a variety of Documents including a Project Charter derived from our Project Proposal

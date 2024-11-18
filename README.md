# README
## SYSC 4806 - Group Project

### Group members: Triton Crowley, Jason Zhang, Caleb Lui-yee, Nitin Alagu, Nick Yung

### Current State (as per Kanban):
In the current state there is a console application where you can create an account (or login), then you have the option to upload a perk, display perks, or logout of your account. Upon uploading a new perk the application will ask for the information, then use the enterd information to store the perk object in the perkMangager.  

### Plan for next sprint:
Next sprint we plan to create the JPA application and add the necessary templates and controllers for the User to add Perks to the site. Alongside this we plan to have a filtering and sorting option for perks in the perkManager, a mechanism for users search for products for which there are perks available matching their profile, and enable users to add memberships to thier account. 
### Features
#### User Profile (Jason)
- Login or Register
- Profile Page (Edit)
  - Username, password, memberships
- Account Creation (Add)
  - Add Memberships
- Non-Account holder
#### Perk Manager (Triton)
- Display the list of perks
- Can filter by products if user has an account, can sort by descending order of votes, or expiry date
- If user has no account, only be able to look at the list of perks
- Add Perk button
- Add login/register button
- Logged in users can vote/downvote
- This will be the homepage
- This will be one view, conditionally hide the filters and up/down vote option
  ##### New Perk (Nick)
  - Add value of perk, membership, and product applied to it
  - Form format
  - All users may add
  ##### Filter Perks (Nitin)
  - Displayed on sidebar if user is logged in
  - can **sort** by descending order of votes, or expiry date
  - List out all the products that apply to the membership of the users... users can select the product to filter by
  ##### Display Perk (Caleb)
  - display name
  - display up/downvote buttons
  - display restrictions
  - display number of votes
### Database Schema (Up to date):
  - h2 database


### Milestone 1:
Early prototype. Give a 10-15 minute demo during the lab on November 11th.
For this milestone we are looking to see enough functionality to get a feel for the system and how it will
work. One important use case should be operational. It should collect data from the back end, do
something with it and display the result. The display doesn't need to be fancy. There should be a GitHub
repo, integrated with CI, and the app should be up and running on Azure. Cloning the repo and running
the pom.xml should provide us with a ready-to-run JAR file.
We will also inspect the README file, the Issues, the Kanban, the code reviews, the tests, and we will
verify that everybody is participating in all aspects of the project (if that is not the case, different team
members will end up with different grades).

### UML Diagram
![SYSC4806-milestone1-UML](https://github.com/user-attachments/assets/1ac8a8d3-d445-464d-8323-07af9967146b)


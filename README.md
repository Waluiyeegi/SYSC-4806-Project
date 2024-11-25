# README
## SYSC 4806 - Group Project

### Group members: Triton Crowley, Jason Zhang, Caleb Lui-yee, Nitin Alagu, Nick Yung

### Current State (as per Kanban):
In the current state, it is a full stack web application using Svelte as the frontend language, and Spring Boot as our server logic. The app allows users to register, login and edit their profile which allows them to add memberships to their profile. All users have the option to upload a perk by filling in the appropriate fields, in which upon uploading a new perk, it will display it as a perk card in the perk manager. Users can click the card to see the perk code associated with it, and can see the upvotes/downvotes associated with the perk. The user can also vote if they desire. 

### Plan for next sprint:
- Create the filter section for logged in users to filter by memberships, products or by descending order of expiry date
- Update the up/downvote bug
- Migrate the membership and product to the actual entities instead of just a string property
- Finish the Delete Perk function

### Database Schema (Up to date):
  - h2 database
![DatabaseSchema](https://github.com/user-attachments/assets/c25a4755-1e92-4cfe-a268-a1845dbe4568)

### UML Class Diagram for Models

### UML Diagram
![SYSC4806-milestone1-UML](https://github.com/Zhangjason308/SYSC-4806-Project/blob/main/UML.png)


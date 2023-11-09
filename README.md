# MessagingSystem
Java Spring boot project in which one user can do comment to another user
In this project , I have done curd operation by adding comment, getting comments, updating comments , deleting comments.
I have also done almost 15 input request from postman.
This project is in MVC pattern in which request is come from postman tool in api's format and api's is defined in controller and after doing validation in user name it will go to the service class in which business logics are been written.
From service it will go to repository of both table ie. user and comment and run certain queries by their on according to function ie. save, delete, findById(we can customized aswell).

I have also created DTO(Data Transfer Object) class in which only required information is available which is used to show or get data from user (postman request body)

Tables- 1) User (userid,name,lastActiveDateTime)
        2) Comment(commentid, message, commentFrom, commentTo,commentDateTime,postedByUserId)

following api's-

i) (PostMapping) addComment - to add the comment to the comment table 
              It will check first for user if it is present in user table it will simply add comment in comment table otherwise it
              will add the user in user table (user can be from comment to or comment from or both)
              validation- username should not contain number or special character

ii) (GetMapping) getSentComment-  It will return the list of comments that is done by a person

iii) (GetMapping) getRecievedComment-  It will return the list of comments that is recieved by a person

iv) (DeleteMapping) deleteComment - It is used to delete the comment

v) (PutMapping) editComment - It is used to edit/update the comment

I have checked validation by throwing exception and giving proper response with message when exception occur by using exceptionHandler


              

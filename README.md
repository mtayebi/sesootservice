# sesootservice
## the goal of the project is to model an service provider site API using **_spring boot_**.

### The goal of the project is educational, so we are trying to keep it as simple as possible. This repository contains three branches, which can be explained as follows:

- Main branch: This branch contains the latest completed features that we have ensured are working properly.
- Dev-basic branch: At the beginning of the project, we used Spring Boot without Spring Security. We handled the security of the project using the HttpSession object, which already exists and is provided by the servlet. Therefore, this branch contains all the logic we have implemented without using Spring Security.
- Dev-security branch: This branch contains all the code changes and additions we made after implementing Spring Security in our code.

### after running the programm you will have this API pathes for requesting(it will create a pre-defned admin user):

- user:	/login (post)[just for dev-basic branch]	| /user/signup (post)	| /user/profile (get)	| /user/putorder (post)	| /logout (get) [just for dev-basic branch]	

- expert:	/login (post) [just for dev-basic branch]	| /expert/signup (post)	| /expert/profile (get)	| /expert/putorder (post) |	/logout (get) [just for dev-basic branch]

- admin: /login (post) [just for dev-basic branch] |	/admin/add-category (post) |	/admin/add-subcategory (post)	|	/logout (get) [just for dev-basic branch]

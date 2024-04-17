# Survey application
This android application was a Mobile Application Development course project assignment. The project was completed through 4 iterations according to project specifications.
## Description and features
1. Iteration (***spirala1*** branch) - Implemented MainActivity for displaying a grid list of surveys. Grid list element displays surveys name, status (completed, active but not completed, not yet active, and unavailable), date (of completion, start or end depending on status), belonging survey group, and progress. Implemented filtering of surveys (all surveys in application, surveys in user's survey group, completed surveys, future surveys, and old uncompleted surveys). Implemented activity for survey group enrollment. After enrolling in a survey group all surveys from that group become available to the user. All data is currently static. Written unit tests for repository classes.
2. Iteration (***spirala2*** branch) - Previous activities moved into respective fragments inside MainActivity. Implemented switching between fragments using swipe navigation. Also implemented taking a survey by clicking on a survey in grid list. Written two instrumented tests. **Run the application from this branch**.
3. Iteration (***spirala3*** branch) - All application data (surveys, survey groups, survey questions, survey attempts) is retrieved and sent to a web service. As the service is no longer available **this branch is unusable**.
4. Iteration (***master*** branch) - Implemented database for offline data access. If there is internet connection the application connects to the service (as in the previous iteration) and caches all received and sent data locally in the database. If there is no internet access the application loads data from the database. This data is intended only for preview which means that the user cannot take new surveys or enroll in survey groups. As branch spirala3 **this branch too is unusable**.
### Survey list
<img src="https://github.com/eduvnjak/rma22p18004/assets/44235447/29eb415c-213e-40f0-9dd9-6d34bbd0b259" height="600px">

### Survey filtering
<img src="https://github.com/eduvnjak/rma22p18004/assets/44235447/34424302-b369-4623-b3ab-314399f1e01c" height="600px">
<img src="https://github.com/eduvnjak/rma22p18004/assets/44235447/6a3282b5-69a0-4f36-b9d7-d16dc3a49201" height="600px">

### Survey group enrollment
<img src="https://github.com/eduvnjak/rma22p18004/assets/44235447/6577efda-5d96-4a7c-8f28-46b48c9a7203" height="600px">
<img src="https://github.com/eduvnjak/rma22p18004/assets/44235447/1742e54e-96e1-4a3b-bc4b-6d92c2cb3a27" height="600px">

### New survey added after enrollment
<img src="https://github.com/eduvnjak/rma22p18004/assets/44235447/a1b81297-ad2a-4107-a84f-99d4a0312819" height="600px">

### Taking a survey
<img src="https://github.com/eduvnjak/rma22p18004/assets/44235447/6e0cc3aa-3efa-491f-9037-1990b6f468e5" height="600px">
<img src="https://github.com/eduvnjak/rma22p18004/assets/44235447/43562f13-7849-4c8f-8921-8ca6d7dab933" height="600px">
<img src="https://github.com/eduvnjak/rma22p18004/assets/44235447/112cd7fd-9fd2-442c-9117-7f7a1ab52136" height="600px">

### Survey update in list after taking 
<img src="https://github.com/eduvnjak/rma22p18004/assets/44235447/34372bb8-1ba3-4151-89e0-1067e51ff505" height="600px">

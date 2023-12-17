# CS151-Spot Finder

**Team #6:** Jeremy Chan, Mathew Estrada, Ben Cuttriss Mallon

### Team members who worked on the proposal
- Jeremy Chan
  - Problem Statement, Description of Functionality, High-level Description
  - Class Diagram
 - Mathew Estrada
   - Problem Statement, Description of Functionality, Operations, References
   - State Diagram
- Ben Cuttriss Mallon
  - Problem Statement, Description of Functionality, High-level Description
  - Use Case Diagram
  - Sequence Diagram

### Team members who worked on the project presentation
- Jeremy Chan
  - Presentation setup and formatting
  - Introduction, "Important or Unique aspects" section, "What could have been better" section
  - Credits and presentation images
  - Class Diagram explanation
- Mathew Estrada
  - Working program and code demonstration
  - Introduction, "What could have been better" section
  - State Diagram explanation
- Ben Cuttriss Mallon
  - Introduction, "What could have been better" section
  - Use Case Diagram explanation
  - Sequence Diagram explanation

 ### Team members who worked on the project code and report
- Jeremy Chan
  - Initial project application and FXML setup
    - Created project with Spring Boot
    - Initial Project application start code and FXML (search button and text, background)
  - Hid API key from public/repository
  - "Team members who worked on __________" section of the report
  - Diagrams section of the report
  - Problem statement section of the report
- Mathew Estrada
  - Yelp Fusion API
    - made requests to API and displayed data from API to users
    - Search results functionality and FXML, Reviews functionality
  - Edge-case handling (if a user searched for an invalid/unknown city)
  - Implemented Results page, with FXML and Java code
  - Connected retrieved information to the Review page
  - CSS / FXML styling for all pages
  - Implemented No Results page,  with FXML and Java code
  - Solution / Operations / Steps to Run Code / Program Screenshots sections of the report
- Ben Cuttriss Mallon
  - Review page FXML and functionality

# Problem Statement
New food spots and locations are always a place of interest in people’s everyday lives, and they may not know any good places/restaurants/viewpoints to enjoy in their local area. Instead of endlessly searching on the internet or spam texting friends for locations, "Spot Finder" offers a convenient solution.

# Diagrams

## Diagram Descriptions

| Image Name | Diagram Type | Description | Created by |
|------------|--------------|-------------|-------------|
| [Class Diagram](./diagrams/ClassDiagram.drawio.pdf) | Class Diagram | Includes the layout of the project's classes, like Place, Review, applications, etc. | Jeremy |
| [Use Case Diagram](./diagrams/UseCaseDiagram.pdf) | Use Case Diagram | Includes a diagram of the user's actions. | Ben |
| [Sequence Diagram](./diagrams/SequenceDiagram.pdf) | Sequence Diagram | A diagram of what the user is doing on the application and how their actions are processed. | Ben |
| [State Diagram](./diagrams/stateDiagram.pdf) | State Diagram | A diagram of the different states possible in the application, along with the events to switch states. | Mathew |

# Functionality
Simply type in your city and search to find the top ten restaurants in your city. Scroll through the categories and reviews to find the spot that best suits you. 

#Operations
- Search Text Box / Button
    - The user can type anything (preferably a city) into the text box, allowing the application to retrieve their city of choice when the search button is clicked. This information is then sent to the Yelp API appropriately.

- Rating Hyperlinks
    - The user is able to click on any orange-colored hyperlinks for the businesses displayed on the Results page. These take the user to the Reviews page for the corresponding business that was clicked. Information is then retrieved once more from the Yelp API to return popular reviews, the date of those reviews, and the reviewer's name.
 
- New Search Button
    - This button is displayed on both the Results and No Results page. When pressed, the application will take the user back to the homepage to allow them to perform a new search.
 
- Return to Results Button
    - When clicked, the user is sent back to the results page. This allows them to review the businesses that were displayed before the Reviews page without having to enter the city again. 

#Solution
Our program Spot Finder is designed to be the optimum choice for individuals looking for popular places around them. Spot Finder utilizes the Yelp API along with efficient Java/FXML code to develop a web application that allows users to search for high-rating businesses around their city. With the help of the API, the application can retrieve important information to help users find the businesses, such as the business name, address, distance from the city, and rating.

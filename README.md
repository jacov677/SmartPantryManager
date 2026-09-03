                                               App name = Smart Pantry Manger. This is a android app that helps reduce your household waste with tracking your ingredients thas already at home and sugests recipies that you can cook ## NOW ## by stricktly using the ingredients that you have. No SHopping trip.

Module --> Mobile App Development 700
Institution--> Richfield Graduate Institution of Technology
Authot--> Jaco van niekerk

### --> Problem Solved by app
Food always gets thrown away due to people forgetting what ingredients they aread have at home. They eitehr dont have the ingredients half way through a recipe.
The manager works the other way arround, it checks what is currently in your pantry and shows all meals that you can compelete.


### -->
There is a core rule matching. If a recipe needs 5 ingredients and only 4 exists in the users pantry no rule will match and recipe wont be shown.
It would match small typo's and so on like 'tomatoes' and 'tomato' so that small mismatches are not causing errors or not showing anyn recipes.

### -->Features
-Pantry Management
-Add or Edit ingredients
-Suggested recipes
-Recipe detail
-Settings
-Input Validation
-Persistent Storage

### --> The Screens
--> Screen --> Purpose
--> Add  or Edit Ingredients
--> Suggested Recipes or Only made with current pantry
--> Recipe details or Ingredients
--> Setting or User Preferences

### --> Chosen Database SQLLite
Reason for choosing SQl lite
1. The sql lite decision was regarding SQlLite's ability to work in a offline state
2. It Persists --> DB file lives on the device
3. Matches run locally and instantly
4. None external accouns and configurations
5. Matches everything that the module requires

### --> Our Data Model
We have three tables because the recipe needs a variable number of ingredients and some list storable fixed column table.
--> Table --> Holds
-'pantry_items' Users current items --> name , quantity, unit, expiry date
-'recipes' --> recipe name and the preperation steps

### --> Built with
-Language --> Java
-IDE --> Android Studio
-Target --> Compile SDK --> API 37
-JAVA Compatibility 11
-Build System GRADLE 9.5.0
-Database --> SQL lite vfia 'SQL Lite open helper'
-UI --> XML



### --> The setup and how to run.
1. Clone the repository:

   ```bash
   git clone https://github.com/jacov677/SmartPantryManager.git
   ```

2. Open Android Studio, choose ##Open##, and select the cloned `SmartPantryManager` folder.

3. Wait for Gradle to sync. The first sync downloads dependencies and can take several
   minutes. Progress is shown at the bottom of the window.

4. Select a device from the device dropdown in the toolbar — either an emulator created in
   ##Tools → Device Manager##, or a connected physical device.

5. Click ##Run## (the green ▶ button), or press `Ctrl + R` on macOS.

On first launch the app seeds the recipe collection into the database automatically. No
manual database setup, server, API key or account is required.




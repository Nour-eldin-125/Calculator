# Calculator

## Description:

The project of the Calculator contains 3 Text-Views\
1. Operation Text-View
2. Hint Text-View
3. Result Text-View

Then buttons for calculations and numbers.<br/>
![Description!](/imgs/description.png)
<br/>
<br/>

#### Functions Over View:
##### Memory functions:
###### Memory Clear:
Clears the memory value stored in the calculator
###### Memory Recall:
Gets the value stored in the memory and displays it in the Operation Text-View that it can be used in the calculations.
###### Memory add:
Adds the value in the operation Text-View to the value stored in the memory and updates value in memory.
###### Memory subtract:
Subtracts the value in the operation Text-View from the value stored in the memory and updates value in memory.
###### Clear function:
The clear functions clears all the Text-Views and the values of the Variables inside the code
###### Back function:
The back function removes any miss clicks of the user either removing numbers or the operation clicked by the user and the user can return after pressing the equal button to the second operand in the operations and can back till he clears all the numbers in the first operand.
###### History function:
History displays the history of all the operations done in the calculator and have a delete option by swiping left or right on the item that will be deleted.
###### Sign function:
Sign toggles from positive to negative and vice versa in the Operation Text-View.
###### Arithmetic functions:
The basic arithmetic operations:
1.	Multiplication
2.	Division
3.	Subtraction
4.	Addition
      And all the numbers entered can be either integers or decimal values by clicking on the point button.



## Layouts :
### Layouts of the application:
#### Portait layout :
![portait-Layout!](/imgs/Portait_lo.png)
#### Landsacpe Layout :
![portait-Layout!](/imgs/landscape_lo.png)

### Layouts implementation :
#### Main Layout :
![Main_layout](Lo_Design.png)
<br/>
Parent linear layout (Vertical)
Containing:
1.	Fragment Container
2.	Linear Layout (vertical):
      List of Linear Layouts (horizontal) containing the buttons.

#### Fragment Layout:
![First_Fragment_layout](/imgs/Fragment_1_design.png)

Fragment of the Text-Views containing:
1.	Operation Text-View
2.	Hint Text-View
3.	Result Text-View

<br/>
The fragment is transitioned in the main activity code in the first in the on start and connected using Mutable-Live-Data so any changes happens by the buttons clicking is observed in the fragment and displayed.

#### History Fragment:
![First_Fragment_layout](/imgs/Fragment_2_design.png)

<br/>
Contains a Recycler view to display the items in the database and linked to it by Mutable-Live-Data so any updates in the database is observed and added to the recycler view.


#### Item layout:

![items_Lo](/imgs/item_lo.png)
<br/>
Item of the recycler view contains 2 Text-Views:
1.	Calculation summed up in it (ex. 1+2)
2.	Result of the calculation.

#### Splash screen:

![Splash_LO](/imgs/Splash_lo.png)
<br/>

Splash screen appears in the first opening of the app and stays for 3 second and the goes to the main activity to continue the code.


## Code Review:

![code_classes](/imgs/code_review.png)

### Db package implements room and contains:
1.	Dao
2.	hisDB (Room handler)
3.	HsitoryEntity (Entity)
4.	Repository

### Calculation Fragment contains:
The 3 Text-Views and live data observer to change in them

### Calculator Class contains:
The variables and all of the calculations methods called in the main activity

### History fragment contains:
The recycler view and the live data observer of the data inside the room database

### History viewmodel contains:
Viewmodel class of the live data of the observer of Room and its setters and getters

###	Main Activity contains:
The methods of calling the calculator functions and other methods of updating the live data variables to change the UI

###	My View model contains:
the live data object of the calculator fragment and its setters and getters
###	RvAdapter contains:
The adapter of recycler view of the history fragment
###	Splash Activity contains:
A timer as the application loads first on the Splash Screen and then timer for 3 seconds goes to the Main Activity.

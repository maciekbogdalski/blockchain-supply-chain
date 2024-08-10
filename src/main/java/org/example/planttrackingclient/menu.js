const readline = require('readline').createInterface({
  input: process.stdin,
  output: process.stdout
});

function mainMenu() {
  console.log('\n*** HLF Basil Tracking System Menu ***');
  console.log('1. Create a new plant tracking');
  console.log('2. Delete a plant tracking');
  console.log('3. Update the actual state of a plant');
  console.log('4. Get the actual state of a plant');
  console.log('5. Get the history of a plant');
  console.log('6. Transfer the ownership of a plant');
  console.log('7. Exit');
  readline.question('\nEnter your choice: ', (choice) => {
    switch (choice) {
      case '1':
        // Implement create tracking logic here
        console.log('Creating a new plant tracking...');
        break;
      case '2':
        // Implement delete tracking logic here
        console.log('Deleting a plant tracking...');
        break;
      case '3':
        // Implement update state logic here
        console.log('Updating the actual state of a plant...');
        break;
      case '4':
        // Implement get actual state logic here
        console.log('Getting the actual state of a plant...');
        break;
      case '5':
        // Implement get history logic here
        console.log('Getting the history of a plant...');
        break;
      case '6':
        // Implement transfer ownership logic here
        console.log('Transferring the ownership of a plant...');
        break;
      case '7':
        console.log('Exiting...');
        readline.close();
        return;
      default:
        console.log('Invalid choice, please try again.');
        break;
    }
    mainMenu(); // Re-display menu until user chooses to exit
  });
}

mainMenu(); // Initial call to display the menu

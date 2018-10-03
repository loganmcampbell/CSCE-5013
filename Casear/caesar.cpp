#include <iostream>
using namespace std;

int main ()
{
  // create number variable with zero to initialize it.
  int number = 0;
  string plaintext = "";
  // Ask your user the number to shift:
  cout << "Enter in the number to shift [0 to 26] : ";
  cin >> number;

  // Error Check your number shifting (make sure it's in bounds)
  while (number < 0 || number >= 26 || !cin)
  {
      cin.clear(); cin.ignore();
      cout << "Enter in the number to shift [0 to 26] : ";
      cin >> number;

  }
  // Display Shift #
  cout << "Shift Number = " << number << endl;

  // Take in String (plaintext)
  cout << "Enter the plaintext (string) : ";
  // Clear cin operator to get a clean slate
  cin.ignore();
  // getline to capture a string
  getline(cin, plaintext);
  // display string
  cout << "plaintext = " << plaintext << endl;

  // generate clear string (container) for encrypted text.
  string ciphertext = "";

  // iterate through the string from 0 to the end of the string size
  for (int x = 0; x < plaintext.size(); x++)
  {
    // for loop that goes from A to Z
    for (char a = 'A'; a <= 'Z'; a++)
    {
      // Generate the new character that is built from shifted
      char shift;
      // look for the same letter in the message as in the alphabet
      if (plaintext[x] == a)
      {
        // compute the new shifted character
        shift = (a - number);
        // capture the bounds of the letter to wrap around the alphabet
        // if the shifted character is less than A because (shift is by going..
        // left of the alphabet therefore < less than 'A'.
        if (shift < 'A')
        {
          // if it is less than 'A' take the shifted letter and minus the end of alphabet
          // take the modulo to generate where the character is going to be
          // IN THE ALPHABET BUT NOT THE ASCII ALPHABET!!
          shift = ('Z' - shift) % 26;
          //THEREFORE the character needs to be subtracted from the end of the alphabet.
          shift = 'Z' - shift;
        }
        // add to the encrypted string.
        ciphertext += shift;
      }

      // Capture spaces in the string and add them into the string as well!
      if (plaintext[x] == ' ')
      {
        ciphertext += ' ';
        // break to stop it from adding over and over because this is going
        // through the alphabet each letter of the message...so your string would be
        // 24(message[x]) spaces would be generated LMAO!!!
        break;
      }
    }
  }
  // Output the encrypted string.
  cout << "ciphertext: " << ciphertext << endl;

  //Keep CIPHERTEXT variable different from the one below.


  // Initalize a new string for the decrypted string.
  plaintext = "";
  // Take in String (ciphertext)
  //cout << "Enter the ciphertext (string) : ";
  // Clear cin operator to get a clean slate
  //cin.ignore();
  // getline to capture a string
  //getline(cin, ciphertext);
  // display string
  //cout << "ciphertext = " << ciphertext << endl;

  // iterate through the given message.
  for (int x = 0; x < ciphertext.size(); x++)
  {
    //loop through A TO Z
    for (char a = 'A'; a <= 'Z'; a++)
    {
      //Create shifted character by initializing it.
      char shift;
      // look for the same letter in the alphabet
      if (ciphertext[x] == a)
      {
        // compute shifted character
        shift = (a + number);
        // create bounds if it is greater than Z
        // wrap around
        if (shift > 'Z')
        {
          shift = (shift - 'A') % 26;
          shift = shift + 'A';
        }
        // create bounds if it is less than A
        // wrap around
        else if (shift < 'A')
        {
          shift = (shift - 'Z') % 26;
          shift = shift + 'Z';
          cout << "shift value : " << int(shift) << endl;
        }
        // Add decrypted letter to the string.
          plaintext += shift;
      }
      // capture spaces
      if (ciphertext[x] == ' ')
      {
        // place spaces into string
        plaintext += ' ';
        break;
      }
    }
  }
  cout << "plaintext: " << plaintext << endl;
  return 0;
}

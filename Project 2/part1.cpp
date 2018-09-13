#include <iostream>
#include <cstdlib>
#include <time.h>
#include <cstring>
#include <iomanip>
#include <sstream>
using namespace std;

void random (char key192[], int length)
{
  srand(time(NULL));
  int random;
  char letter;
  for (int x = 0; x < length; x++)
  {
    random =  'A' + rand()%26;
    letter = char(random);
    key192[x] = (letter);
  }
}

void initializevector (char iv[])
{
  for(int i = 0; i < 36 ; i++)
  {
    sprintf(iv + i, "%X", rand() % 16);
  }
}

string print_hex(int dec)
{
  string s = "";
  int rem;
  while (dec > 0)   // Do this whilst the quotient is greater than 0.
  {
    rem = dec % 16; // Get the remainder.
    if (rem > 9)
    {
      // Map the character given that the remainder is greater than 9.
      switch (rem)
      {
        case 10: s = "A" + s; break;
        case 11: s = "B" + s; break;
        case 12: s = "C" + s; break;
        case 13: s = "D" + s; break;
        case 14: s = "E" + s; break;
        case 15: s = "F" + s; break;
      }
    }
    else
    {
      s = char(rem + 48) + s; // Converts integer (0-9) to ASCII code.
      // x + 48 is the ASCII code for x digit (if 0 <= x <= 9)
    }
    dec = dec/16;
   }
   if (s == "") // if the number was 0, the string will remain empty
     s = "0";
   else
     return s;
}

template <typename data>
void print (data key192[], int length)
{
  for(int x = 0; x < length; x++)
  {
    cout << key192[x] << " ";
  }
  cout << endl;
}

template <typename data>
void convert (data key24[], int key24i[], int length)
{
  for (int x = 0; x < length; x++)
    key24i[x] = (int)key24[x];
}

template <typename data>
string arrtostr (data array[], int length, string container)
{
  container = "";
  for (int x = 0; x < length; x++)
  {
    container = container + array[x];
  }
  return container;
}

string ctohex (string message)
{
  char * characters = new char [message.length()+1];
  strcpy (characters, message.c_str());

  string hexcharacters [message.length()*2];
  char const hex_chars[16] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
  for (int x = 0; x < message.length(); x++)
  {
    char const byte = characters[x];
    hexcharacters[x] += hex_chars[ (byte & 0xF0) >> 4 ];
    hexcharacters[x] += hex_chars[ (byte & 0x0F) >> 0 ];
  }
  string hexmessage = "";
  return hexmessage = arrtostr(hexcharacters,message.length()*2, hexmessage);
}

template <typename data>
void sepbytwo (data array[], int length, string hexarray[])
{
  for (int x = 0; x < length; x++)
  {
    if ((x + 1) >= length)
    {
      break;
    }
    else
    {
      string combine;
      string s(1, array[x]);
      string s1(1, array[x+1]);
      //hexarray[x] = "" + to_string(array[x]) + to_string(array[x+1]);
      combine = s + s1;
      //cout << "hexarray [" << x << "] = " << combine;
      hexarray[x] = combine;
    }
  }
}

long hextoint(string hexnumber)
{
  char * p;
  long n = strtoul( hexnumber.c_str(), & p, 16 );
    if ( * p != 0 )
      {
          //cout << "not a number" << endl;
      }
      else
      {
          //cout << n << endl;
          return n;
      }
}

int XOR(int x, int y)
{
    int res = 0; // Initialize result

    // Assuming 32-bit Integer
    for (int i = 31; i >= 0; i--)
    {
       // Find current bits in x and y
       bool b1 = x & (1 << i);
       bool b2 = y & (1 << i);

        // If both are 1 then 0 else xor is same as OR
        bool xoredBit = (b1 & b2) ? 0 : (b1 | b2);

        // Update result
        res <<= 1;
        res |= xoredBit;
    }
    return res;
}

int main ()
{
  // 24 * 8 (each charcter is 8 bits) = 192 bits
  string sharedkey = "";
  string ivcontain = "";
  int const hlength = 36;
  int const length = 24;
  int const mlength = 18;
  char  key192   [length]  = {};
  int   key192i  [length]  = {};
  string hexarray [hlength] = {};
  string ciphertext[hlength] = {};
  random (key192,length);
  convert(key192,key192i,length);

  for (int x = 0; x < length; x++)
  {
    sharedkey += key192[x];
  }
  cout << "Shared Key :\t " << sharedkey << endl;

  //ASCII CODE is 8bits per character = 1 BYTE
  //THEREFORE MESSAGE HAS TO BE 18 CHARACTERS LONG
  //string message =  "LOGAN MALACHI CAMP";
  cout << "ENTER A 18-BYTE MESSAGE : ";
  //string message = "";
  //cin >> message;
  string message = "cipherblockchainin";
  while (!(message.length() == 18) || message == "")
  {
    cout << "NOT EXACTLY 18 BYTES OR NOTHING HAS BEEN ENTERED." << endl;
    cin.ignore();
    cin >> message;
  }
  cout << "Message :\t " << message << endl;
  cout << "Length :\t " << message.length() << endl;
  char * characters = new char [message.length()+1];
  strcpy (characters, message.c_str());
  string container [length] = {};
  char iv [hlength] = {};
  initializevector(iv);

  //ciphertext = arrtostr(container,length,ciphertext);
  ivcontain = arrtostr(iv,hlength,ivcontain);
  //cout << "Ciphertext =\t " << ciphertext << endl;
  cout << "IV VECTOR :\t " << ivcontain << endl;
  //cout << "HEX MESSAGE =\t " << ctohex(message);
  sepbytwo(iv,hlength,hexarray);
  //print(hexarray,hlength);

  string display;
  for(int x = 0; x < mlength; x++)
  {
    string capture;
    int letter = (int)characters[x];
    cout << "LETTER = " << characters[x] << "\t";
    cout << "ASCII CODE | IV VECTOR : [" << letter << ",";
    string hexletter = "";
    hexletter = hexarray[x];
    cout << hexletter << "] ";
    int hexvalue = hextoint(hexletter);
    int xorvalue = XOR(letter,hexvalue);
    cout << setw(10) << "\tXOR VALUE : "; //<< xorvalue;
    capture = print_hex(xorvalue);
    cout << "\t : " << capture;
    cout << endl;
    ciphertext[x] = capture;
    display = display + capture;
  }
  cout << endl;
  cout << "CIPHERTEXT = \t" << display;
  cout << endl;cout << endl;
  string plaintext;
  for (int x = 0; x < mlength; x++)
  {
    int xorvalue;
    cout << "CIPHERLETTER: " << ciphertext[x] << setw(12) <<  "\tIV :" << hexarray[x];
    xorvalue = XOR(hextoint(ciphertext[x]),hextoint(hexarray[x]));
    cout << setw(10) << "\tXOR VALUE : " << xorvalue;
    cout << setw(10) << "\t\tPLAINLETTER : " << (char)xorvalue;
    plaintext = plaintext + (char)xorvalue;
    cout << endl;
  }
  cout << endl;
  cout << "PLAINTEXT = \t" << plaintext;









  return 0;
}

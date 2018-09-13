#include <iostream>
#include <cstdlib>
#include <time.h>
#include <cstring>
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

int main ()
{
  // 24 * 8 (each charcter is 8 bits) = 192 bits
  string sharedkey = "";
  string ciphertext = "";
  string ivcontain = "";
  int const hlength = 36;
  int const length = 24;
  int const mlength = 18;
  char  key192   [length]  = {};
  int   key192i  [length]  = {};
  random (key192,length);
  convert(key192,key192i,length);

  for (int x = 0; x < length; x++)
  {
    sharedkey += key192[x];
  }
  cout << "Shared Key :\t " << sharedkey << endl;

  //ASCII CODE is 8bits per character = 1 BYTE
  //THEREFORE MESSAGE HAS TO BE 18 CHARACTERS LONG
  string message =  "CIPHERBLOCKCHAININ";
  cout << "Message :\t " << message << endl;
  cout << "Length :\t " << message.length() << endl;
  char * characters = new char [message.length()+1];
  strcpy (characters, message.c_str());
  string container [length] = {};
  char iv [hlength] = {};
  initializevector(iv);
  for (int x = 0; x < length; x++)
  {
    string c;
    if (x < message.length())
    {
      if (key192[x] ^ characters[x])
      {
          container[x] = key192[x];
      }
      else
      {
        container[x] = key192[x];
      }
    }
  }
  //ciphertext = arrtostr(container,length,ciphertext);
  ivcontain = arrtostr(iv,hlength,ivcontain);
  cout << "Ciphertext =\t " << ciphertext << endl;
  cout << "IV VECTOR :\t " << ivcontain << endl;

  cout << "HEX MESSAGE =\t " << ctohex(message);


  return 0;
}

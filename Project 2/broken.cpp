#include <iostream>
#include <cstdlib>
#include <time.h>
using namespace std;

void random (char key16[], int length)
{
  srand(time(NULL));
  int random;
  char letter;
  for (int x = 0; x < length; x++)
  {
    random =  'A' + rand()%26;
    letter = char(random);
    key16[x] = (letter);
  }
}

template <typename data>
void print (data key16[], int length)
{
  for(int x = 0; x < length; x++)
    cout << key16[x] << " ";
  cout << endl;
}
template <typename data>
void convert (data key16[], int key16i[], int length)
{
  for (int x = 0; x < length; x++)
    key16i[x] = (int)key16[x];
}

int main ()
{
  int const length = 12;
  char  key16   [length]  = {};
  int   key16i  [length]  = {};
  random (key16,length);
  convert(key16,key16i,length);
  print(key16,length);
  print(key16i,length);

  return 0;
}

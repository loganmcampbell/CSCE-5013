load 'dictionary.rb'
load 'cipherfunction.rb'
load 'bruteforcefunction.rb'
load 'decipherfunction.rb'

file = 'sample.txt'
dictionary = dictionary(file)
ciphertext = "jo dpnqvufs xpsme"
ciphertext = ciphertext.upcase.split(" ")
found = []
for cipherword in ciphertext
    for word in dictionary
        if (bruteforcefunction(cipherword, word))
          found << bruteforcefunction(cipherword,word)
          break
        end
  end
end

p "[ MESSAGE , KEY ]"
p found

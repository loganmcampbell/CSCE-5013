load 'dictionary.rb'
load 'cipherfunction.rb'
load 'bruteforcefunction.rb'
load 'decipherfunction.rb'

file = 'sample.txt'
dictionary = dictionary(file)
print "Enter ciphertext : "
# ciphertext = gets.chomp
ciphertext = "LOGAN CAMPBELL"
ciphertext = ciphertext.upcase
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
puts "Press RETURN when you're done."
gets

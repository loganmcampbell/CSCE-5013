puts "Enter in the number between 1 and 25 for ciphertext : "
number = gets.to_i
puts "KEY (NUMBER) = " + number.to_s
print "Enter the plaintext : "
plaintext = ""
plaintext = gets.chomp
puts "PLAINTEXT = #{plaintext}"

alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
alphabet = alphabet.chars.to_a

shiftalpha = []
alphabet.each_index do |i|
    # p i
    #new shift
    shift = i + number
    if (shift >= 25)
      shift = (shift + alphabet.length) % alphabet.length
    end
    # p alphabet[i].to_s, shift
    shiftalpha.insert(shift,alphabet[i])
    shift = shift - 1
end
shiftalpha = shiftalpha.compact

iterate = plaintext.split('')
codeword = []
cipher = []
iterate.each_index do |x|
  codeword << alphabet.index(iterate[x])
end
codeword.each_index do |z|
  if (codeword[z] == nil)
    cipher << " "
  else
  cipher << alphabet[shiftalpha.index(iterate[z])]
end
end
# p shiftalpha
# p alphabet
# p iterate
# p codeword
p cipher
puts "Press RETURN when you're done."
gets

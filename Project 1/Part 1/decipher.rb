puts "Enter in the number between 1 and 25 for ciphertext : "
number = gets.to_i
puts "KEY (NUMBER) = " + number.to_s
puts "Enter the ciphertext : "
ciphertext = ""
ciphertext = gets.chomp
ciphertext = ciphertext.upcase
print "CIPHErTEXT = #{ciphertext}"

alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
alphabet = alphabet.chars.to_a

shiftalpha = []
alphabet.each_index do |i|
    shift = i + number
    if (shift >= 25)
      shift = (shift + alphabet.length) % alphabet.length
    end
    # p alphabet[i].to_s, shift
    shiftalpha.insert(shift,alphabet[i])
    shift = shift + 1
end
puts "NUMBER KEY [" + number.to_s + "] "
shiftalpha = shiftalpha.compact
iterate = ciphertext.split('')
codeword = []
original = []
iterate.each_index do |x|
  codeword << alphabet.index(iterate[x])
end

codeword.each_index do |z|
    if (codeword[z] == nil)
      original << " "
    else
    original << shiftalpha[alphabet.index(iterate[z])]
  end
end

p original
puts "Press RETURN when you're done"
gets

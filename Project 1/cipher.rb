# puts "enter in the number between 1 and 25 for ciphertext : "
# number = gets
number = 3
puts "shift number  = " + number.to_s
puts "enter the plaintext : "
plaintext = ""
# plaintext = gets
plaintext = "COMPUTER"
plaintext = plaintext.upcase
puts 'PLAINTEXT = ' + plaintext.upcase

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
   cipher << alphabet[shiftalpha.index(iterate[z])]
end

p shiftalpha
p alphabet
p iterate
p codeword
p cipher

# puts "Press RETURN when you're done."
# gets

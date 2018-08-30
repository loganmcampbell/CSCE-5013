# Get KEY NUMBER (N):

# gets number
number = 3

# GET CIPHERTEXT
# ciphertext = ""
#gets ciphertext
ciphertext = "VHQGQXGHV"


ciphertext = ciphertext.upcase
puts 'CIPHERTEXT = ' + ciphertext.upcase

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
# p alphabet
iterate = ciphertext.split('')
codeword = []
original = []
iterate.each_index do |x|
  codeword << alphabet.index(iterate[x])
end
# p codeword
codeword.each_index do |z|
    original << shiftalpha[alphabet.index(iterate[z])]
end

p original

def ciphertext(text, key)
  number = key
  plaintext = text.upcase

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
  return cipher
end

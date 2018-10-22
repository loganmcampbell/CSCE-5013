require "openssl"
require "encrypto_signo"

pairkey = OpenSSL::PKey::RSA.new(2048)
# puts("Public Key: #{pairkey.public_key}")
# puts("Private Key: #{pairkey}")
message = ""
while message.length != 18
  puts("Enter a 18-Byte Message : ")
  message = gets.chomp
end

#SIGNS MESSAGE HERE:
puts("** SIGNATURE IS CREATED WITH PRIVATE KEY ** ")
signature = EncryptoSigno.sign(pairkey,message)

File.open('signature.txt', 'w') do |f1|
  f1.print(signature)
end

File.open('msg2.txt', 'w') do |f1|
  f1.print(message)
end

puts("PRESS ENTER TO CONTINUE OR EDIT MESSAGE OR SIGNATURE")
gets()
# READ SIGNATURE!!! EDIT SIGNATURE SOME HOW!!!
rsignature = ""
fh = open('signature.txt')
content = fh.read
fh.close
rsignature += content

rmessage = ""
File.open('msg2.txt', 'r') do |f|
  while line = f.gets
    rmessage = line
  end
end

verification = EncryptoSigno.verify(pairkey,rsignature,rmessage)
print("#{verification}")
puts()
puts("** ENTER TO QUIT ** ")
gets()

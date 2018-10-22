require "openssl"
require "base64"
require "active_support/security_utils"

#1 Generate Key
key = ([*('A'..'Z'),*('0'..'9')]-%w(0 1 I O)).sample(16).join
puts("Shared Key [16 BYTE]: ", key)
puts()
#2 Get Data
# message = "LOGANCAMPBELL123"
message = ""
while message.length != 18 do
  puts("Enter a 18-Byte Message : ")
  message = gets.chomp
end
puts("MESSAGE = #{message}")

File.open('msg1.txt', 'w') do |f1|
  f1.print(message)
end

def signit(message, key)
  return OpenSSL::HMAC.hexdigest("SHA256", key, message)
end
def verify(verifymsg, signedmsg, key)
  return ActiveSupport::SecurityUtils::secure_compare(
    OpenSSL::HMAC.hexdigest("SHA256", key, verifymsg), signedmsg)
end
puts("\n")
puts("***ALICE***")
puts("message is hashed with key ")
hmacd = signit(message,key)

File.open('hmacd.txt', 'w') do |f1|
  f1.print(hmacd)
end

print("HMAC: " , hmacd)
puts("\n\n")
puts("***BOB***")



puts("message is collected and then verified hmac is checked")


puts("PRESS ENTER TO CONTINUE OR EDIT HMACD TXT")
gets()
rhmac = ""
File.open('hmacd.txt', 'r') do |f1|
  while line = f1.gets
    rhmac = line
  end
end

puts("PRESS ENTER TO CONTINUE OR EDIT MESSAGE FROM msg1.txt")
gets()
rmessage = ""
File.open('msg1.txt', 'r') do |f1|
  while line = f1.gets
    rmessage = line
  end
end

print(verify(rmessage,rhmac,key))
puts("\n")
puts("** PRESS ENTER TO QUIT **")
gets()

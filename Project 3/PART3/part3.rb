require "openssl"
require "base64"
require "active_support/security_utils"
require "encrypto_signo"

#1 GENERATE KEY HMAC
key = ([*('A'..'Z'),*('0'..'9')]-%w(0 1 I O)).sample(16).join
puts("Shared Key [16 BYTE]: ", key)
puts()





message = ""
while message.length != 7 do
  puts("Enter a 7-Byte Message : ")
  message = gets.chomp
end
puts("MESSAGE = #{message}")



def signit(message, key)
  return OpenSSL::HMAC.hexdigest("SHA256", key, message)
end
def verify(verifymsg, signedmsg, key)
  return ActiveSupport::SecurityUtils::secure_compare(
    OpenSSL::HMAC.hexdigest("SHA256", key, verifymsg), signedmsg)
end
puts("\n")

count = 0
t1 = Time.now.to_f
while count < 100 do
  hmacd = signit(message,key)
  verify(message,hmacd,key)
  count = count + 1
end

t2 = Time.now.to_f
diff = (t2 - t1)*1000
avgHMAC = diff/100
print("AVERAGE TIME HMAC [100x] : #{avgHMAC} MILLISECONDS")
puts()

pairkey = OpenSSL::PKey::RSA.new(2048)

count = 0
t1 = Time.now.to_f
while count < 100 do
  EncryptoSigno.sign(pairkey, message)
  count = count + 1
end
t2 = Time.now.to_f
diff = (t2 - t1)*1000
avgSign = diff/100
print("AVERAGE TIME TO SIGN[100x] : #{avgSign} MILLISECONDS")
puts()

count = 0
t1 = Time.now.to_f
signature = EncryptoSigno.sign(pairkey,message)
while count < 100 do
  verification = EncryptoSigno.verify(pairkey,signature,message)
  count = count + 1
end
t2 = Time.now.to_f
diff = (t2 - t1) * 1000
avgVer = diff/100
print("AVERAGE TIME TO VErIFY[100x] : #{avgVer} MILLISECONDS")
puts()



count = 0
t1 = Time.now.to_f
while count < 100 do
  signature = EncryptoSigno.sign(pairkey,message)
  verification = EncryptoSigno.verify(pairkey,signature,message)
  count = count + 1
end
t2 = Time.now.to_f
diff = (t2 - t1)*1000
avgSV = diff/100
print("AVERAGE TIME TO SIGN AND VERIFY [100x] : #{avgSV} MILLISECONDS")
puts()
puts("** PRESS ENTER TO EXIT **")
gets()

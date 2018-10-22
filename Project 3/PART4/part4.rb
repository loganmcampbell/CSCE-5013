require 'digest'
#GENERATE MESSAGE
puts("ENTER THE MESSAGE(S) LENGTH : ")
# length = gets.to_i
length = 8
messagelen = 4
amount = 5
puts()
randommessage = Array.new
x = 0
while (x < amount) do
  message = ([*('A'..'Z'),*('0'..'9')]-%w(0 1 I O)).sample(messagelen).join
  hash = Digest::SHA256.hexdigest(message)
  hash = hash[0...length]
  randommessage[x] = hash
  x = x + 1
end
print(randommessage)


found = false
t1 = Time.now.to_f
count = 0

while (found == false) do
      while (x < amount) do
        message = ([*('A'..'Z'),*('0'..'9')]-%w(0 1 I O)).sample(messagelen).join
        hash = Digest::SHA256.hexdigest(message)
        hash = hash[0...length]
        randommessage[x] = hash
        x = x + 1
      end
      if (randommessage.uniq.length == randommessage.length)
        count = count + 1
        puts(count)
      else
        dup = array.select{|element| array.count(element) > 1 }
        print(dup)
        found = true
      end
end
t2 = Time.now.to_f

diff = ((t2 - t1) * 1000)/20
puts("AVERAGE TIME TO CALCULATE A DUPLICATE HASH : #{diff} MS")
puts("NUMBER OF TIMES : #{count}")

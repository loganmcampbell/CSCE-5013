require "openssl"
require "base64"
require "active_support/security_utils"
def signit(message, key)
  return OpenSSL::HMAC.hexdigest("SHA256", key, message)
end
def verify(verifymsg, signedmsg, key)
  return ActiveSupport::SecurityUtils::secure_compare(
    OpenSSL::HMAC.hexdigest("SHA256", key, verifymsg), signedmsg)
end
class Node
  attr_accessor :data, :left, :right
  def initialize(data)
    @data = data
  end
end
messages = Array.new
hashes   = Array.new
phashes  = Array.new
limit = 8
key = ([*('A'..'Z'),*('0'..'9')]-%w(0 1 I O)).sample(16).join
puts("Shared Key [16 BYTE]: ", key)
puts()
for x in 1..limit
  message = ""
  while message.length != 4
    # puts("4-letter message #{x} : ")
    # message = gets.chomp
    random4 = ([*('A'..'Z'),*('0'..'9')]-%w(0 1 I O)).sample(4).join
    message = random4
    messages[x] = message
  end
end
#A
treeroot        =   Node.new(0)
#BC
parent1 = Node.new(1)
treeroot.left = parent1
parent2 = Node.new(2)
treeroot.right = parent2
#DE
parent1_3 = Node.new(3)
parent1.left = parent1_3
parent1_4 = Node.new(4)
parent1.right = parent1_4
#FG
parent2_5 = Node.new(5)
parent2.left = parent2_5
parent2_6 = Node.new(6)
parent2.right = parent2_6
#H/I/J/K/L/M/N/O
parent1_3.left  = signit(messages[1],key)
hashes[1] = parent1_3.left
parent1_3.right = signit(messages[2],key)
hashes[2] = parent1_3.right
parent1_4.left  = signit(messages[3],key)
hashes[3] = parent1_4.left
parent1_4.right = signit(messages[4],key)
hashes[4] = parent1_4.right
parent2_5.left  = signit(messages[5],key)
hashes[5] = parent2_5.left
parent2_5.right = signit(messages[6],key)
hashes[6] = parent2_5.right
parent2_6.left  = signit(messages[7],key)
hashes[7] = parent2_6.left
parent2_6.right = signit(messages[8],key)
hashes[8] = parent2_6.right

parent1_3 = signit(parent1_3.left + parent1_3.right, key)
phashes[1] = parent1_3
parent1_4 = signit(parent1_4.left + parent1_4.right, key)
phashes[2] = parent1_4
parent2_5 = signit(parent2_5.left + parent2_5.right, key)
phashes[3] = parent2_5
parent2_6 = signit(parent2_6.left + parent2_6.right, key)
phashes[4] = parent2_6
parent1   = signit(parent1_3      + parent1_4,       key)
phashes[5] = parent1
parent2   = signit(parent2_5      + parent2_6,       key)
phashes[6] = parent2
treeroot  = signit(parent1        + parent2,         key)
phashes[7] = treeroot


print("\tGENERATED DIGITAL SIGNATURE FOR MERKLE TREE ROOT")
puts("\n================================================================")
print(treeroot)
puts("\n================================================================")
puts()
puts("Enter the message number to extend signature: ")
choice = Integer(gets.chomp)
# choice = 1
while (choice < 1 || choice > 8)
  puts("Enter a valid number:  ")
  choice = Integer(gets.chomp)
end
print(choice)
puts()
File.open('sig.txt', 'w') do |f1|
  f1.print(hashes[choice])
end

File.open('msg.txt', 'w') do |f1|
  f1.print(messages[choice])
end

puts("#{messages[choice]} : #{hashes[choice]}")

puts("PRESS ENTER TO CONTINUE OR EDIT SIGNATURE")
gets()
sig = ""
File.open('sig.txt', 'r') do |f1|
  while line = f1.gets
    sig = line
  end
end
hashes[choice] = sig


puts("PRESS ENTER TO CONTINUE OR EDIT MESSAGE")
gets()
msg = ""
File.open('msg.txt', 'r') do |f1|
  while line = f1.gets
    msg = line
  end
end
messages[choice] = msg
puts(verify(msg,sig,key))

treecontainer = Array.new()
treecombine   = Array.new()
for x in 1..limit
  treecontainer[x] = signit(messages[x],key)
end
treecontainer[choice] = sig

treecombine[1] = signit(treecontainer[1] + treecontainer[2], key)
treecombine[2] = signit(treecontainer[3] + treecontainer[4], key)
treecombine[3] = signit(treecontainer[5] + treecontainer[6], key)
treecombine[4] = signit(treecontainer[7] + treecontainer[8], key)
treecombine[5] = signit(treecombine[1] + treecombine[2], key)
treecombine[6] = signit(treecombine[3] + treecombine[4], key)
treecombine[7] = signit(treecombine[5] + treecombine[6], key)

puts("----------------------------------------------------------------")
puts("-------------------- M E R K L E T R E E -----------------------")
puts("--------------------  O R I G I N I A L  -----------------------")
puts("----------------------------------------------------------------")
puts(phashes)
puts()
puts("----------------------------------------------------------------")
puts("-------------------- M E R K L E T R E E -----------------------")
puts("------------------  R E G E N E R A T E D  ---------------------")
puts("----------------------------------------------------------------")
puts(treecombine)
puts()

if (treecombine[7] == phashes[7])
  puts("VERIFICATION: TRUE")
else
  puts("VERIFICATION: FALSE")
end


gets()

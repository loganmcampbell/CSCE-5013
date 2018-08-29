def dictionary (filename)

content = ""
File.open(filename).each do |line|
   content = content + line
end

content = content.tr('.','')
content = content.tr(',','')
content = content.split(" ")
vocabulary = content

return content

end

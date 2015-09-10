#This script is for use of the ModUpdater developer to generate the GameVersions enum

require 'json'

puts "Please give me your file"
json = JSON.parse(File.read(gets.chomp))

json.each do |i|
  if i["name"] != nil
    name = i["name"]
    enumname = name.upcase
    enumname = enumname.gsub(/\s/, '')
    enumname.gsub!('.', '')
    enumname.gsub!('-', '')
    if enumname[0,1] =~ /\D/
      puts "#{enumname}(\"#{name}\"),"
    else
      puts "R#{enumname}(\"#{name}\"),"
    end
  end
end
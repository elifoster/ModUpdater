require 'json'

$wikichangelog = ""
$cfchangelog = ""

def die_with_error(valuename, type)
  type = type.upcase
  exit "PROVIDED JSON DOES NOT HAVE #{valuename} #{type}!! FATAL!"
end

def scan_json(json)
  if json["modupdater"] != nil
    $inner = json["modupdater"]
    if $inner["new_vers"] != nil
      if $inner["cf_settings"] != nil
        $cf = $inner["cf_settings"]
        $release_type = $cf["type"]
        $api_key = $cf["api_key"]
        $project = $cf["project"]
        $file_name = $cf["file_name"]
        $file_dir = $cf["file_dir"]
        $game_vers = $cf["game_vers"]
        if $api_key != nil && $project != nil && $file_dir != nil
          if $release_type == nil
            if $release_type != "release" && $release_type != "beta" && $release_type != "alpha"
              type = "release"
            end
          end
          if $file_name == nil
            $file_name = file_dir.split('/')[-1]
          end
        else
          die_with_error("api_key/project/file_dir", "string")
        end
      else
        die_with_error("cf_settings", "object")
      end
      if $inner["issues_bool"] != nil
        if $inner["issues_url"] != nil
          if $inner["issues_bool"] == true
            $issue_url = $inner["issues_url"]
          end
        else
          die_with_error("issues_url", "string")
        end
      else
        die_with_error("issues_bool", "boolean")
      end
      if $inner["changelog"] != nil
        if $inner["changelog"]["entry"] != nil
          if $inner["wiki_bool"] != nil
            if $inner["wiki_bool"] == true
              if $inner["wiki_page"] == nil
                if $inner["mod_name"] != nil
                  $mod_name = $inner["mod_name"]
                  $wiki_page = $inner["mod_name"]
                else
                  die_with_error("wiki_page/mod_name", "string")
                end
              else
                if $inner["mod_name"] != nil
                  $mod_name = $inner["mod_name"]
                  $wiki_page = $inner["wiki_page"]
                else
                  $mod_name = $inner["wiki_page"]
                  $wiki_page = $inner["wiki_page"]
                end
              end
                $inner["changelog"].each do |entry|
                  if entry["changes"] != nil
                    changechange = entry["changes"]
                    if entry["type"] != nil
                      changetype = entry["type"]
                      if issue_url != nil && entry["issue"] != nil && inner["issues_bool"] == true
                        changeissue = entry["issue"]
                        $wikichangelog = $wikichangelog + "* '''#{changetype}''': #{changechange} ([#{issue_url}/#{changeissue} ##{changeissue}]).\n"
                      else
                        $wikichangelog = $wikichangelog + "* '''#{changetype}''': #{changechange}\n"
                      end
                    else
                      if issue_url != nil && entry["issue"] != nil && inner["issues_bool"] == true
                        changeissue = entry["issue"]
                        $wikichangelog = $wikichangelog + "* #{changechange} ([#{issue_url}/#{changeissue} ##{changeissue}])\n"
                      else
                        $wikichangelog = $wikichangelog + "* #{changechange}\n"
                      end
                    end
                  else
                  die_with_error("changes", "string")
                end
              else
                die_with_error("wiki_page", "string")
              end
            end
          else
            die_with_error("wiki_bool", "boolean")
          end
        else
          die_with_error("entry", "object")
        end
      end
      if $inner["twitter_bool"] != nil
        if $inner["twitter_bool"] == true
          $twitter_un = $inner["twitter_un"]
        end
      else
        die_with_error("twitter_bool", "boolean")
      end
    else
      die_with_error("new_vers", "string")
    end
  else
    die_with_error("modupdater", "object")
  end
end

puts "Please input your JSON configuration file: "
file_name = gets.chomp
file = File.read(file_name)
hash = JSON.parse(file)
scan_json(hash)

exit

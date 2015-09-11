require 'json'
require 'mediawiki_api'
require 'io/console'
require 'mime/types'
require 'crack'
require_relative 'bot.rb'
require_relative 'libs/wikiutils.rb'

$wikichangelog = ""
$cfchangelog = ""

def die_with_error(valuename, type)
  type = type.upcase
  exit "PROVIDED JSON DOES NOT HAVE #{valuename} #{type}!! FATAL!"
end

def handle_base_json(json)
  if json["modupdater"] != nil
    $inner = json["modupdater"]
    if $inner["new_vers"] != nil
      $new_vers = $inner["new_vers"]
    else
      die_with_error("new_vers", "string")
    end
  else
    die_with_error("modupdater", "object")
  end
end

def handle_cf_json(json)
  if $inner["cf_settings"] != nil
    $cf = $inner["cf_settings"]
    $release_type = $cf["type"]
    $api_key = $cf["api_key"]
    $project = $cf["project"]
    $file_name = $cf["file_name"]
    $file_dir = $cf["file_dir"]
    $game_versions = Array.new
    if $cf["game_versions"] != nil
      $cf["game_versions"].each do |vers|
        $game_versions.push(vers)
      end
    else
      die_with_error("game_versions", "array")
    end
    if $api_key != nil && $project != nil && $file_dir != nil
      if $release_type != "release" && $release_type != "beta" && $release_type != "alpha"
        $release_type = "release"
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
end

def handle_twitter_json(json)
  if $inner["twitter_bool"] != nil
    if $inner["twitter_bool"] == true
      $twitter_un = $inner["twitter_un"]
      $twitter_bool = true
      if $inner["tweet_custom"] != nil
        if $inner["tweet_custom"].length > 140
          name_length = $mod_name.length
          version_length = $new_vers.length
          if name_length + version_length > 101
            $twitter_msg = "My mod with a long name and version number has updated. Get it at CurseForge"
          else
            $twitter_msg = "#{$mod_name} has updated to #{$new_vers}. Get it at CurseForge"
          end
        else
          $twitter_msg = $inner["tweet_custom"]
        end
      else
        name_length = $mod_name.length
        version_length = $new_vers.length
        if name_length + version_length > 101
          $twitter_msg = "My mod with a long name and version number has updated. Get it at CurseForge"
        else
          $twitter_msg = "#{$mod_name} has updated to #{$new_vers}. Get it at CurseForge"
        end
      end
    else
      $twitter_bool = false
    end
  else
    die_with_error("twitter_bool", "boolean")
  end
end

def handle_wiki_json(json)
  if $inner["wiki_settings"] != nil
    $wiki = $inner["wiki_settings"]
    if $wiki["wiki_bool"] != nil
      $wiki_bool = $wiki["wiki_bool"]
      if $wiki["wiki_bool"] == true
        if $wiki["wiki_page"] == nil
          if $wiki["mod_name"] != nil
            $mod_name = $wiki["mod_name"]
            $wiki_page = "#{$mod_name}/Changelog"
          else
            die_with_error("wiki_page/mod_name", "string")
          end
        else
          if $wiki["mod_name"] != nil
            $mod_name = $wiki["mod_name"]
            $wiki_page = $wiki["wiki_page"]
          else
            $mod_name = $wiki["wiki_page"].chomp('/Changelog')
            $wiki_page = $wiki["wiki_page"]
          end
        end
      end
      if $wiki["wiki_un"] == nil
        die_with_error("wiki_un", "string")
      else
        $wiki_un = $wiki["wiki_un"]
      end

      if $wiki["section_size"] != nil
        if $wiki["section_size"].is_a? String
          if $wiki["section_size"].to_i < 2 || $wiki["section_size"].to_i > 6
            $section_size = 2
          else
            $section_size = $wiki["section_size"].to_i
          end
        else
          if $wiki["section_size"] < 2 || $wiki["section_size"] > 6
            $section_size = 2
          else
            $section_size = $wiki["section_size"]
          end
        end
      else
        $section_size = 2
      end
    else
      die_with_error("wiki_bool", "boolean")
    end
  else
    die_with_error("wiki_settings", "object")
  end
end

def handle_changelog_json(json)
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
    $wikichangelog = "=" * $section_size + " #{$new_vers} " + "=" * $section_size + "\n"
    $inner["changelog"].each do |entry|
      if entry["changes"] != nil
        changechange = entry["changes"]
        if entry["type"] != nil
          changetype = entry["type"]
          if $issue_url != nil && entry["issue"] != nil && $inner["issues_bool"] == true
            if entry["issue"].is_a? String
              changeissue = entry["issue"].to_i # For XML
            else
              changeissue = entry["issue"]
            $wikichangelog = $wikichangelog + "* '''#{changetype}''': #{changechange} ([#{$issue_url}/#{changeissue} ##{changeissue}]).\n"
            $cfchangelog = $cfchangelog + "* **#{changetype}**: #{changechange} ([[#{$issue_url}/#{changeissue}|##{changeissue}]]).\n"
          else
            $wikichangelog = $wikichangelog + "* '''#{changetype}''': #{changechange}.\n"
            $cfchangelog = $cfchangelog + "* **#{changetype}**: #{changechange}.\n"
          end
        else
          if $issue_url != nil && entry["issue"] != nil && $inner["issues_bool"] == true
            if entry["issue"].is_a? String
              changeissue = entry["issue"].to_i # For XML
            else
              changeissue = entry["issue"]
            $wikichangelog = $wikichangelog + "* #{changechange} ([#{issue_url}/#{changeissue} ##{changeissue}]).\n"
            $cfchangelog = $cfchangelog + "* #{changechange} ([[#{issue_url}/#{changeissue}|##{changeissue}]]).\n"
          else
            $wikichangelog = $wikichangelog + "* #{changechange}.\n"
            $cfchangelog = $cfchangelog + "* #{changechange}.\n"
          end
        end
      else
      die_with_error("changes", "string")
      end
    end
  end
end

def get_version_id_from_name(json, name)
  hash = JSON.parse(json)
  hash.each do |i|
    if i["name"] == name
      return i["id"]
    end
  end
end

def get_extension_from_file(file)
  return file.split('.')[-1]
end

def call_everything()
  $cf = Bot::CurseForge.new($project)
  version_ids = Array.new()
  version_id = get_version_id_from_name($cf.get_versions($api_key), $game_versions)
  $game_versions.each do |version_name|
    version_id = get_version_id_from_name($cf.get_versions($api_key), version_name)
    version_ids.push(version_id)
  end
  metadata_hash = {
    :changelog => $cfchangelog,
    :releaseType => $release_type,
    :gameVersions => version_ids,
    :displayName => $file_name,
    :change_markup_type => 'creole'
  }
  metadata_json = JSON.generate(metadata_hash)
  type = MIME::Types.type_for(get_extension_from_file($file_dir))

  if $cf.upload(metadata_json, $file_dir, type, $api_key) != true
    exit "Something went wrong"
  end

  if $wiki_bool == true
    puts "Please enter your Wiki password: "
    $wiki_pw = STDIN.noecho(&:gets).chomp
    $mw = MediawikiApi::Client.new("http://ftb.gamepedia.com/api.php")
    $mw.log_in($wiki_un, $wiki_pw)
    $other_mw = Wiki_Utils::Client.new("http://ftb.gamepedia.com/api.php")
    text = $other_mw.get_wikitext($wiki_page)
    if text == nil
      text = "On this page, the changelog for the #{$mod_name} mod is located.\n\n#{$wikichangelog}"
    else
      text.each_line do |line|
        if line[0,1] == "="
          text = text.gsub(line, $wikichangelog + "\n" + line)
        end
      end
    end
    params = {
      title: $wiki_page,
      text: text,
      summary: "Updating #{$mod_name} changelog for #{$new_vers} version.",
      minor: 1,
    }
    $mw.edit(params)
    main_wiki_page = $wiki_page[0, $wiki_page.index("/Changelog")]
    text = $other_mw.get_wikitext(main_wiki_page)
    if text != nil
      if text =~ /\|version\=(.*?)[\n]/
        if text !~ /\|version=#{$new_vers}/
          text = text.gsub(/\|version\=(.*?)[\n]/i, "|version=#{$new_vers}\n")
        end
      elsif text =~ /\{\{[Ii]nfobox mod/
        text = text.gsub(/\{\{[Ii]nfobox mod/, "{{Infobox mod\n|version=#{$new_vers}")
      end
    end
    params = {
      title: main_wiki_page,
      text: text,
      summary: "Updating #{$mod_name} version to #{$new_vers} version.",
      minor: 1
    }
    $mw.edit(params)
  end
  if $twitter_bool == true
    puts "Please enter your Twitter password: "
    $twitter_pw = STDIN.noecho(&:gets).chomp
    system("perl", "twitter.pl", $twitter_un, $twitter_pw, $twitter_msg)
  end
end

accepted_extensions = [".json", ".xml"]
puts "Please input your JSON or XML configuration file: "
file_name = gets.chomp
file = File.read(file_name)
if accepted_extensions.include? File.extname(file_name)
  if File.extname(file_name) == accepted_extensions[0]
    json = JSON.parse(file)
    puts "JSON"
    puts json
  elsif File.extname(file_name) == accepted_extensions[1]
    xml = Crack::XML.parse(file)
    json = xml.to_json
    puts "XML"
    puts json
  end
else
  exit "Uh, you need to provide an XML or JSON"
end
exit
handle_base_json(json)
handle_cf_json(json)
handle_wiki_json(json)
handle_twitter_json(json)
handle_changelog_json(json)

call_everything()

exit

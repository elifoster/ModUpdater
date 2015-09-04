require 'json'
require 'mediawiki_api'
require 'io/console'
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
    $game_vers = $cf["game_vers"]
    if $api_key != nil && $project != nil && $file_dir != nil
      if $release_type != "r" && $release_type != "b" && $release_type != "a"
        $release_type = "r"
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
      if $wiki["section_size"] == nil || $wiki["section_size"] < 2 || $wiki["section_size"] > 6
        $section_size = 2
      else
        $section_size = $wiki["section_size"]
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
            changeissue = entry["issue"]
            $wikichangelog = $wikichangelog + "* '''#{changetype}''': #{changechange} ([#{$issue_url}/#{changeissue} ##{changeissue}]).\n"
            $cfchangelog = $cfchangelog + "* **#{changetype}**: #{changechange} ([[#{$issue_url}/#{changeissue}|##{changeissue}]]).\n"
          else
            $wikichangelog = $wikichangelog + "* '''#{changetype}''': #{changechange}.\n"
            $cfchangelog = $cfchangelog + "* **#{changetype}**: #{changechange}.\n"
          end
        else
          if $issue_url != nil && entry["issue"] != nil && $inner["issues_bool"] == true
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

def call_everything()
  $cf = Bot::CurseForge.new($project)
  version_id = get_version_id_from_name($cf.get_versions($api_key), $game_vers)
  cf_params = {
    name: $file_name,
    game_version: version_id,
    releaseType: $release_type,
    change_log: $cfchangelog,
    file: $file_dir,
    change_markup_type: 'creole'
  }

  if $cf.upload(cf_params, $api_key) != true
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
      summary: "Updating #{$mod_name} changelog for #{$new_vers} version (test run).",
      minor: 1,
    }
    $mw.edit(params)
  end

  if $twitter_bool == true
    puts "Please enter your Twitter password: "
    $twitter_pw = STDIN.noecho(&:gets).chomp
    system("perl", "twitter.pl", $twitter_un, $twitter_pw, $twitter_msg)
  end
end

puts "Please input your JSON configuration file: "
file_name = gets.chomp
file = File.read(file_name)
json = JSON.parse(file)

handle_base_json(json)
handle_cf_json(json)
handle_wiki_json(json)
handle_twitter_json(json)
handle_changelog_json(json)

call_everything()

exit

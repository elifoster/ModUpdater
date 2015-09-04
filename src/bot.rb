require 'open_uri_redirections'
require 'net/http/post/multipart'
require 'rest-client'
require 'net/http'
require 'net/https'

module Bot
  class CurseForge
    def initialize(projectid)
      @uploadurl = "https://minecraft.curseforge.com/api/projects/#{projectid}/upload-file"
      @versionurl = "https://minecraft.curseforge.com/api/game/versions"
    end

    def get_versions(api_key)
      uri = open(@versionurl, "X-Api-Token" => api_key)
      return uri.read
    end

    def upload(metadata, file, mimetype, api_key)
      uri = URI("#{@uploadurl}?token=#{api_key}")
      req = Net::HTTP::Post::Multipart.new(
        uri,
        "metadata" => metadata,
        "file" => UploadIO.new(File.new(file), mimetype)
      )
      res = Net::HTTP.start(
        uri.host,
        uri.port,
        :use_ssl => true
      ) do |http|
        http.request(req)
      end
      if res.is_a? Net::HTTPSuccess || res == '201'
        return true
      else
        puts "You should probably report this to the ModUpdater developer."
        if res == '403'
          puts "You do not have permission to edit that project, or your API key was incorrect."
          exit 1
        elsif res == '404'
          puts "Project could not be found"
          exit 1
        elsif res == '405'
          puts "ModUpdater dev did GET instead of POST. Please report this to the author."
          exit 1
        elsif res == '422'
          puts "Provided parameters have an error in their form. Please check your JSON or report it to the ModUpdater author"
          exit 1
        else
          puts "What happened is unknown. Report it to the developer."
          puts "response body: #{res.body}"
          exit 1
        end
      end
    end
  end
end

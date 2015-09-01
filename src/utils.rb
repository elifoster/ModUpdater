require 'net/http'
require 'twitter'

module Bot
  class CurseForge
    def initialize(projectid)
      @cfapi = "http://minecraft.curseforge.com/projects/#{projectid}/upload-file.json"
    end

    def upload(params)
      request = URI(@cfapi)
      request.query = URI.encode_www_form(params)
      response = Net::HTTP.get_response(request)
      if response.is_a? Net::HTTPSuccess || response == '201'
        return true
      else
        if response == '403'
          exit "You do not have permission to edit that project, or your API key was incorrect."
        elsif response == '404'
          exit "Project could not be found"
        elsif response == '405'
          exit "ModUpdater dev did GET instead of POST. Please report this to the author."
        elsif response == '422'
          exit "Provided parameters have an error in their form. Please check your JSON or report it to the ModUpdater author"
        end
      end
    end
  end

  class Twitter
    def initialize()
      $client = Twitter::REST::Client.new do |config|
        config.consumer_key = ""
        config.consumer_secret = ""
      end
    end

    def tweet(message)
      $client.update(message)
    end
  end
end

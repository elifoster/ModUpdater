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
      if response.is_a? Net::HTTPSuccess
        return true
      else
        if response.
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

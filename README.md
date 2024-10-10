------------------------------[ IDEA ]------------------------------
- Twitch.tv chat anti-spam solution and statistics tool.

------------------------------[ WHAT IS TWITCH.TV? ]------------------------------
- Twitch is a live streaming video platform owned by Twitch Interactive, a subsidiary of Amazon.
  The service primarily focuses on video game live streaming, including broadcasts of eSports competitions,
  in addition to music broadcasts, creative content, and more recently, "in real life" streams.
  Content on the site can be viewed either live or via video on demand. [source: Wikipedia]

------------------------------[ IMPORTANT DETAILS ]------------------------------
- In order to create data mock up I used tool called 'Twitch Chat Downloader' (Python + Twitch API).
- Working with files is only a mock up of real linking-into the Real Time broadcast and Twitch's API.
- Records in the files are part of the records from real Twitch VOD (broadcast saved as video). [twitch.tv/videos/362861327]
- I used SwingBuilder to mock up chat for broadcaster. It's outside of Twitch's infrastructure and only seen by broadcaster while other solutions can be publicly seen.
- VOD I chose to work with perfectly presents the issue I'm trying to battle. Amount of spam is overwhelming.
- Culture within Twitch and it's user base is very specific and extreme amount of spam can lead to reduction of quality of experience and/or deteriorate level of communication between broadcaster and their viewers.
- Twitch chat enables various small images, custom emojis for each channel (broadcaster) called EMOTES. 
- Emotes require string of certain letters separated with space to apprear as image. Hence, emote spam is so easy to catch. 

------------------------------[ POSSIBLE FUTURE ]------------------------------
- In order to make my tool work in real cases I could do one of the following:
	 a. Connect the tool to the Twitch API as "BOT" - automatic moderator than supervises chat within Twitch infrastructure and the same chat as viewers use.
	 b. Make it into a bridge between Twitch API and one of the external distribution for chat overlay see only by broadcaster (ouside of Twitch's infrastructure). For example 'Overwolf Overlay' or 'Stream Elements'.
	 c. Join as provider and add my code to one of the ongoing projects creating tools for streamers and broadcasters. Example here would be 'Twitch LABS'. 

------------------------------[ DISCLAIMER ]------------------------------
- I do not take responsibility for content of the messages. They are just a test sample taken from VOD of real life broadcast solely for the purpose of testing my solution.
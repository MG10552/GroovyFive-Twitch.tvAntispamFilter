/** @author Głodowski Michał S10552 **/

package zad1;

import groovy.swing.SwingBuilder
import javax.swing.WindowConstants as WC
import javax.swing.*
import javax.swing.JScrollPane

// Choose initial file to work with. Mock up.
def sourceData = new FileInputStream("src/zad1/v362861327.txt") // In real life case can use URL or Twitch OAuth instead.
// Target for the output.
def target = new FileOutputStream("src/zad1/v362861327_cleaned.txt", true)

// For chat file read it line by line.
def readLbyL = sourceData.readLines().stream()

// Counters' definitions.
def mentions = 0 // Number of mentions during broadcast.
def mc = 0 // Total number of reviewed records.
def linksRedacted = 0 // Number of links removed during broadcast.
def spamRemoved = 0 // Number of messages captured as spam during broadcast.
def chatToRead = [] // Container for swingChat.

if (sourceData != null && readLbyL != null) {
	// Take one message - work with it - return the result. Simulate real time work - one by one.
	readLbyL.each {
		def formatData = it.split(" ")
		
		// Cut timestamps and authors away from the content of the messages.
		def timestamps = formatData.take(1)
		def users = formatData[1]
		
		// Take "the rest". These are messages posted by viewers.
		def messages = formatData.drop(2)
		
		// Join words of the message into singular message.
		def realMsg = messages.join(" ")
		
		// Check how many times viewers mentioned each other or broadcaster via Twitch chat @[name] function.
		if (realMsg =~ /@(\w+)\b/) {
			mentions++
		}
		// Look for links and remove them. If link is found replace it with "[REDACTED - external link]"
		def linkFinder = /(https?:\/\/)?(www\.)?\w+\.\w+/
		
		if (realMsg =~ linkFinder) {
			realMsg = realMsg.replaceAll(linkFinder, "[REDACTED - external link]")
			linksRedacted++
		}
		
		// Remove messages with overly repeated phrases within one message. Remove messages considered as spam.
		def formatMsg = realMsg.toLowerCase().split(" ").toList()
		def spamDetector = formatMsg.groupBy().collectEntries {
			[(it.key) : it.value.size()]
		}
		
		// If the same word / phrase is repeated more than 3 times consider this message a spam and replace it's content.
		// Important note: Twitch does not allow messages to be deleted completely during or even after the broadcast. They only can be censored or edited to say something else.
		spamDetector.each {
			if (it.value > 3) {
				realMsg = "--------------------[Message REDACTED - obnoxious spam]--------------------"
				spamRemoved++
				println "Obnoxious SPAM found. (" + spamRemoved + ") Phrase --> " + it.key
			}
		}
		
		// Add one to total number of messages.
		mc++
		
		// Combine time, users and their messages back together.
		def output_RT = users + " " + realMsg
		def output_LOG = timestamps + users + realMsg
		
		// Join output's elements together into one record.
		def readableLog = output_LOG.join(" ")+"\n"
		
		// Push result to the SwingChat.
		chatToRead << output_RT
		
		// Push result to the target (in this case) file.
		target << readableLog
	}

	procOfSpam = ((spamRemoved.toDouble()/mc.toDouble())*100).round(2)
	
	// Summary of statistics.
	println "\n----------[STATS]----------"
	println " 1. Various viewers were mentioned personally (" + mentions + ") times."
	println " 2. Removed (" + linksRedacted + ") external links."
	println " 3. Removed (" + spamRemoved + ") spam messages."
	println " 4. Viewers collectively posted (" + mc + ") messages during the broadcast."
	println " 5. Approximately (" + procOfSpam + "%) of messages were spam."
	println "---------------------------"

	// Example of external use with chat that can be read spam-free.
	def swingChat = new SwingBuilder().edt {
		lookAndFeel('com.sun.java.swing.plaf.windows.WindowsLookAndFeel')
		frame = frame(pack:true, visible:true, defaultCloseOperation:WC.EXIT_ON_CLOSE) {
			hbox() {
				scrollPane(verticalScrollBarPolicy:JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ) {
					list(id: 'fl', listData: chatToRead)
				}
			}
		}.pack()
	}

} else {
	println "Could not read the file! It seems that source does not contain required data to analyze. Make sure data source is correct."
}
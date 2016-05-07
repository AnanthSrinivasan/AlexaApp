# AlexaApp
Alexa app to place order 

Install Brew: </br>
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

Install Maven: </br>
brew update
brew install maven

Add the below line to your ~/.bashrc </br>
export PATH=$PATH:/usr/local/Cellar/maven/3.3.9/bin

Command to build the Alexa app and get a jar, </br>
mvn assembly:assembly -DdescriptorId=jar-with-dependencies package
